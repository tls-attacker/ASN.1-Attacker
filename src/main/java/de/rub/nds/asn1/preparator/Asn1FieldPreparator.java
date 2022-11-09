/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.model.Asn1Field;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Asn1FieldPreparator<T extends Asn1Field> extends Preparator {

    private static final Logger LOGGER = LogManager.getLogger();

    private final T field;

    public Asn1FieldPreparator(final T field) {
        this.field = field;
    }

    @Override
    public void prepare() {
        field.setContent(encodeContent());
        field.setLength(BigInteger.valueOf(field.getContent().getValue().length));
        field.setLengthOctets(encodeLength(field.getLength().getValue()));
        field.setTagOctets(encodeIdentifier());
    }

    protected abstract byte[] encodeContent();

    private byte[] encodeIdentifier() {
        byte firstIdentifierByte = 0;
        firstIdentifierByte = this.encodeTagClass(firstIdentifierByte, this.field.getTagClass().getValue());
        firstIdentifierByte = this.encodeIsConstructed(firstIdentifierByte, this.field.getTagConstructed().getValue());
        return this.encodeTagNumber(firstIdentifierByte, this.field.getTagNumber().getValue());
    }

    private byte encodeTagClass(byte firstIdentifierByte, int tagClass) {
        return (byte) (firstIdentifierByte | ((tagClass & 0x3) << 6));
    }

    private byte encodeIsConstructed(byte firstIdentifierByte, boolean isConstructed) {
        return ((isConstructed == true) ? (byte) (firstIdentifierByte | 0x20) : firstIdentifierByte);
    }

    private byte[] encodeTagNumber(byte firstIdentifierByte, int tagNumber) {
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        ByteArrayOutputStream longEncodingStream = new ByteArrayOutputStream();
        try {
            if (tagNumber < 0) {
                LOGGER.warn("Tag number is smaller than zero. Defaulting to zero!");
                tagNumber = 0;
            }
            if (this.field.getLongTagLength().getValue() == 0 && tagNumber <= 0x1F) {

                byte[] result = new byte[] { firstIdentifierByte };
                result[0] |= (byte) (tagNumber & 0x1F);
                resultStream.write(result);
            } else {
                int longTagNumberBytes = this.field.getLongTagLength().getValue();
                // Quick Fix
                if (longTagNumberBytes > 65535) {
                    LOGGER.warn("Fix von longTagNumberBytes: critical value: " + longTagNumberBytes);
                    longTagNumberBytes = 65535;
                }
                byte[] longEncoding = this.encodeLongTagNumber(tagNumber);
                if (longEncoding.length < longTagNumberBytes) {
                    longEncodingStream.write(new byte[longTagNumberBytes - longEncoding.length]);
                    longEncodingStream.write(longEncoding);
                }
                firstIdentifierByte = (byte) (firstIdentifierByte | 0x1F);
                resultStream.write(new byte[] { firstIdentifierByte });
                resultStream.write(longEncoding);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return resultStream.toByteArray();
    }

    private byte[] encodeLongTagNumber(int tagNumber) {
        int tagNumberByteCount = this.getTagNumberByteCount(tagNumber);
        byte[] result = new byte[tagNumberByteCount];
        byte moreFlag = 0x00;
        for (int i = tagNumberByteCount - 1; i >= 0; i--) {
            result[i] = (byte) (moreFlag | (tagNumber & 0x7F));
            tagNumber = tagNumber >> 7;
            moreFlag = (byte) 0x80;
        }
        return result;
    }

    private int getTagNumberByteCount(int tagNumber) {
        int result = 0;
        while (tagNumber > 0) {
            result++;
            tagNumber = tagNumber >> 7;
        }
        return result;
    }

    private byte[] encodeLength(BigInteger contentLength) {
        this.field.setLength(contentLength);
        BigInteger length = this.field.getLength().getValue();
        if (length.compareTo(BigInteger.ZERO) == -1) {
            LOGGER.warn("Field length is smaller than zero. Defaulting to zero!");
            length = BigInteger.ZERO;
        }
        if (this.field.getLongLength().getValue() == 0 && length.compareTo(BigInteger.valueOf(127)) <= 0) {
            return new byte[] { (byte) length.byteValue() };
        } else {
            return encodeLongLength(length);
        }
    }

    private byte[] encodeLongLength(BigInteger length) {
        try {
            byte[] result = null;
            byte[] longLengthBytes = length.toByteArray();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int longLengthBytesLength = this.field.getLongLength().getValue();
            // Quick Fix
            if (longLengthBytesLength > 65535) {
                LOGGER.warn("Fix von longLengthBytes: critical value: " + longLengthBytes);
                longLengthBytesLength = 65535;
            }
            if (longLengthBytes[0] == 0x00) {
                if (longLengthBytes.length < (longLengthBytesLength + 1)) {
                    outputStream.write(new byte[longLengthBytesLength + 1 - longLengthBytes.length]);
                    outputStream.write(longLengthBytes);
                }
                longLengthBytes[0] = (byte) (0x80 | longLengthBytes.length - 1);
                result = longLengthBytes;
            } else {
                byte[] prefix = new byte[] { (byte) (0x80 | longLengthBytes.length) };
                outputStream.write(prefix);
                if (longLengthBytes.length < (longLengthBytesLength)) {
                    outputStream.write(new byte[longLengthBytesLength - longLengthBytes.length]);
                    outputStream.write(longLengthBytes);
                }
            }
            return result;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

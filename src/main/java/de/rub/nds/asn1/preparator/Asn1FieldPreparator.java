/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.model.Asn1Field;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Asn1FieldPreparator<T extends Asn1Field> extends Preparator {

    private static final Logger LOGGER = LogManager.getLogger();

    protected final T field;

    public Asn1FieldPreparator(final T field) {
        this.field = field;
    }

    @Override
    public void prepare() {
        LOGGER.info("Preparing: {}", field.getIdentifier());
        field.setContent(encodeContent());
        field.setLength(BigInteger.valueOf(field.getContent().getValue().length));
        field.setLengthOctets(encodeLength(field.getLength().getValue()));
        field.setTagClass(field.getTagClassType().getIntValue());
        field.setTagConstructed(field.getTagConstructedType() == TagConstructed.CONSTRUCTED);
        field.setTagNumber(field.getTagNumberType().getIntValue());
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
            if (tagNumber <= 0x1F) {

                byte[] result = new byte[]{firstIdentifierByte};
                result[0] |= (byte) (tagNumber & 0x1F);
                resultStream.write(result);
            } else {
                int longTagNumberBytes = getTagNumberByteCount(tagNumber);
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
                resultStream.write(new byte[]{firstIdentifierByte});
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

    private int getLengthByteCount(BigInteger length) {
        return ((length.bitLength() / 8) / 8) + 1;
    }

    private byte[] encodeLength(BigInteger contentLength) {
        this.field.setLength(contentLength);
        BigInteger length = this.field.getLength().getValue();
        if (length.compareTo(BigInteger.ZERO) == -1) {
            LOGGER.warn("Field length is smaller than zero. Defaulting to zero!");
            length = BigInteger.ZERO;
        }
        if (length.compareTo(BigInteger.valueOf(127)) <= 0) {
            return new byte[]{(byte) length.byteValue()};
        } else {
            return encodeLongLength(length);
        }
    }

    private byte[] encodeLongLength(BigInteger length) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int numberOfBytes = getLengthByteCount(length);
        outputStream.write(numberOfBytes | 0x80);
        outputStream.writeBytes(ArrayConverter.intToBytes(length.bitLength() / 8, numberOfBytes));
        return outputStream.toByteArray();
    }
}

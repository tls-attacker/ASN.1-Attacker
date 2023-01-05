/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.context.AbstractContext;
import de.rub.nds.asn1.model.Asn1Field;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Asn1FieldPreparator<Context extends AbstractContext, T extends Asn1Field>
        extends Preparator {

    private static final Logger LOGGER = LogManager.getLogger();

    protected final T field;
    protected final Context context;

    public Asn1FieldPreparator(Context context, final T field) {
        this.field = field;
        this.context = context;
    }

    @Override
    public void prepare() {
        LOGGER.trace("Preparing: {}", field.getIdentifier());
        prepareContent();
        prepareLength();
        prepareLengthOctets();
        prepareTagClass();
        prepareTagConstructed();
        prepareTagNumber();
        prepareTagOctets();
    }

    public void prepareTagOctets() {
        field.setTagOctets(encodeIdentifier());
    }

    public void prepareTagNumber() {
        if (field.getTagNumberType() != null) {
            field.setTagNumber(field.getTagNumberType().getIntValue());
        } else {
            field.setTagNumber(0);
        }
    }

    public void prepareTagConstructed() {
        field.setTagConstructed(field.getTagConstructedType() == TagConstructed.CONSTRUCTED);
    }

    public void prepareTagClass() {
        field.setTagClass(field.getTagClassType().getIntValue());
    }

    public void prepareLengthOctets() {
        field.setLengthOctets(encodeLength(field.getLength().getValue()));
    }

    public void prepareLength() {
        field.setLength(BigInteger.valueOf(field.getContent().getValue().length));
    }

    public void prepareContent() {
        field.setContent(encodeContent());
    }

    protected abstract byte[] encodeContent();

    private byte[] encodeIdentifier() {
        byte firstIdentifierByte = 0;
        firstIdentifierByte =
                this.encodeTagClass(firstIdentifierByte, this.field.getTagClass().getValue());
        firstIdentifierByte =
                this.encodeIsConstructed(
                        firstIdentifierByte, this.field.getTagConstructed().getValue());
        return this.encodeTagNumber(firstIdentifierByte, this.field.getTagNumber().getValue());
    }

    private byte encodeTagClass(byte firstIdentifierByte, int tagClass) {
        return (byte) (firstIdentifierByte | ((tagClass & 0x3) << 6));
    }

    private byte encodeIsConstructed(byte firstIdentifierByte, boolean isConstructed) {
        return ((isConstructed == true)
                ? (byte) (firstIdentifierByte | 0x20)
                : firstIdentifierByte);
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

                byte[] result = new byte[] {firstIdentifierByte};
                result[0] |= (byte) (tagNumber & 0x1F);
                resultStream.write(result);
            } else {
                int longTagNumberBytes = getTagNumberByteCount(tagNumber);
                // Quick Fix
                if (longTagNumberBytes > 65535) {
                    LOGGER.warn(
                            "Fix von longTagNumberBytes: critical value: " + longTagNumberBytes);
                    longTagNumberBytes = 65535;
                }
                byte[] longEncoding = this.encodeLongTagNumber(tagNumber);
                if (longEncoding.length < longTagNumberBytes) {
                    longEncodingStream.write(new byte[longTagNumberBytes - longEncoding.length]);
                    longEncodingStream.write(longEncoding);
                }
                firstIdentifierByte = (byte) (firstIdentifierByte | 0x1F);
                resultStream.write(new byte[] {firstIdentifierByte});
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
        int fullBytes = length.bitLength() / 8;
        if (length.bitLength() % 8 != 0) {
            fullBytes++;
        }
        return fullBytes;
    }

    private byte[] encodeLength(BigInteger contentLength) {
        this.field.setLength(contentLength);
        BigInteger length = this.field.getLength().getValue();
        if (length.compareTo(BigInteger.ZERO) == -1) {
            LOGGER.warn("Field length is smaller than zero. Defaulting to zero!");
            length = BigInteger.ZERO;
        }
        if (length.compareTo(BigInteger.valueOf(127)) <= 0) {
            return new byte[] {(byte) length.byteValue()};
        } else {
            return encodeLongLength(length);
        }
    }

    private byte[] encodeLongLength(BigInteger length) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int numberOfBytes = getLengthByteCount(length);
        outputStream.write(numberOfBytes | 0x80);
        LOGGER.info(
                "Encoding: "
                        + length
                        + " as "
                        + ArrayConverter.bytesToHexString(
                                ArrayConverter.bigIntegerToByteArray(length, numberOfBytes, true)));
        outputStream.writeBytes(ArrayConverter.bigIntegerToByteArray(length, numberOfBytes, true));
        return outputStream.toByteArray();
    }
}

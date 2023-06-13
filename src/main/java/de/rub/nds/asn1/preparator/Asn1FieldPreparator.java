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
import de.rub.nds.asn1.constants.TimeAccurracy;
import de.rub.nds.asn1.model.Asn1Field;
import de.rub.nds.asn1.oid.ObjectIdentifier;
import de.rub.nds.asn1.time.TimeEncoder;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

public abstract class Asn1FieldPreparator<Field extends Asn1Field> {

    private static final Logger LOGGER = LogManager.getLogger();

    protected final Field field;

    public Asn1FieldPreparator(final Field field) {
        this.field = field;
    }

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

    protected byte[] encodeBoolean(boolean value) {
        if (value == true) {
            return new byte[] {(byte) 0xFF};
        } else {
            return new byte[1];
        }
    }

    protected byte[] encodeBoolean(BigInteger bigInt) {
        int size = bigInt.toByteArray().length;
        if (bigInt.testBit(size * 8)) {
            return ArrayConverter.bigIntegerToByteArray(bigInt, size + 1, true);
        } else {
            return bigInt.toByteArray();
        }
    }

    protected byte[] encodeNull() {
        return new byte[0];
    }

    protected byte[] encodeObjectIdentifier(ObjectIdentifier oid) {
        return oid.getEncoded();
    }

    protected byte[] encodePrimitiveBitString(byte[] usedBits, Byte unusedBits, Byte padding) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(new byte[] {unusedBits});
            byte[] encodedContent = Arrays.copyOf(usedBits, usedBits.length);
            encodedContent = shiftLeft(encodedContent, unusedBits);
            encodedContent[encodedContent.length - 1] &= (0xFF - (1 << unusedBits - 1));
            encodedContent[encodedContent.length - 1] |= padding;
            outputStream.write(encodedContent);
            return outputStream.toByteArray();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected byte[] encodeGeneralizedTime(DateTime date, TimeAccurracy accurracy) {
        return TimeEncoder.encodeGeneralizedTimeLocalTime(date, accurracy).getBytes();
    }

    protected byte[] encodeFullUtcTime(DateTime date, TimeAccurracy accurracy) {
        return TimeEncoder.encodeFullUtc(date, accurracy).getBytes();
    }

    protected byte[] encodeGeneralizedTimeUtc(DateTime date, TimeAccurracy accurracy) {
        return TimeEncoder.encodeGeneralizedTimeUtc(date, accurracy).getBytes();
    }

    protected byte[] encodeIa5String(String tempString) {
        return tempString.getBytes(StandardCharsets.US_ASCII);
    }

    /**
     * Encodes a printable string. We also allow non-printable characters
     *
     * @param tempString the string to encode
     * @return the encoded string
     */
    protected byte[] encodePrintableString(String tempString) {
        return tempString.getBytes(StandardCharsets.US_ASCII);
    }

    protected byte[] encodeOctetString(byte[] bytes) {
        return bytes;
    }

    protected byte[] encodeT61String(String tempString) {
        /** TODO Not sure this is correct... */
        return tempString.getBytes(StandardCharsets.UTF_8);
    }

    protected byte[] encodeUtf8String(String tempString) {
        return tempString.getBytes(StandardCharsets.UTF_8);
    }

    private byte[] shiftLeft(byte[] input, int n) {
        if (input.length == 0) {
            return input;
        }
        BigInteger tempBigInt = new BigInteger(1, input);
        tempBigInt = tempBigInt.shiftLeft(n);
        return tempBigInt.toByteArray();
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

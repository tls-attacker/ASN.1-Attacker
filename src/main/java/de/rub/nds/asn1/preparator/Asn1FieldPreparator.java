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
import de.rub.nds.asn1.model.Asn1BitString;
import de.rub.nds.asn1.model.Asn1Boolean;
import de.rub.nds.asn1.model.Asn1Field;
import de.rub.nds.asn1.model.Asn1GeneralizedTime;
import de.rub.nds.asn1.model.Asn1Ia5String;
import de.rub.nds.asn1.model.Asn1Integer;
import de.rub.nds.asn1.model.Asn1Null;
import de.rub.nds.asn1.model.Asn1ObjectIdentifier;
import de.rub.nds.asn1.model.Asn1OctetString;
import de.rub.nds.asn1.model.Asn1PrintableString;
import de.rub.nds.asn1.model.Asn1T61String;
import de.rub.nds.asn1.model.Asn1UtcTime;
import de.rub.nds.asn1.model.Asn1Utf8String;
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

    public final void prepare() {
        LOGGER.trace("Preparing: {}", field.getIdentifier());
        prepareContent(field);
        prepareAfterContent(field);
    }

    /**
     * Prepares all generic values in the field that are NOT the content. Expects that the content
     * is already set beforehand (to compute the length fields)
     *
     * @param field
     */
    protected final void prepareAfterContent(Asn1Field field) {
        prepareLength(field);
        prepareLengthOctets(field);
        prepareTagClass(field);
        prepareTagConstructed(field);
        prepareTagNumber(field);
        prepareTagOctets(field);
    }

    public void prepareTagOctets(Asn1Field field) {
        field.setTagOctets(encodeIdentifier(field));
    }

    public void prepareTagNumber(Asn1Field field) {
        if (field.getUniversalTagNumberType() != null) {
            field.setTagNumber(field.getUniversalTagNumberType().getIntValue());
        } else {
            field.setTagNumber(0);
        }
    }

    public void prepareTagConstructed(Asn1Field field) {
        field.setTagConstructed(field.getTagConstructedType() == TagConstructed.CONSTRUCTED);
    }

    public void prepareTagClass(Asn1Field field) {
        field.setTagClass(field.getTagClassType().getIntValue());
    }

    public void prepareLengthOctets(Asn1Field field) {
        field.setLengthOctets(encodeLength(field.getLength().getValue()));
    }

    public void prepareLength(Asn1Field field) {
        field.setLength(BigInteger.valueOf(field.getContent().getValue().length));
    }

    public void prepareContent(Asn1Field field) {
        field.setContent(encodeContent());
    }

    protected abstract byte[] encodeContent();

    private byte[] encodeIdentifier(Asn1Field field) {
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

    protected Asn1Boolean prepareField(Asn1Boolean asn1Boolean, boolean value) {
        if (asn1Boolean == null) {
            asn1Boolean = new Asn1Boolean("boolean");
        }
        asn1Boolean.setValue(value);
        asn1Boolean.setContent(encodeBoolean(asn1Boolean.getValue().getValue()));
        prepareAfterContent(asn1Boolean);
        return asn1Boolean;
    }

    protected Asn1Integer prepareField(Asn1Integer asn1Integer, BigInteger value) {
        if (asn1Integer == null) {
            asn1Integer = new Asn1Integer("integer");
        }
        asn1Integer.setValue(value);
        asn1Integer.setContent(encodeInteger(asn1Integer.getValue().getValue()));
        prepareAfterContent(asn1Integer);
        return asn1Integer;
    }

    protected Asn1Null prepareField(Asn1Null asn1Null) {
        if (asn1Null == null) {
            asn1Null = new Asn1Null("null");
        }
        asn1Null.setContent(encodeNull());
        prepareAfterContent(asn1Null);
        return asn1Null;
    }

    protected Asn1ObjectIdentifier prepareField(
            Asn1ObjectIdentifier asn1ObjectIdentifier, ObjectIdentifier oid) {
        if (asn1ObjectIdentifier == null) {
            asn1ObjectIdentifier = new Asn1ObjectIdentifier("objectIdentifier");
        }
        asn1ObjectIdentifier.setValue(oid.toString());
        asn1ObjectIdentifier.setContent(
                encodeObjectIdentifier(
                        new ObjectIdentifier(asn1ObjectIdentifier.getValue().getValue())));
        prepareAfterContent(asn1ObjectIdentifier);
        return asn1ObjectIdentifier;
    }

    protected Asn1BitString prepareField(
            Asn1BitString asn1BitString, byte[] usedBits, byte unusedBits) {
        if (asn1BitString == null) {
            asn1BitString = new Asn1BitString("bitstring");
        }
        asn1BitString.setUsedBits(asn1BitString.getUsedBits().getValue());
        asn1BitString.setUnusedBits(asn1BitString.getUnusedBits().getValue());
        asn1BitString.setPadding(asn1BitString.getPadding().getValue());
        asn1BitString.setContent(encodeBitString(usedBits, unusedBits, (byte) 0));
        prepareAfterContent(asn1BitString);
        return asn1BitString;
    }

    protected Asn1GeneralizedTime prepareField(
            Asn1GeneralizedTime asn1GeneralizedTime, DateTime time, TimeAccurracy accurracy) {
        if (asn1GeneralizedTime == null) {
            asn1GeneralizedTime = new Asn1GeneralizedTime("generalizedTime");
        }
        asn1GeneralizedTime.setValue(new String(encodeGeneralizedTime(time, accurracy)));
        asn1GeneralizedTime.setContent(asn1GeneralizedTime.getValue().getValue().getBytes());
        prepareAfterContent(asn1GeneralizedTime);
        return asn1GeneralizedTime;
    }

    protected Asn1UtcTime prepareField(
            Asn1UtcTime asn1UtcTime, DateTime time, TimeAccurracy accurracy) {
        if (asn1UtcTime == null) {
            asn1UtcTime = new Asn1UtcTime("utcTime");
        }
        asn1UtcTime.setValue(new String(encodeFullUtcTime(time, accurracy)));
        asn1UtcTime.setContent(asn1UtcTime.getValue().getValue().getBytes());
        prepareAfterContent(asn1UtcTime);
        return asn1UtcTime;
    }

    protected Asn1PrintableString prepareField(
            Asn1PrintableString asn1PrintableString, String value) {
        if (asn1PrintableString == null) {
            asn1PrintableString = new Asn1PrintableString("printableString");
        }
        asn1PrintableString.setValue(value);
        asn1PrintableString.setContent(
                encodePrintableString(asn1PrintableString.getValue().getValue()));
        prepareAfterContent(asn1PrintableString);
        return asn1PrintableString;
    }

    protected Asn1Ia5String prepareField(Asn1Ia5String asn1Ia5String, String value) {
        if (asn1Ia5String == null) {
            asn1Ia5String = new Asn1Ia5String("ia5String");
        }
        asn1Ia5String.setValue(value);
        asn1Ia5String.setContent(encodePrintableString(asn1Ia5String.getValue().getValue()));
        prepareAfterContent(asn1Ia5String);
        return asn1Ia5String;
    }

    protected Asn1OctetString prepareField(Asn1OctetString asn1OctetString, byte[] value) {
        if (asn1OctetString == null) {
            asn1OctetString = new Asn1OctetString("octetString");
        }
        asn1OctetString.setValue(value);
        asn1OctetString.setContent(encodeOctetString(asn1OctetString.getValue().getValue()));
        prepareAfterContent(asn1OctetString);
        return asn1OctetString;
    }

    protected Asn1T61String prepareField(Asn1T61String asn1T61String, String value) {
        if (asn1T61String == null) {
            asn1T61String = new Asn1T61String("t61String");
        }
        asn1T61String.setValue(value);
        asn1T61String.setContent(encodeT61String(asn1T61String.getValue().getValue()));
        prepareAfterContent(asn1T61String);
        return asn1T61String;
    }

    protected Asn1Utf8String prepareField(Asn1Utf8String asn1Utf8String, String value) {
        if (asn1Utf8String == null) {
            asn1Utf8String = new Asn1Utf8String("utf8String");
        }
        asn1Utf8String.setValue(value);
        asn1Utf8String.setContent(encodeT61String(asn1Utf8String.getValue().getValue()));
        prepareAfterContent(asn1Utf8String);
        return asn1Utf8String;
    }

    protected byte[] encodeBoolean(boolean value) {
        if (value == true) {
            return new byte[] {(byte) 0xFF};
        } else {
            return new byte[1];
        }
    }

    protected byte[] encodeInteger(BigInteger bigInt) {
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

    protected byte[] encodeBitString(byte[] usedBits, Byte unusedBits, Byte padding) {
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

/*
 * ASN.1-Attacker - A library for arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.TimeAccurracy;
import de.rub.nds.asn1.model.Asn1BitString;
import de.rub.nds.asn1.model.Asn1BmpString;
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
import de.rub.nds.asn1.model.Asn1UniversalString;
import de.rub.nds.asn1.model.Asn1UtcTime;
import de.rub.nds.asn1.model.Asn1Utf8String;
import de.rub.nds.asn1.oid.ObjectIdentifier;
import de.rub.nds.asn1.time.TimeEncoder;
import de.rub.nds.asn1.time.TimeField;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

public class Asn1PreparatorHelper {

    private static final Logger LOGGER = LogManager.getLogger();

    private Asn1PreparatorHelper() {}

    public static void prepareTagOctets(Asn1Field field) {
        field.setTagOctets(encodeTag(field));
    }

    public static void prepareTagNumber(Asn1Field field) {
        if (field.getUniversalTagNumberType() != null) {
            field.setTagNumber(field.getUniversalTagNumberType().getIntValue());
        } else {
            field.setTagNumber(0);
        }
    }

    public static void prepareTagConstructed(Asn1Field field) {
        field.setTagConstructed(field.getTagConstructedType() == TagConstructed.CONSTRUCTED);
    }

    public static void prepareTagClass(Asn1Field field) {
        field.setTagClass(field.getTagClassType().getIntValue());
    }

    public static void prepareLengthOctets(Asn1Field field) {
        field.setLengthOctets(encodeLength(field, field.getLength().getValue()));
    }

    public static void prepareLength(Asn1Field field) {
        field.setLength(BigInteger.valueOf(field.getContent().getValue().length));
    }

    private static byte[] encodeTag(Asn1Field field) {
        byte firstIdentifierByte = 0;
        firstIdentifierByte = encodeTagClass(firstIdentifierByte, field.getTagClass().getValue());
        firstIdentifierByte =
                encodeIsConstructed(firstIdentifierByte, field.getTagConstructed().getValue());
        return encodeTagNumber(firstIdentifierByte, field.getTagNumber().getValue());
    }

    private static byte encodeTagClass(byte firstIdentifierByte, int tagClass) {
        return (byte) (firstIdentifierByte | ((tagClass & 0x3) << 6));
    }

    private static byte encodeIsConstructed(byte firstIdentifierByte, boolean isConstructed) {
        return ((isConstructed == true)
                ? (byte) (firstIdentifierByte | 0x20)
                : firstIdentifierByte);
    }

    private static byte[] encodeTagNumber(byte firstIdentifierByte, int tagNumber) {
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
                byte[] longEncoding = encodeLongTagNumber(tagNumber);
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

    /**
     * Prepares all generic values in the field that are NOT the content. Expects that the content
     * is already set beforehand (to compute the length fields)
     *
     * @param field the field to prepare
     */
    public static void prepareAfterContent(Asn1Field field) {
        Asn1PreparatorHelper.prepareLength(field);
        Asn1PreparatorHelper.prepareLengthOctets(field);
        Asn1PreparatorHelper.prepareTagClass(field);
        Asn1PreparatorHelper.prepareTagConstructed(field);
        Asn1PreparatorHelper.prepareTagNumber(field);
        Asn1PreparatorHelper.prepareTagOctets(field);
    }

    public static Asn1Boolean prepareField(Asn1Boolean asn1Boolean, boolean value) {
        if (asn1Boolean == null) {
            asn1Boolean = new Asn1Boolean("boolean");
        }
        asn1Boolean.setValue(value);
        asn1Boolean.setContent(encodeBoolean(asn1Boolean.getValue().getValue()));
        prepareAfterContent(asn1Boolean);
        return asn1Boolean;
    }

    public static Asn1Integer prepareField(Asn1Integer asn1Integer, BigInteger value) {
        if (asn1Integer == null) {
            asn1Integer = new Asn1Integer("integer");
        }
        asn1Integer.setValue(value);
        asn1Integer.setContent(encodeInteger(asn1Integer.getValue().getValue()));
        prepareAfterContent(asn1Integer);
        return asn1Integer;
    }

    public static Asn1Null prepareField(Asn1Null asn1Null) {
        if (asn1Null == null) {
            asn1Null = new Asn1Null("null");
        }
        asn1Null.setContent(encodeNull());
        prepareAfterContent(asn1Null);
        return asn1Null;
    }

    public static Asn1ObjectIdentifier prepareField(
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

    public static Asn1BitString prepareField(
            Asn1BitString asn1BitString, byte[] usedBits, byte unusedBits) {
        if (asn1BitString == null) {
            asn1BitString = new Asn1BitString("bitstring");
        }
        asn1BitString.setUsedBits(usedBits);
        asn1BitString.setUnusedBits(unusedBits);
        asn1BitString.setPadding((byte) 0);
        asn1BitString.setContent(
                encodeBitString(
                        asn1BitString.getUsedBits().getValue(),
                        asn1BitString.getUnusedBits().getValue(),
                        asn1BitString.getPadding().getValue()));
        prepareAfterContent(asn1BitString);
        return asn1BitString;
    }

    public static Asn1GeneralizedTime prepareField(
            Asn1GeneralizedTime asn1GeneralizedTime, DateTime time, TimeAccurracy accurracy) {
        if (asn1GeneralizedTime == null) {
            asn1GeneralizedTime = new Asn1GeneralizedTime("generalizedTime");
        }
        asn1GeneralizedTime.setValue(new String(encodeGeneralizedTime(time, accurracy)));
        asn1GeneralizedTime.setContent(asn1GeneralizedTime.getValue().getValue().getBytes());
        prepareAfterContent(asn1GeneralizedTime);
        return asn1GeneralizedTime;
    }

    public static TimeField prepareField(
            TimeField timeField, DateTime time, TimeAccurracy accurracy) {
        if (timeField instanceof Asn1UtcTime) {
            return prepareField((Asn1UtcTime) timeField, time, accurracy);
        } else if (timeField instanceof Asn1GeneralizedTime) {
            return prepareField((Asn1GeneralizedTime) timeField, time, accurracy);
        } else {
            LOGGER.error("Unknown TimeField");
            return null;
        }
    }

    public static Asn1UtcTime prepareField(
            Asn1UtcTime asn1UtcTime, DateTime time, TimeAccurracy accurracy) {
        if (asn1UtcTime == null) {
            asn1UtcTime = new Asn1UtcTime("utcTime");
        }
        asn1UtcTime.setValue(new String(encodeFullUtcTime(time, accurracy)));
        asn1UtcTime.setContent(asn1UtcTime.getValue().getValue().getBytes());
        prepareAfterContent(asn1UtcTime);
        return asn1UtcTime;
    }

    public static Asn1PrintableString prepareField(
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

    public static Asn1BmpString prepareField(Asn1BmpString asn1BmpString, String value) {
        if (asn1BmpString == null) {
            asn1BmpString = new Asn1BmpString("bmpString");
        }
        asn1BmpString.setValue(value);
        asn1BmpString.setContent(encodeBmpString(asn1BmpString.getValue().getValue()));
        prepareAfterContent(asn1BmpString);
        return asn1BmpString;
    }

    public static Asn1UniversalString prepareField(
            Asn1UniversalString asn1UniversalString, String value) {
        if (asn1UniversalString == null) {
            asn1UniversalString = new Asn1UniversalString("universalString");
        }
        asn1UniversalString.setValue(value);
        asn1UniversalString.setContent(
                encodeUniversalString(asn1UniversalString.getValue().getValue()));
        prepareAfterContent(asn1UniversalString);
        return asn1UniversalString;
    }

    public static Asn1Ia5String prepareField(Asn1Ia5String asn1Ia5String, String value) {
        if (asn1Ia5String == null) {
            asn1Ia5String = new Asn1Ia5String("ia5String");
        }
        asn1Ia5String.setValue(value);
        asn1Ia5String.setContent(encodePrintableString(asn1Ia5String.getValue().getValue()));
        prepareAfterContent(asn1Ia5String);
        return asn1Ia5String;
    }

    public static Asn1OctetString prepareField(Asn1OctetString asn1OctetString, byte[] value) {
        if (asn1OctetString == null) {
            asn1OctetString = new Asn1OctetString("octetString");
        }
        asn1OctetString.setValue(value);
        asn1OctetString.setContent(encodeOctetString(asn1OctetString.getValue().getValue()));
        prepareAfterContent(asn1OctetString);
        return asn1OctetString;
    }

    public static Asn1T61String prepareField(Asn1T61String asn1T61String, String value) {
        if (asn1T61String == null) {
            asn1T61String = new Asn1T61String("t61String");
        }
        asn1T61String.setValue(value);
        asn1T61String.setContent(encodeT61String(asn1T61String.getValue().getValue()));
        prepareAfterContent(asn1T61String);
        return asn1T61String;
    }

    public static Asn1Utf8String prepareField(Asn1Utf8String asn1Utf8String, String value) {
        if (asn1Utf8String == null) {
            asn1Utf8String = new Asn1Utf8String("utf8String");
        }
        asn1Utf8String.setValue(value);
        asn1Utf8String.setContent(encodeT61String(asn1Utf8String.getValue().getValue()));
        prepareAfterContent(asn1Utf8String);
        return asn1Utf8String;
    }

    public static byte[] encodeBoolean(boolean value) {
        if (value == true) {
            return new byte[] {(byte) 0xFF};
        } else {
            return new byte[1];
        }
    }

    public static byte[] encodeInteger(BigInteger bigInt) {
        int size = bigInt.toByteArray().length;
        if (bigInt.testBit(size * 8)) {
            return ArrayConverter.bigIntegerToByteArray(bigInt, size + 1, true);
        } else {
            return bigInt.toByteArray();
        }
    }

    public static byte[] encodeNull() {
        return new byte[0];
    }

    public static byte[] encodeObjectIdentifier(ObjectIdentifier oid) {
        return oid.getEncoded();
    }

    public static byte[] encodeBitString(byte[] usedBits, Byte unusedBits, Byte padding) {
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

    public static byte[] encodeGeneralizedTime(DateTime date, TimeAccurracy accurracy) {
        return TimeEncoder.encodeGeneralizedTimeLocalTime(date, accurracy).getBytes();
    }

    public static byte[] encodeFullUtcTime(DateTime date, TimeAccurracy accurracy) {
        return TimeEncoder.encodeFullUtc(date, accurracy).getBytes();
    }

    public static byte[] encodeGeneralizedTimeUtc(DateTime date, TimeAccurracy accurracy) {
        return TimeEncoder.encodeGeneralizedTimeUtc(date, accurracy).getBytes();
    }

    public static byte[] encodeIa5String(String tempString) {
        return tempString.getBytes(StandardCharsets.US_ASCII);
    }

    /**
     * Encodes a printable string. We also allow non-printable characters
     *
     * @param tempString the string to encode
     * @return the encoded string
     */
    public static byte[] encodePrintableString(String tempString) {
        return tempString.getBytes(StandardCharsets.US_ASCII);
    }

    /**
     * TODO probably incorrect
     *
     * @param tempString the String to encode
     * @return the string encoded as a bmp string as a byte array
     */
    public static byte[] encodeBmpString(String tempString) {
        return tempString.getBytes(StandardCharsets.US_ASCII);
    }

    /**
     * TODO probably incorrect
     *
     * @param tempString the String to encode
     * @return the string encoded as a universal string as a byte array
     */
    public static byte[] encodeUniversalString(String tempString) {
        return tempString.getBytes(StandardCharsets.US_ASCII);
    }

    public static byte[] encodeOctetString(byte[] bytes) {
        return bytes;
    }

    public static byte[] encodeT61String(String tempString) {
        /** TODO Not sure this is correct... */
        return tempString.getBytes(StandardCharsets.UTF_8);
    }

    public static byte[] encodeUtf8String(String tempString) {
        return tempString.getBytes(StandardCharsets.UTF_8);
    }

    private static byte[] shiftLeft(byte[] input, int n) {
        if (input.length == 0) {
            return input;
        }
        BigInteger tempBigInt = new BigInteger(1, input);
        tempBigInt = tempBigInt.shiftLeft(n);
        return tempBigInt.toByteArray();
    }

    private static byte[] encodeLongTagNumber(int tagNumber) {
        int tagNumberByteCount = getTagNumberByteCount(tagNumber);
        byte[] result = new byte[tagNumberByteCount];
        byte moreFlag = 0x00;
        for (int i = tagNumberByteCount - 1; i >= 0; i--) {
            result[i] = (byte) (moreFlag | (tagNumber & 0x7F));
            tagNumber = tagNumber >> 7;
            moreFlag = (byte) 0x80;
        }
        return result;
    }

    private static int getTagNumberByteCount(int tagNumber) {
        int result = 0;
        while (tagNumber > 0) {
            result++;
            tagNumber = tagNumber >> 7;
        }
        return result;
    }

    private static int getLengthByteCount(BigInteger length) {
        int fullBytes = length.bitLength() / 8;
        if (length.bitLength() % 8 != 0) {
            fullBytes++;
        }
        return fullBytes;
    }

    private static byte[] encodeLength(Asn1Field field, BigInteger contentLength) {
        field.setLength(contentLength);
        BigInteger length = field.getLength().getValue();
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

    private static byte[] encodeLongLength(BigInteger length) {
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

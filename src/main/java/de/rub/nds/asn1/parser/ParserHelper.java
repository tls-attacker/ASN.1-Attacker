/*
 * ASN.1-Attacker - A Library for Arbitrary ASN.1 Structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.UniversalTagNumber;
import de.rub.nds.asn1.model.Asn1BitString;
import de.rub.nds.asn1.model.Asn1Boolean;
import de.rub.nds.asn1.model.Asn1Encodable;
import de.rub.nds.asn1.model.Asn1Field;
import de.rub.nds.asn1.model.Asn1GeneralizedTime;
import de.rub.nds.asn1.model.Asn1Ia5String;
import de.rub.nds.asn1.model.Asn1Integer;
import de.rub.nds.asn1.model.Asn1Null;
import de.rub.nds.asn1.model.Asn1ObjectIdentifier;
import de.rub.nds.asn1.model.Asn1OctetString;
import de.rub.nds.asn1.model.Asn1PrintableString;
import de.rub.nds.asn1.model.Asn1T61String;
import de.rub.nds.asn1.model.Asn1UnknownField;
import de.rub.nds.asn1.model.Asn1UnknownSequence;
import de.rub.nds.asn1.model.Asn1UnknownSet;
import de.rub.nds.asn1.model.Asn1UtcTime;
import de.rub.nds.asn1.model.Asn1Utf8String;
import de.rub.nds.asn1.oid.ObjectIdentifier;
import de.rub.nds.asn1.util.Asn1Header;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import de.rub.nds.protocol.exception.ParserException;
import de.rub.nds.protocol.util.SilentByteArrayOutputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParserHelper {

    private static final Logger LOGGER = LogManager.getLogger();

    private ParserHelper() {}

    /**
     * Parses the next field in the stream as the provided tag number. Parses as unknown if the tag
     * number in the stream mismatches or if the tag number is not implemented. The tagClass has to
     * be universal, otherwise a parser exception is thrown. The parameter is there for a sanity
     * check.
     *
     * @param inputStream the stream to parse from
     * @param tagClass the tag class to parse
     * @param tagNumbers the tag numbers to potentially parse
     * @return the parsed field
     */
    public static Asn1Field parseTagNumberOrUnkownField(
            BufferedInputStream inputStream, TagClass tagClass, UniversalTagNumber... tagNumbers) {
        if (tagNumbers.length == 0) {
            throw new ParserException("No tag numbers provided");
        }
        if (tagClass != TagClass.UNIVERSAL) {
            throw new ParserException("Cannot parse this tag number generically.");
        }
        Asn1Header header = lookAhead(inputStream);
        UniversalTagNumber foundNumber = null;
        if (header.getTagClass() == tagClass) {
            for (UniversalTagNumber tagNumber : tagNumbers) {
                if (tagNumber != null && header.getTagNumber() == tagNumber.getIntValue()) {
                    foundNumber = tagNumber;
                }
            }
        }
        if (foundNumber == null) {
            return parseUnknown(inputStream);
        } else {
            return parseTagNumberField(inputStream, foundNumber);
        }
    }

    /**
     * Strictly parses the next field in the stream as of of the provided tag number. Throws a
     * ParserException if the next tag number is not exepected. If a not implemented tag number is
     * requested an unknown field is parsed.
     *
     * @param inputStream the stream to parse from
     * @param tagClass the tag class to parse
     * @param tagNumbers The tag numbers to parse
     * @return an Asn1Field that was parsed
     */
    public static Asn1Field parseTagNumberField(
            BufferedInputStream inputStream, TagClass tagClass, UniversalTagNumber... tagNumbers) {
        if (tagClass != TagClass.UNIVERSAL) {
            throw new ParserException("Cannot parse this tag number generically.");
        }
        Asn1Header header = lookAhead(inputStream);
        UniversalTagNumber foundNumber = null;
        if (header.getTagClass() == tagClass) {
            for (UniversalTagNumber tagNumber : tagNumbers) {
                if (header.getTagNumber() == tagNumber.getIntValue()) {
                    foundNumber = tagNumber;
                }
            }
        }
        if (foundNumber == null) {
            throw new ParserException(
                    "Unexpected tagNumber. Found: "
                            + header.getTagNumber()
                            + " but expected "
                            + Arrays.toString(tagNumbers));
        } else {
            return parseTagNumberField(inputStream, foundNumber);
        }
    }

    public static Asn1Field parseTagNumberField(
            BufferedInputStream inputStream, UniversalTagNumber tagNumber) {
        switch (tagNumber) {
            case BIT_STRING:
                Asn1BitString bitstring = new Asn1BitString("bitString");
                parseAsn1BitString(bitstring, inputStream);
                return bitstring;
            case BOOLEAN:
                Asn1Boolean asn1Boolean = new Asn1Boolean("boolean");
                parseAsn1Boolean(asn1Boolean, inputStream);
                return asn1Boolean;
            case GENERALIZEDTIME:
                Asn1GeneralizedTime asn1GeneralizedTime =
                        new Asn1GeneralizedTime("generalizedTime");
                parseAsn1GeneralizedTime(asn1GeneralizedTime, inputStream);
                return asn1GeneralizedTime;
            case IA5STRING:
                Asn1Ia5String asn1Ia5String = new Asn1Ia5String("ia5String");
                parseAsn1Ia5String(asn1Ia5String, inputStream);
                return asn1Ia5String;
            case INTEGER:
                Asn1Integer asn1Integer = new Asn1Integer("integer");
                parseAsn1Integer(asn1Integer, inputStream);
                return asn1Integer;
            case NULL:
                Asn1Null asn1Null = new Asn1Null("null");
                parseAsn1Null(asn1Null, inputStream);
                return asn1Null;
            case OCTET_STRING:
                Asn1OctetString asn1OctetString = new Asn1OctetString("octetString");
                parseAsn1OctetString(asn1OctetString, inputStream);
                return asn1OctetString;
            case PRINTABLESTRING:
                Asn1PrintableString asn1PrintableString =
                        new Asn1PrintableString("printableString");
                parseAsn1PrintableString(asn1PrintableString, inputStream);
                return asn1PrintableString;
            case OBJECT_IDENTIFIER:
                Asn1ObjectIdentifier asn1ObjectIdentifier =
                        new Asn1ObjectIdentifier("objectIdentifier");
                parseAsn1ObjectIdentifier(asn1ObjectIdentifier, inputStream);
                return asn1ObjectIdentifier;
            case SEQUENCE:
                Asn1UnknownSequence asn1UnknownSequence = new Asn1UnknownSequence("sequence");
                parseStructure(asn1UnknownSequence, inputStream);
                return asn1UnknownSequence;
            case SET:
                Asn1UnknownSet asn1UnknownSet = new Asn1UnknownSet("set");
                parseStructure(asn1UnknownSet, inputStream);
                return asn1UnknownSet;
            case T61STRING:
                Asn1T61String asn1t61String = new Asn1T61String("t61String");
                parseAsn1T61String(asn1t61String, inputStream);
                return asn1t61String;
            case UTCTIME:
                Asn1UtcTime asn1UtcTime = new Asn1UtcTime("utcTime");
                parseAsn1UtcTime(asn1UtcTime, inputStream);
                return asn1UtcTime;
            case UTF8STRING:
                Asn1Utf8String asn1Utf8String = new Asn1Utf8String("utf8String");
                parseAsn1Utf8String(asn1Utf8String, inputStream);
                return asn1Utf8String;
            default:
                LOGGER.warn(
                        "Could theoretically parse tag number {} but this is not implemented yet. Parsing as unknown.",
                        tagNumber);
                return parseUnknown(inputStream);
        }
    }

    public static Asn1UnknownField parseUnknown(BufferedInputStream inputStream) {
        Asn1Header asn1Header = lookAhead(inputStream);
        Asn1UnknownField unknownField =
                new Asn1UnknownField(
                        "unknown",
                        asn1Header.getTagClass(),
                        asn1Header.getTagConstructed(),
                        asn1Header.getTagNumber());
        parseStructure(unknownField, inputStream);
        return unknownField;
    }

    public static boolean canParse(
            BufferedInputStream inputStream, TagClass tagClass, int tagNumber) {
        Asn1Header header = lookAhead(inputStream);
        if (header == null) {
            return false;
        }
        if (header.getTagClass() != tagClass) {
            return false;
        }
        if (header.getTagNumber() == tagNumber) {
            return true;
        }

        return false;
    }

    /**
     * Look ahead to the next field in the stream.
     *
     * @param inputStream The stream to look ahead in.
     * @return The header of the next field.
     */
    public static Asn1Header lookAhead(BufferedInputStream inputStream) {
        try {
            LOGGER.debug("Looking ahead...");
            if (inputStream.available() == 0) {
                LOGGER.debug("Cannot look ahead, stream is empty");
                return null;
            }
            inputStream.mark(inputStream.available());
            byte[] tagOctets = parseTagOctets(inputStream);
            int tagClass = parseTagClass(tagOctets[0]);
            boolean constructed = parseTagConstructed(tagOctets[0]);
            int parseTagNumber = parseTagNumber(tagOctets);
            byte[] lengthOctets;
            lengthOctets = parseLengthOctets(inputStream);

            BigInteger parseLength = parseLength(lengthOctets);
            inputStream.reset();
            LOGGER.debug("Reset stream. Back to normal");
            return new Asn1Header(
                    TagClass.fromIntValue(tagClass),
                    parseTagNumber,
                    parseLength,
                    TagConstructed.fromBooleanValue(constructed));
        } catch (IOException e) {
            throw new ParserException("Failed to look ahead.", e);
        }
    }

    /**
     * Parses TagOctets, TagClass, TagConstructed, TagNumber, ContentLength and Content octets. Sets
     * the constants in the field and checks that the they match the expected ones. If not a
     * ParserException is thrown. Does not parse the content octets
     *
     * @param field Field where the parsed values should be written to
     * @param stream The stream to read from
     */
    public static void parseStructure(Asn1Field field, BufferedInputStream stream) {
        try {
            field.setTagOctets(parseTagOctets(stream));
            field.setTagClass(parseTagClass(field.getTagOctets().getValue()[0]));
            field.setTagConstructed(parseTagConstructed(field.getTagOctets().getValue()[0]));
            field.setTagNumber(parseTagNumber(field.getTagOctets().getValue()));
            field.setLengthOctets(parseLengthOctets(stream));
            field.setLength(parseLength(field.getLengthOctets().getValue()));
            field.setContent(parseContentOctets(field.getLength().getValue(), stream));
            validateConstants(field);
        } catch (IOException ex) {
            throw new ParserException(ex);
        }
    }

    /**
     * Parses an ASN.1 Boolean field. Its structure and its content
     *
     * @param asn1Boolean the Asn1Boolean to write the parsed values into
     * @param inputStream the InputStream to read the boolean from
     */
    public static void parseAsn1Boolean(Asn1Boolean asn1Boolean, BufferedInputStream inputStream) {
        parseStructure(asn1Boolean, inputStream);
        parseBooleanContent(asn1Boolean);
    }

    /**
     * Parses an ASN.1 Integer field. Its structure and its content
     *
     * @param asn1Integer the Asn1Integer to write the parsed values into
     * @param inputStream the InputStream to read the integer from
     */
    public static void parseAsn1Integer(Asn1Integer asn1Integer, BufferedInputStream inputStream) {
        parseStructure(asn1Integer, inputStream);
        parseIntegerContent(asn1Integer);
    }

    /**
     * Parses an ASN.1 Null field. Its structure and its content
     *
     * @param asn1Null the Asn1Null to write the parsed values into
     * @param inputStream the InputStream to read the null field from
     */
    public static void parseAsn1Null(Asn1Null asn1Null, BufferedInputStream inputStream) {
        parseStructure(asn1Null, inputStream);
        parseNullContent(asn1Null);
    }

    /**
     * Parses an ASN.1 ObjectIdentifier field. Its structure and its content
     *
     * @param asn1ObjectIdentifier the Asn1ObjectIdentifier to write the parsed values into
     * @param inputStream the InputStream to read the object identifier from
     */
    public static void parseAsn1ObjectIdentifier(
            Asn1ObjectIdentifier asn1ObjectIdentifier, BufferedInputStream inputStream) {
        parseStructure(asn1ObjectIdentifier, inputStream);
        parseAsn1ObjectIdentifierContent(asn1ObjectIdentifier);
    }

    /**
     * Parses an ASN.1 BitString field. Its structure and its content
     *
     * @param asn1BitString the Asn1BitString to write the parsed values into
     * @param inputStream the inputstream to read the bitstring from
     */
    public static void parseAsn1BitString(
            Asn1BitString asn1BitString, BufferedInputStream inputStream) {
        parseStructure(asn1BitString, inputStream);
        parseBitStringContent(asn1BitString);
    }

    /**
     * Parses an ASN.1 GeneralizedTime field. Its structure and its content
     *
     * @param asn1GeneralizedTime the Asn1GeneralizedTime to write the parsed values into
     * @param inputStream the inputstream to read the generalized time from
     */
    public static void parseAsn1GeneralizedTime(
            Asn1GeneralizedTime asn1GeneralizedTime, BufferedInputStream inputStream) {
        parseStructure(asn1GeneralizedTime, inputStream);
        parseGeneralizedTimeContent(asn1GeneralizedTime);
    }

    /**
     * Parses an ASN.1 IA5 String field. Its structure and its content
     *
     * @param asn1Ia5String the Asn1Ia5String to write the parsed values into
     * @param inputStream the inputstream to read the ia5 string from
     */
    public static void parseAsn1Ia5String(
            Asn1Ia5String asn1Ia5String, BufferedInputStream inputStream) {
        parseStructure(asn1Ia5String, inputStream);
        parseIa5StringContent(asn1Ia5String);
    }

    /**
     * Parses an ASN.1 OctetString field. Its structure and its content
     *
     * @param asn1OctetString the Asn1OctetString to write the parsed values into
     * @param inputStream the InputStream to read the octet string from
     */
    public static void parseAsn1OctetString(
            Asn1OctetString asn1OctetString, BufferedInputStream inputStream) {
        parseStructure(asn1OctetString, inputStream);
        parseOctetStringContent(asn1OctetString);
    }

    /**
     * Parses an ASN.1 PrintableString field. Its structure and its content
     *
     * @param asn1PrintableString the Asn1PrintableString to write the parsed values into
     * @param inputStream the InputStream to read the printable string from
     */
    public static void parseAsn1PrintableString(
            Asn1PrintableString asn1PrintableString, BufferedInputStream inputStream) {
        parseStructure(asn1PrintableString, inputStream);
        parsePrintableStringContent(asn1PrintableString);
    }

    /**
     * Parses an ASN.1 T61String field. Its structure and its content
     *
     * @param asn1T61String the Asn1T61String to write the parsed values into
     * @param inputStream the InputStream to read the T61 string from
     */
    public static void parseAsn1T61String(
            Asn1T61String asn1T61String, BufferedInputStream inputStream) {
        parseStructure(asn1T61String, inputStream);
        parseT61StringContent(asn1T61String);
    }

    /**
     * Parses an ASN.1 UTC Time field. Its structure and its content
     *
     * @param asn1UtcTime the Asn1UtcTime to write the parsed values into
     * @param inputStream the InputStream to read the utc time field from
     */
    public static void parseAsn1UtcTime(Asn1UtcTime asn1UtcTime, BufferedInputStream inputStream) {
        parseStructure(asn1UtcTime, inputStream);
        parseUtcTimeContent(asn1UtcTime);
    }

    /**
     * Parses an Utf8String field. Its structure and its content
     *
     * @param asn1Utf8String the Asn1Utf8String to write the parsed values into
     * @param inputStream the InputStream to read the utf8 string from
     */
    public static void parseAsn1Utf8String(
            Asn1Utf8String asn1Utf8String, BufferedInputStream inputStream) {
        parseStructure(asn1Utf8String, inputStream);
        parseUtf8StringContent(asn1Utf8String);
    }

    public static void parseAsn1ObjectIdentifierContent(Asn1ObjectIdentifier asn1ObjectIdentifier) {
        ObjectIdentifier oid = new ObjectIdentifier(asn1ObjectIdentifier.getContent().getValue());
        asn1ObjectIdentifier.setValue(oid.toString());
    }

    public static void parseNullContent(Asn1Null asn1Null) {
        if (asn1Null.getContent().getValue().length > 0) {
            throw new ParserException("NullField contains data");
        }
    }

    public static void parseIntegerContent(Asn1Integer asn1Integer) {
        asn1Integer.setValue(new BigInteger(asn1Integer.getContent().getValue()));
    }

    public static void parseBooleanContent(Asn1Boolean asn1Boolean) {
        if (asn1Boolean.getContent().getValue().length != 1) {
            throw new ParserException(
                    "ASN.1 boolean has incorrect size. Expected \'1\' but found "
                            + asn1Boolean.getContent().getValue().length);
        }
        if (asn1Boolean.getContent().getValue()[0] == (byte) 0xFF) {
            asn1Boolean.setValue(true);
        } else if (asn1Boolean.getContent().getValue()[0] == (byte) 0x00) {
            asn1Boolean.setValue(false);
        } else {
            LOGGER.warn("asn1Boolean is not DER encoded. Assuming \"false\".");
            asn1Boolean.setValue(false);
        }
    }

    public static void parseBitStringContent(Asn1BitString asn1BitString) {
        if (asn1BitString.getContent().getValue().length == 0) {
            throw new ParserException("No content in Asn1PrimitiveBitString");
        }
        InputStream inputStream = new ByteArrayInputStream(asn1BitString.getContent().getValue());
        try {
            asn1BitString.setUnusedBits((byte) inputStream.read());
            byte[] remainingBytes;
            remainingBytes =
                    inputStream.readNBytes(asn1BitString.getLength().getValue().intValue() - 1);

            LOGGER.debug("Unused bits: {}", asn1BitString.getUnusedBits().getValue());
            LOGGER.debug("Remaining bytes: {}", ArrayConverter.bytesToHexString(remainingBytes));

            asn1BitString.setUsedBits(
                    shiftRightUnsigned(remainingBytes, asn1BitString.getUnusedBits().getValue()));
            asn1BitString.setPadding(
                    extractBits(remainingBytes, asn1BitString.getUnusedBits().getValue()));

            LOGGER.debug(
                    "Used bits: {}",
                    ArrayConverter.bytesToHexString(asn1BitString.getUsedBits().getValue()));
            LOGGER.debug(
                    "Padding: {}",
                    ArrayConverter.bytesToHexString(
                            new byte[] {asn1BitString.getPadding().getValue()}));
        } catch (IOException e) {
            throw new ParserException("Could not parse BitString", e);
        }
    }

    public static void parseGeneralizedTimeContent(Asn1GeneralizedTime asn1GeneralizedTime) {
        asn1GeneralizedTime.setValue(new String(asn1GeneralizedTime.getContent().getValue()));
    }

    public static void parseIa5StringContent(Asn1Ia5String asn1Ia5String) {
        asn1Ia5String.setValue(new String(asn1Ia5String.getContent().getValue()));
    }

    public static void parseOctetStringContent(Asn1OctetString asn1OctetString) {
        asn1OctetString.setValue(asn1OctetString.getContent().getValue());
    }

    public static void parsePrintableStringContent(Asn1PrintableString asn1PrintableString) {
        asn1PrintableString.setValue(new String(asn1PrintableString.getContent().getValue()));
    }

    public static void parseT61StringContent(Asn1T61String asn1t61String) {
        asn1t61String.setValue(new String(asn1t61String.getContent().getValue()));
    }

    public static void parseUtcTimeContent(Asn1UtcTime asn1UtcTime) {
        asn1UtcTime.setValue(new String(asn1UtcTime.getContent().getValue()));
    }

    public static void parseUtf8StringContent(Asn1Utf8String asn1Utf8String) {
        asn1Utf8String.setValue(new String(asn1Utf8String.getContent().getValue()));
    }

    private static byte extractBits(byte[] input, int n) {
        if (input.length == 0) {
            return 0;
        }
        if (n > 8) {
            throw new IllegalArgumentException("n must be between 0 and 8, inclusive");
        }
        int mask = (1 << n) - 1;
        return (byte) (input[input.length - 1] & mask);
    }

    private static byte[] shiftRightUnsigned(byte[] array, int n) {
        if (array.length == 0) {
            return array;
        }

        // forces BigInt to always parse the array as unsigned
        byte[] paddedArray = new byte[array.length + 1];
        paddedArray[0] = 0;
        System.arraycopy(array, 0, paddedArray, 1, array.length);

        BigInteger bigInt = new BigInteger(paddedArray);
        BigInteger shiftInt = bigInt.shiftRight(n);
        byte[] shiftedArray = shiftInt.toByteArray();
        // return as many bytes as the original array
        byte[] result = new byte[array.length];
        System.arraycopy(shiftedArray, shiftedArray.length - array.length, result, 0, array.length);
        return result;
    }

    public static byte[] parseTagOctets(BufferedInputStream stream) throws IOException {
        if (stream.available() == 0) {
            throw new ParserException("Cannot read from empty stream");
        }
        int read = stream.read();
        if ((read & 0x1F) == 0x1F) {
            // Long tag
            SilentByteArrayOutputStream tagByteStream = new SilentByteArrayOutputStream();
            tagByteStream.write(read);
            do {
                if (stream.available() == 0) {
                    throw new ParserException(
                            "Incomplete tag: "
                                    + ArrayConverter.bytesToHexString(tagByteStream.toByteArray()));
                }
                read = stream.read();
                if (read == -1) {
                    throw new ParserException(
                            "Incomplete tag: "
                                    + ArrayConverter.bytesToHexString(tagByteStream.toByteArray()));
                }
                tagByteStream.write(read);
            } while ((read & 0x80) > 0);
            LOGGER.debug("Parsed (long) tag octets: {}", tagByteStream.toByteArray());
            return tagByteStream.toByteArray();
        } else {
            // Short tag
            byte[] tag = new byte[] {(byte) read};
            LOGGER.debug("Parsed short tag octets: {}", tag);
            return tag;
        }
    }

    public static int parseTagClass(byte firstTagByte) {
        return (firstTagByte >> 6) & 0x03;
    }

    public static boolean parseTagConstructed(byte firstTagByte) {
        return ((firstTagByte >> 5) & 0x01) != 0;
    }

    public static int parseTagNumber(byte[] encodedTag) {
        if (encodedTag.length == 1) {
            return encodedTag[0] & 0x1F;
        }
        int tagNumber = 0;
        byte nextByte;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(encodedTag);
        do {
            nextByte = (byte) (inputStream.read() & 0xFF);
            tagNumber = (tagNumber << 7) | (nextByte & 0x7F);
        } while ((nextByte & 0x80) > 0 && inputStream.available() > 0);
        return tagNumber;
    }

    public static BigInteger parseLength(byte[] lengthOctets) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(lengthOctets);
        BigInteger length = BigInteger.ZERO;
        byte lengthByte;
        lengthByte = (byte) (inputStream.read() & 0xFF);
        if (lengthByte == (byte) 0x80) {
            throw new ParserException("Indefinite lengths are currently not supported!");
        }
        if (lengthByte == (byte) 0xFF) {
            throw new ParserException("Reserved length value!");
        }
        if ((lengthByte & 0xFF) < 128) {
            length = BigInteger.valueOf(lengthByte & 0xFF);
        } else {
            int numberOfLengthBytes = (lengthByte & 0x7F);
            if (inputStream.available() != numberOfLengthBytes) {
                throw new ParserException("Length octets have incorrect length");
            }
            for (int i = 0; i < numberOfLengthBytes; i++) {
                length = length.shiftLeft(8);
                length = length.or(BigInteger.valueOf(inputStream.read() & 0xFF));
            }
        }
        return length;
    }

    public static byte[] parseLengthOctets(BufferedInputStream inputStream)
            throws ParserException, IOException {
        SilentByteArrayOutputStream outputStream = new SilentByteArrayOutputStream();
        byte lengthByte;
        try {
            lengthByte = (byte) (inputStream.read() & 0xFF);
            outputStream.write(lengthByte & 0xFF);
        } catch (IOException ex) {
            throw new ParserException(ex);
        }
        if (lengthByte == (byte) 0x80) {
            throw new ParserException("Indefinite lengths are currently not supported!");
        }
        if (lengthByte == (byte) 0xFF) {
            throw new ParserException("Reserved length value!");
        }
        if ((lengthByte & 0xFF) < 128) {
            LOGGER.debug("Parsed (short) length octets: {}", outputStream.toByteArray());
            return outputStream.toByteArray();
        } else {
            int numberOfLengthBytes = (lengthByte & 0x7F);
            if (numberOfLengthBytes > inputStream.available()) {
                throw new ParserException("Not enough bytes for length octets in stream");
            }
            for (int i = 0; i < numberOfLengthBytes; i++) {
                try {
                    outputStream.write(inputStream.read());
                } catch (IOException ex) {
                    throw new ParserException(ex);
                }
            }
            LOGGER.debug("Parsed (long) length octets: {}", outputStream.toByteArray());
            return outputStream.toByteArray();
        }
    }

    public static byte[] parseContentOctets(BigInteger length, BufferedInputStream inputStream)
            throws IOException {
        if (inputStream.available() < length.intValue()) {
            throw new ParserException("Not enough bytes in stream");
        }
        byte[] octets = inputStream.readNBytes(length.intValue());
        LOGGER.debug("Parsed content octets: {}", octets);
        return octets;
    }

    /**
     * Throws a ParserException if the header does not match the expected one
     *
     * @param field the field for which the constants should be validated
     */
    public static void validateConstants(Asn1Field field) {
        if (field.getTagClassType() != null
                && field.getTagClassType().getIntValue() != field.getTagClass().getValue()) {
            TagClass foundTagClass = TagClass.fromIntValue(field.getTagClass().getValue());
            throw new ParserException(
                    "TagClassType did not match expectations expected "
                            + field.getTagClassType().name()
                            + "("
                            + field.getTagClassType().getIntValue()
                            + ") but found "
                            + (foundTagClass == null ? "???" : foundTagClass.name())
                            + " ("
                            + field.getTagClass().getValue()
                            + ")");
        }
        if (field.getTagConstructedType() != null
                && field.getTagConstructedType().getBooleanValue()
                        != field.getTagConstructed().getValue()) {
            TagConstructed foundTagConstructed =
                    TagConstructed.fromBooleanValue(field.getTagConstructed().getValue());
            throw new ParserException(
                    "TagConstructedType did not match expectations expected "
                            + field.getTagConstructedType().name()
                            + "("
                            + field.getTagConstructedType().getBooleanValue()
                            + ") but found "
                            + (foundTagConstructed == null ? "???" : foundTagConstructed.name())
                            + " ("
                            + field.getTagConstructed().getValue()
                            + ")");
        }
        if (field.getUniversalTagNumberType() != null
                && !Objects.equals(
                        field.getUniversalTagNumberType().getIntValue(),
                        field.getTagNumber().getValue())) {
            UniversalTagNumber foundTagNumber =
                    UniversalTagNumber.fromIntValue(field.getTagNumber().getValue());
            throw new ParserException(
                    "TagNumber did not match expectations expected "
                            + field.getUniversalTagNumberType().name()
                            + "("
                            + field.getUniversalTagNumberType().getIntValue()
                            + ") but found "
                            + (foundTagNumber == null ? "???" : foundTagNumber.name())
                            + " ("
                            + field.getTagNumber().getValue()
                            + ") for "
                            + field.getIdentifier());
        }
    }

    public static void parseGenericField(Asn1Encodable encodable, BufferedInputStream inputStream) {
        if (encodable instanceof Asn1Integer) {
            parseAsn1Integer((Asn1Integer) encodable, inputStream);
        } else if (encodable instanceof Asn1BitString) {
            parseAsn1BitString((Asn1BitString) encodable, inputStream);
        } else if (encodable instanceof Asn1Boolean) {
            parseAsn1Boolean((Asn1Boolean) encodable, inputStream);
        } else if (encodable instanceof Asn1GeneralizedTime) {
            parseAsn1GeneralizedTime((Asn1GeneralizedTime) encodable, inputStream);
        } else if (encodable instanceof Asn1Ia5String) {
            parseAsn1Ia5String((Asn1Ia5String) encodable, inputStream);
        } else if (encodable instanceof Asn1Null) {
            parseAsn1Null((Asn1Null) encodable, inputStream);
        } else if (encodable instanceof Asn1ObjectIdentifier) {
            parseAsn1ObjectIdentifier((Asn1ObjectIdentifier) encodable, inputStream);
        } else if (encodable instanceof Asn1OctetString) {
            parseAsn1OctetString((Asn1OctetString) encodable, inputStream);
        } else if (encodable instanceof Asn1PrintableString) {
            parseAsn1PrintableString((Asn1PrintableString) encodable, inputStream);
        } else if (encodable instanceof Asn1T61String) {
            parseAsn1T61String((Asn1T61String) encodable, inputStream);
        } else if (encodable instanceof Asn1UtcTime) {
            parseAsn1UtcTime((Asn1UtcTime) encodable, inputStream);
        } else if (encodable instanceof Asn1Utf8String) {
            parseAsn1Utf8String((Asn1Utf8String) encodable, inputStream);
        } else {
            parseStructure((Asn1Field) encodable, inputStream);
        }
    }
}

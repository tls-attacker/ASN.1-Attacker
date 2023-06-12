/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.TagNumber;
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
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Asn1FieldParser<Field extends Asn1Field> extends Asn1Parser<Field> {

    private static final Logger LOGGER = LogManager.getLogger();

    public Asn1FieldParser(Field field) {
        super(field);
    }

    private void setConstants(Asn1Field field) {
        if (field.getTagClassType() == null) {
            field.setTagClassType(TagClass.fromIntValue(field.getTagClass().getValue()));
        }
        if (field.getTagConstructedType() != null) {
            field.setTagConstructedType(
                    TagConstructed.fromBooleanValue(field.getTagConstructed().getValue()));
        }
        if (field.getTagNumberType() == null) {
            field.setTagNumberType(TagNumber.fromIntValue(field.getTagNumber().getValue()));
        }
    }

    private boolean areConstantsValid(Asn1Field field) {
        if (field.getTagClassType() != null
                && field.getTagClassType().getIntValue() != field.getTagClass().getValue()) {
            TagClass foundTagClass = TagClass.fromIntValue(field.getTagClass().getValue());
            LOGGER.warn(
                    "TagClassType did not match expectations expected "
                            + field.getTagClassType().name()
                            + "("
                            + field.getTagClassType().getIntValue()
                            + ") but found "
                            + (foundTagClass == null ? "???" : foundTagClass.name())
                            + " ("
                            + field.getTagClass().getValue()
                            + ")");
            return false;
        }
        if (field.getTagConstructedType() != null
                && field.getTagConstructedType().getBooleanValue()
                        != field.getTagConstructed().getValue()) {
            TagConstructed foundTagConstructed =
                    TagConstructed.fromBooleanValue(field.getTagConstructed().getValue());
            LOGGER.warn(
                    "TagConstructedType did not match expectations expected "
                            + field.getTagConstructedType().name()
                            + "("
                            + field.getTagConstructedType().getBooleanValue()
                            + ") but found "
                            + (foundTagConstructed == null ? "???" : foundTagConstructed.name())
                            + " ("
                            + field.getTagConstructed().getValue()
                            + ")");
            return false;
        }
        if (field.getTagNumberType() != null
                && !Objects.equals(
                        field.getTagNumberType().getIntValue(), field.getTagNumber().getValue())) {
            TagNumber foundTagNumber = TagNumber.fromIntValue(field.getTagNumber().getValue());
            LOGGER.warn(
                    "TagNumber did not match expectations expected "
                            + field.getTagNumberType().name()
                            + "("
                            + field.getTagNumberType().getIntValue()
                            + ") but found "
                            + (foundTagNumber == null ? "???" : foundTagNumber.name())
                            + " ("
                            + field.getTagNumber().getValue()
                            + ")");
            return false;
        }
        return true;
    }

    /**
     * Parses TagOctets, TagClass, TagConstructed, TagNumber, ContentLength and Content octets. Sets
     * the constants in the field and checks that the they match the expected ones. If not a
     * ParserException is thrown. Does not parse the content octets
     *
     * @param field
     * @param stream
     */
    protected void parseStructure(Asn1Field field, InputStream stream) {
        try {
            field.setTagOctets(this.parseTagOctets(stream));
            field.setTagClass(this.parseTagClass(field.getTagOctets().getValue()[0]));
            field.setTagConstructed(this.parseTagConstructed(field.getTagOctets().getValue()[0]));
            field.setTagNumber(this.parseTagNumber(field.getTagOctets().getValue()));
            field.setLengthOctets(this.parseLengthOctets(stream));
            field.setLength(this.parseLength(field.getLengthOctets().getValue()));
            field.setContent(this.parseContentOctets(field.getLength().getValue(), stream));
            setConstants(field);
            if (!areConstantsValid(field)) {
                throw new ParserException("Expectation missmatch");
            }
        } catch (IOException ex) {
            throw new ParserException(ex);
        }
    }

    /**
     * Parses an ASN.1 Boolean field. Its structure and its content
     *
     * @param asn1Boolean
     * @param inputStream
     */
    protected void parseAsn1Boolean(Asn1Boolean asn1Boolean, InputStream inputStream) {
        parseStructure(asn1Boolean, inputStream);
        parseBooleanContent(asn1Boolean);
    }

    /**
     * Parses an ASN.1 Integer field. Its structure and its content
     *
     * @param asn1Integer
     * @param inputStream
     */
    protected void parseAsn1Integer(Asn1Integer asn1Integer, InputStream inputStream) {
        parseStructure(asn1Integer, inputStream);
        parseIntegerContent(asn1Integer);
    }

    /**
     * Parses an ASN.1 Null field. Its structure and its content
     *
     * @param asn1Null
     * @param inputStream
     */
    protected void parseAsn1Null(Asn1Null asn1Null, InputStream inputStream) {
        parseStructure(asn1Null, inputStream);
        parseNullContent(asn1Null);
    }

    /**
     * Parses an ASN.1 ObjectIdentifier field. Its structure and its content
     *
     * @param asn1ObjectIdentifier
     * @param inputStream
     */
    protected void parseAsn1ObjectIdentifier(
            Asn1ObjectIdentifier asn1ObjectIdentifier, InputStream inputStream) {
        parseStructure(asn1ObjectIdentifier, inputStream);
        parseAsn1ObjectIdentifierContent(asn1ObjectIdentifier);
    }

    /**
     * Parses an ASN.1 BitString field. Its structure and its content
     *
     * @param asn1BitString
     * @param inputStream
     */
    protected void parseAsn1BitString(Asn1BitString asn1BitString, InputStream inputStream) {
        parseStructure(asn1BitString, inputStream);
        parseBitStringContent(asn1BitString);
    }

    /**
     * Parses an ASN.1 GeneralizedTime field. Its structure and its content
     *
     * @param asn1GeneralizedTime
     * @param inputStream
     */
    protected void parseAsn1GeneralizedTime(
            Asn1GeneralizedTime asn1GeneralizedTime, InputStream inputStream) {
        parseStructure(asn1GeneralizedTime, inputStream);
        parseGeneralizedTimeContent(asn1GeneralizedTime);
    }

    /**
     * Parses an ASN.1 IA5 String field. Its structure and its content
     *
     * @param asn1Ia5String
     * @param inputStream
     * @throws IOException
     */
    protected void parseAsn1Ia5String(Asn1Ia5String asn1Ia5String, InputStream inputStream) {
        parseStructure(asn1Ia5String, inputStream);
        parseIa5StringContent(asn1Ia5String);
    }

    /**
     * Parses an ASN.1 OctetString field. Its structure and its content
     *
     * @param asn1OctetString
     * @param inputStream
     */
    protected void parseAsn1OctetString(Asn1OctetString asn1OctetString, InputStream inputStream) {
        parseStructure(asn1OctetString, inputStream);
        parseOctetStringContent(asn1OctetString);
    }

    /**
     * Parses an ASN.1 PrintableString field. Its structure and its content
     *
     * @param asn1PrintableString
     * @param inputStream
     * @throws IOException
     */
    protected void parseAsn1PrintableString(
            Asn1PrintableString asn1PrintableString, InputStream inputStream) {
        parseStructure(asn1PrintableString, inputStream);
        parsePrintableStringContent(asn1PrintableString);
    }

    /**
     * Parses an ASN.1 T61String field. Its structure and its content
     *
     * @param asn1T61String
     * @param inputStream
     */
    protected void parseAsn1T61String(Asn1T61String asn1T61String, InputStream inputStream) {
        parseStructure(asn1T61String, inputStream);
        parseT61StringContent(asn1T61String);
    }

    /**
     * Parses an ASN.1 UTC Time field. Its structure and its content
     *
     * @param asn1UtcTime
     * @param inputStream
     */
    protected void parseAsn1UtcTime(Asn1UtcTime asn1UtcTime, InputStream inputStream) {
        parseStructure(asn1UtcTime, inputStream);
        parseUtcTimeContent(asn1UtcTime);
    }

    /**
     * Parses an Utf8String field. Its structure and its content
     *
     * @param asn1Utf8String
     * @param inputStream
     */
    protected void parseAsn1Utf8String(Asn1Utf8String asn1Utf8String, InputStream inputStream) {
        parseStructure(asn1Utf8String, inputStream);
        parseUtf8StringContent(asn1Utf8String);
    }

    private void parseAsn1ObjectIdentifierContent(Asn1ObjectIdentifier asn1ObjectIdentifier) {
        ObjectIdentifier oid = new ObjectIdentifier(asn1ObjectIdentifier.getContent().getValue());
        asn1ObjectIdentifier.setValue(oid.toString());
    }

    private void parseNullContent(Asn1Null asn1Null) {
        if (asn1Null.getContent().getValue().length > 0) {
            throw new ParserException("NullField contains data");
        }
    }

    private void parseIntegerContent(Asn1Integer asn1Integer) {
        asn1Integer.setValue(new BigInteger(asn1Integer.getContent().getValue()));
    }

    private void parseBooleanContent(Asn1Boolean asn1Boolean) {
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

    private void parseBitStringContent(Asn1BitString asn1BitString) {
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
                    shiftRight(remainingBytes, asn1BitString.getUnusedBits().getValue()));
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

    private void parseGeneralizedTimeContent(Asn1GeneralizedTime asn1GeneralizedTime) {
        asn1GeneralizedTime.setValue(new String(asn1GeneralizedTime.getContent().getValue()));
    }

    private void parseIa5StringContent(Asn1Ia5String asn1Ia5String) {
        asn1Ia5String.setValue(new String(asn1Ia5String.getContent().getValue()));
    }

    private void parseOctetStringContent(Asn1OctetString asn1OctetString) {
        asn1OctetString.setValue(asn1OctetString.getContent().getValue());
    }

    private void parsePrintableStringContent(Asn1PrintableString asn1PrintableString) {
        asn1PrintableString.setValue(new String(asn1PrintableString.getContent().getValue()));
    }

    private void parseT61StringContent(Asn1T61String asn1t61String) {
        asn1t61String.setValue(new String(asn1t61String.getContent().getValue()));
    }

    private void parseUtcTimeContent(Asn1UtcTime asn1UtcTime) {
        asn1UtcTime.setValue(new String(asn1UtcTime.getContent().getValue()));
    }

    private void parseUtf8StringContent(Asn1Utf8String asn1Utf8String) {
        asn1Utf8String.setValue(new String(asn1Utf8String.getContent().getValue()));
    }

    private byte extractBits(byte[] input, int n) {
        if (input.length == 0) {
            return 0;
        }
        if (n > 8) {
            throw new IllegalArgumentException("n must be between 0 and 8, inclusive");
        }
        int mask = (1 << n) - 1;
        return (byte) (input[input.length - 1] & mask);
    }

    private byte[] shiftRight(byte[] array, int n) {
        if (array.length == 0) {
            return array;
        }
        BigInteger bigInt = new BigInteger(array);
        BigInteger shiftInt = bigInt.shiftRight(n);
        return shiftInt.toByteArray();
    }
}

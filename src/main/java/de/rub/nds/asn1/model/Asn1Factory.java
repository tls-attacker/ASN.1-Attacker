/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.TagNumber;

public class Asn1Factory {

    private Asn1Factory() {}

    public static Asn1Field createAsn1Element(
            int tagClassValue, boolean constructedValue, int tagNumberValue) {
        TagClass tagClass = TagClass.fromIntValue(tagClassValue);
        TagConstructed tagConstructed = TagConstructed.fromBooleanValue(constructedValue);
        TagNumber tagNumber = TagNumber.fromIntValue(tagNumberValue);
        if (tagClass != TagClass.UNIVERSAL || tagNumber == null) {
            return new Asn1UnknownField("unknownField");
        }
        switch (tagNumber) {
            case BIT_STRING:
                if (tagConstructed == TagConstructed.PRIMITIVE) {
                    return new Asn1PrimitiveBitString("field");
                } else {
                    throw new UnsupportedOperationException(
                            "Constructed Asn1BitStrings not supported");
                }
            case BMPSTRING:
                throw new UnsupportedOperationException("Asn1BmpString not supported");
            case BOOLEAN:
                if (tagConstructed == TagConstructed.PRIMITIVE) {
                    return new Asn1Boolean("field");
                } else {
                    throw new UnsupportedOperationException(
                            "Constructed Asn1Boolean not supported");
                }
            case CHARACTER_STRING:
                throw new UnsupportedOperationException(
                        "Constructed Asn1CharacterString not supported");
            case EMBEDDED_PDV:
                throw new UnsupportedOperationException(
                        "Constructed Asn1EmbeddedPdv not supported");
            case END_OF_CONTENT:
                throw new UnsupportedOperationException(
                        "Constructed Asn1EndOfContent not supported");
            case ENUMERATED:
                if (tagConstructed == TagConstructed.PRIMITIVE) {
                    return new Asn1Enumerated("field");
                } else {
                    throw new UnsupportedOperationException(
                            "Constructed Asn1Enumerated not supported");
                }
            case EXTERNAL:
                throw new UnsupportedOperationException("Constructed Asn1External not supported");
            case GENERALIZEDTIME:
                if (tagConstructed == TagConstructed.PRIMITIVE) {
                    return new Asn1PrimitiveGeneralizedTime("field");
                } else {
                    throw new UnsupportedOperationException(
                            "Constructed Asn1GeneralizedTime not supported");
                }
            case GENERALSTRING:
                throw new UnsupportedOperationException(
                        "Constructed Asn1GeneralString not supported");
            case GRAPHICSTRING:
                throw new UnsupportedOperationException(
                        "Constructed Asn1GraphicString not supported");
            case IA5STRING:
                if (tagConstructed == TagConstructed.PRIMITIVE) {
                    return new Asn1PrimitiveIa5String("field");
                } else {
                    throw new UnsupportedOperationException(
                            "Constructed Asn1PrimitiveIa5String not supported");
                }
            case INTEGER:
                if (tagConstructed == TagConstructed.PRIMITIVE) {
                    return new Asn1Integer("field");
                } else {
                    throw new UnsupportedOperationException(
                            "Constructed Asn1Integer not supported");
                }
            case NULL:
                if (tagConstructed == TagConstructed.PRIMITIVE) {
                    return new Asn1Null("field");
                } else {
                    throw new UnsupportedOperationException("Constructed Asn1Null not supported");
                }
            case NUMERICSTRING:
                throw new UnsupportedOperationException(
                        "Constructed Asn1NumericString not supported");
            case OBJECT_DESCRIPTOR:
                throw new UnsupportedOperationException(
                        "Constructed Asn1ObjectDescriptor not supported");
            case OBJECT_IDENTIFIER:
                if (tagConstructed == TagConstructed.PRIMITIVE) {
                    return new Asn1ObjectIdentifier("field");
                } else {
                    throw new UnsupportedOperationException(
                            "Constructed Asn1ObjectIdentifier not supported");
                }
            case OCTET_STRING:
                if (tagConstructed == TagConstructed.PRIMITIVE) {
                    return new Asn1PrimitiveOctetString("field");
                } else {
                    throw new UnsupportedOperationException(
                            "Constructed Asn1PrimitiveOctetString not supported");
                }
            case PRINTABLESTRING:
                if (tagConstructed == TagConstructed.PRIMITIVE) {
                    return new Asn1PrimitivePrintableString("field");
                } else {
                    throw new UnsupportedOperationException(
                            "Constructed Asn1PrimitiveOctetString not supported");
                }
            case REAL:
                throw new UnsupportedOperationException("Constructed Asn1Real not supported");
            case RELATIVE_OID:
                throw new UnsupportedOperationException(
                        "Constructed Asn1RelativeOid not supported");
            case SEQUENCE:
                if (tagConstructed == TagConstructed.PRIMITIVE) {
                    return new GenericAsn1Sequence("field");
                } else {
                    throw new UnsupportedOperationException(
                            "Constructed Asn1Sequence not supported");
                }
            case SET:
                if (tagConstructed == TagConstructed.PRIMITIVE) {
                    return new GenericAsn1Set("field");
                } else {
                    throw new UnsupportedOperationException("Constructed Asn1Set not supported");
                }
            case T61STRING:
                if (tagConstructed == TagConstructed.PRIMITIVE) {
                    return new Asn1PrimitiveT61String("field");
                } else {
                    throw new UnsupportedOperationException(
                            "Constructed Asn1PrimitiveT61String not supported");
                }
            case UNIVERSALSTRING:
                throw new UnsupportedOperationException(
                        "Constructed Asn1UniversalString not supported");
            case UTCTIME:
                if (tagConstructed == TagConstructed.PRIMITIVE) {
                    return new Asn1PrimitiveUtcTime("field");
                } else {
                    throw new UnsupportedOperationException(
                            "Constructed Asn1PrimitiveUtcTime not supported");
                }
            case UTF8STRING:
                if (tagConstructed == TagConstructed.PRIMITIVE) {
                    return new Asn1PrimitiveUtf8String("field");
                } else {
                    throw new UnsupportedOperationException(
                            "Constructed Asn1PrimitiveUtf8String not supported");
                }
            case VIDEOTEXSTRING:
                throw new UnsupportedOperationException(
                        "Constructed Asn1VideoTexString not supported");
            case VISIBLESTRING:
                throw new UnsupportedOperationException(
                        "Constructed Asn1VisibleString not supported");
            default:
                return new Asn1UnknownField("unknownField");
        }
    }
}

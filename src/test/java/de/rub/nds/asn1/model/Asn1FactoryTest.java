/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import static org.junit.jupiter.api.Assertions.*;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.TagNumber;
import org.junit.jupiter.api.Test;

public class Asn1FactoryTest {

    @Test
    public void testCreateAsn1Element() {
        // Test Unsupported
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.BIT_STRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.BMPSTRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.BOOLEAN.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.CHARACTER_STRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.EMBEDDED_PDV.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.END_OF_CONTENT.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.ENUMERATED.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.EXTERNAL.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.GENERALIZEDTIME.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.GENERALSTRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.GRAPHICSTRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.IA5STRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.INTEGER.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.NULL.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.NUMERICSTRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.OBJECT_DESCRIPTOR.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.OBJECT_IDENTIFIER.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.OCTET_STRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.PRINTABLESTRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.REAL.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.RELATIVE_OID.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.T61STRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.UNIVERSALSTRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.UTCTIME.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.UTF8STRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.VIDEOTEXSTRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Asn1Factory.createAsn1Element(
                            TagClass.UNIVERSAL.getIntValue(),
                            TagConstructed.CONSTRUCTED.getBooleanValue(),
                            TagNumber.VISIBLESTRING.getIntValue());
                });
        // Supported classes
        assertTrue(
                Asn1Factory.createAsn1Element(
                                TagClass.UNIVERSAL.getIntValue(),
                                TagConstructed.PRIMITIVE.getBooleanValue(),
                                TagNumber.BIT_STRING.getIntValue())
                        instanceof Asn1PrimitiveBitString);
        assertTrue(
                Asn1Factory.createAsn1Element(
                                TagClass.UNIVERSAL.getIntValue(),
                                TagConstructed.PRIMITIVE.getBooleanValue(),
                                TagNumber.BOOLEAN.getIntValue())
                        instanceof Asn1Boolean);
        assertTrue(
                Asn1Factory.createAsn1Element(
                                TagClass.UNIVERSAL.getIntValue(),
                                TagConstructed.PRIMITIVE.getBooleanValue(),
                                TagNumber.ENUMERATED.getIntValue())
                        instanceof Asn1Enumerated);
        assertTrue(
                Asn1Factory.createAsn1Element(
                                TagClass.UNIVERSAL.getIntValue(),
                                TagConstructed.PRIMITIVE.getBooleanValue(),
                                TagNumber.GENERALIZEDTIME.getIntValue())
                        instanceof Asn1PrimitiveGeneralizedTime);
        assertTrue(
                Asn1Factory.createAsn1Element(
                                TagClass.UNIVERSAL.getIntValue(),
                                TagConstructed.PRIMITIVE.getBooleanValue(),
                                TagNumber.IA5STRING.getIntValue())
                        instanceof Asn1PrimitiveIa5String);
        assertTrue(
                Asn1Factory.createAsn1Element(
                                TagClass.UNIVERSAL.getIntValue(),
                                TagConstructed.PRIMITIVE.getBooleanValue(),
                                TagNumber.INTEGER.getIntValue())
                        instanceof Asn1Integer);
        assertTrue(
                Asn1Factory.createAsn1Element(
                                TagClass.UNIVERSAL.getIntValue(),
                                TagConstructed.PRIMITIVE.getBooleanValue(),
                                TagNumber.NULL.getIntValue())
                        instanceof Asn1Null);
        assertTrue(
                Asn1Factory.createAsn1Element(
                                TagClass.UNIVERSAL.getIntValue(),
                                TagConstructed.PRIMITIVE.getBooleanValue(),
                                TagNumber.OBJECT_IDENTIFIER.getIntValue())
                        instanceof Asn1ObjectIdentifier);
        assertTrue(
                Asn1Factory.createAsn1Element(
                                TagClass.UNIVERSAL.getIntValue(),
                                TagConstructed.PRIMITIVE.getBooleanValue(),
                                TagNumber.OCTET_STRING.getIntValue())
                        instanceof Asn1PrimitiveOctetString);
        assertTrue(
                Asn1Factory.createAsn1Element(
                                TagClass.UNIVERSAL.getIntValue(),
                                TagConstructed.PRIMITIVE.getBooleanValue(),
                                TagNumber.PRINTABLESTRING.getIntValue())
                        instanceof Asn1PrimitivePrintableString);
        assertTrue(
                Asn1Factory.createAsn1Element(
                                TagClass.UNIVERSAL.getIntValue(),
                                TagConstructed.PRIMITIVE.getBooleanValue(),
                                TagNumber.T61STRING.getIntValue())
                        instanceof Asn1PrimitiveT61String);
        assertTrue(
                Asn1Factory.createAsn1Element(
                                TagClass.UNIVERSAL.getIntValue(),
                                TagConstructed.PRIMITIVE.getBooleanValue(),
                                TagNumber.UTCTIME.getIntValue())
                        instanceof Asn1PrimitiveUtcTime);
        assertTrue(
                Asn1Factory.createAsn1Element(
                                TagClass.UNIVERSAL.getIntValue(),
                                TagConstructed.PRIMITIVE.getBooleanValue(),
                                TagNumber.UTF8STRING.getIntValue())
                        instanceof Asn1PrimitiveUtf8String);

        assertTrue(
                Asn1Factory.createAsn1Element(
                                TagClass.UNIVERSAL.getIntValue(),
                                TagConstructed.CONSTRUCTED.getBooleanValue(),
                                TagNumber.SEQUENCE.getIntValue())
                        instanceof Asn1Sequence);
        assertTrue(
                Asn1Factory.createAsn1Element(
                                TagClass.UNIVERSAL.getIntValue(),
                                TagConstructed.CONSTRUCTED.getBooleanValue(),
                                TagNumber.SET.getIntValue())
                        instanceof Asn1Set);

        // Unknown
        assertTrue(
                Asn1Factory.createAsn1Element(
                                TagClass.APPLICATION.getIntValue(),
                                TagConstructed.CONSTRUCTED.getBooleanValue(),
                                TagNumber.SET.getIntValue())
                        instanceof Asn1UnknownField);
        assertTrue(
                Asn1Factory.createAsn1Element(
                                TagClass.CONTEXT_SPECIFIC.getIntValue(),
                                TagConstructed.CONSTRUCTED.getBooleanValue(),
                                TagNumber.SET.getIntValue())
                        instanceof Asn1UnknownField);
        assertTrue(
                Asn1Factory.createAsn1Element(
                                TagClass.PRIVATE.getIntValue(),
                                TagConstructed.CONSTRUCTED.getBooleanValue(),
                                TagNumber.SET.getIntValue())
                        instanceof Asn1UnknownField);
        assertTrue(
                Asn1Factory.createAsn1Element(
                                TagClass.UNIVERSAL.getIntValue(),
                                TagConstructed.CONSTRUCTED.getBooleanValue(),
                                12345)
                        instanceof Asn1UnknownField);
    }
}

/*
 * ASN.1-Attacker - A library for arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import static org.junit.jupiter.api.Assertions.*;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.UniversalTagNumber;
import org.junit.jupiter.api.Test;

public class Asn1FactoryTest {

    @Test
    public void testCreateAsn1Element() {
        // Test Unsupported
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.BIT_STRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.BMPSTRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.BOOLEAN.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.CHARACTER_STRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.EMBEDDED_PDV.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.END_OF_CONTENT.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.ENUMERATED.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.EXTERNAL.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.GENERALIZEDTIME.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.GENERALSTRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.GRAPHICSTRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.IA5STRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.INTEGER.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.NULL.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.NUMERICSTRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.OBJECT_DESCRIPTOR.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.OBJECT_IDENTIFIER.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.OCTET_STRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.PRINTABLESTRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.REAL.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.RELATIVE_OID.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.T61STRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.UNIVERSALSTRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.UTCTIME.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.UTF8STRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.VIDEOTEXSTRING.getIntValue());
                });
        assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    new Asn1Factory()
                            .createAsn1Element(
                                    TagClass.UNIVERSAL.getIntValue(),
                                    TagConstructed.CONSTRUCTED.getBooleanValue(),
                                    UniversalTagNumber.VISIBLESTRING.getIntValue());
                });
        // Supported classes
        assertTrue(
                new Asn1Factory()
                                .createAsn1Element(
                                        TagClass.UNIVERSAL.getIntValue(),
                                        TagConstructed.PRIMITIVE.getBooleanValue(),
                                        UniversalTagNumber.BIT_STRING.getIntValue())
                        instanceof Asn1BitString);
        assertTrue(
                new Asn1Factory()
                                .createAsn1Element(
                                        TagClass.UNIVERSAL.getIntValue(),
                                        TagConstructed.PRIMITIVE.getBooleanValue(),
                                        UniversalTagNumber.BOOLEAN.getIntValue())
                        instanceof Asn1Boolean);
        assertTrue(
                new Asn1Factory()
                                .createAsn1Element(
                                        TagClass.UNIVERSAL.getIntValue(),
                                        TagConstructed.PRIMITIVE.getBooleanValue(),
                                        UniversalTagNumber.GENERALIZEDTIME.getIntValue())
                        instanceof Asn1GeneralizedTime);
        assertTrue(
                new Asn1Factory()
                                .createAsn1Element(
                                        TagClass.UNIVERSAL.getIntValue(),
                                        TagConstructed.PRIMITIVE.getBooleanValue(),
                                        UniversalTagNumber.IA5STRING.getIntValue())
                        instanceof Asn1Ia5String);
        assertTrue(
                new Asn1Factory()
                                .createAsn1Element(
                                        TagClass.UNIVERSAL.getIntValue(),
                                        TagConstructed.PRIMITIVE.getBooleanValue(),
                                        UniversalTagNumber.INTEGER.getIntValue())
                        instanceof Asn1Integer);
        assertTrue(
                new Asn1Factory()
                                .createAsn1Element(
                                        TagClass.UNIVERSAL.getIntValue(),
                                        TagConstructed.PRIMITIVE.getBooleanValue(),
                                        UniversalTagNumber.NULL.getIntValue())
                        instanceof Asn1Null);
        assertTrue(
                new Asn1Factory()
                                .createAsn1Element(
                                        TagClass.UNIVERSAL.getIntValue(),
                                        TagConstructed.PRIMITIVE.getBooleanValue(),
                                        UniversalTagNumber.OBJECT_IDENTIFIER.getIntValue())
                        instanceof Asn1ObjectIdentifier);
        assertTrue(
                new Asn1Factory()
                                .createAsn1Element(
                                        TagClass.UNIVERSAL.getIntValue(),
                                        TagConstructed.PRIMITIVE.getBooleanValue(),
                                        UniversalTagNumber.OCTET_STRING.getIntValue())
                        instanceof Asn1OctetString);
        assertTrue(
                new Asn1Factory()
                                .createAsn1Element(
                                        TagClass.UNIVERSAL.getIntValue(),
                                        TagConstructed.PRIMITIVE.getBooleanValue(),
                                        UniversalTagNumber.PRINTABLESTRING.getIntValue())
                        instanceof Asn1PrintableString);
        assertTrue(
                new Asn1Factory()
                                .createAsn1Element(
                                        TagClass.UNIVERSAL.getIntValue(),
                                        TagConstructed.PRIMITIVE.getBooleanValue(),
                                        UniversalTagNumber.T61STRING.getIntValue())
                        instanceof Asn1T61String);
        assertTrue(
                new Asn1Factory()
                                .createAsn1Element(
                                        TagClass.UNIVERSAL.getIntValue(),
                                        TagConstructed.PRIMITIVE.getBooleanValue(),
                                        UniversalTagNumber.UTCTIME.getIntValue())
                        instanceof Asn1UtcTime);
        assertTrue(
                new Asn1Factory()
                                .createAsn1Element(
                                        TagClass.UNIVERSAL.getIntValue(),
                                        TagConstructed.PRIMITIVE.getBooleanValue(),
                                        UniversalTagNumber.UTF8STRING.getIntValue())
                        instanceof Asn1Utf8String);

        assertTrue(
                new Asn1Factory()
                                .createAsn1Element(
                                        TagClass.UNIVERSAL.getIntValue(),
                                        TagConstructed.CONSTRUCTED.getBooleanValue(),
                                        UniversalTagNumber.SEQUENCE.getIntValue())
                        instanceof Asn1Sequence);
        assertTrue(
                new Asn1Factory()
                                .createAsn1Element(
                                        TagClass.UNIVERSAL.getIntValue(),
                                        TagConstructed.CONSTRUCTED.getBooleanValue(),
                                        UniversalTagNumber.SET.getIntValue())
                        instanceof Asn1Set);

        // Unknown
        assertTrue(
                new Asn1Factory()
                                .createAsn1Element(
                                        TagClass.APPLICATION.getIntValue(),
                                        TagConstructed.CONSTRUCTED.getBooleanValue(),
                                        UniversalTagNumber.SET.getIntValue())
                        instanceof Asn1UnknownField);
        assertTrue(
                new Asn1Factory()
                                .createAsn1Element(
                                        TagClass.CONTEXT_SPECIFIC.getIntValue(),
                                        TagConstructed.CONSTRUCTED.getBooleanValue(),
                                        UniversalTagNumber.SET.getIntValue())
                        instanceof Asn1UnknownField);
        assertTrue(
                new Asn1Factory()
                                .createAsn1Element(
                                        TagClass.PRIVATE.getIntValue(),
                                        TagConstructed.CONSTRUCTED.getBooleanValue(),
                                        UniversalTagNumber.SET.getIntValue())
                        instanceof Asn1UnknownField);
        assertTrue(
                new Asn1Factory()
                                .createAsn1Element(
                                        TagClass.UNIVERSAL.getIntValue(),
                                        TagConstructed.CONSTRUCTED.getBooleanValue(),
                                        12345)
                        instanceof Asn1UnknownField);
    }
}

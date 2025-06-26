/*
 * ASN.1-Attacker - A Library for Arbitrary ASN.1 Structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.UniversalTagNumber;
import de.rub.nds.asn1.parser.ParserHelper;
import de.rub.nds.asn1.preparator.Asn1PreparatorHelper;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

public class Asn1VisibleStringTest {

    @Test
    public void testConstructor() {
        Asn1VisibleString visibleString = new Asn1VisibleString("test");
        assertNotNull(visibleString);
        assertEquals("test", visibleString.getIdentifier());
        assertEquals(TagClass.UNIVERSAL, visibleString.getTagClassType());
        assertEquals(TagConstructed.PRIMITIVE, visibleString.getTagConstructedType());
        assertEquals(UniversalTagNumber.VISIBLESTRING, visibleString.getUniversalTagNumberType());
    }

    @Test
    public void testImplicitConstructor() {
        Asn1VisibleString visibleString = new Asn1VisibleString("test", 5);
        assertNotNull(visibleString);
        assertEquals("test", visibleString.getIdentifier());
        assertEquals(TagClass.CONTEXT_SPECIFIC, visibleString.getTagClassType());
        assertEquals(TagConstructed.PRIMITIVE, visibleString.getTagConstructedType());
    }

    @Test
    public void testEncoding() {
        Asn1VisibleString visibleString = new Asn1VisibleString("test");
        String testString = "Hello World!";
        visibleString = Asn1PreparatorHelper.prepareField(visibleString, testString);

        // VisibleString tag is 0x1A (26), length is 12, then the ASCII content
        byte[] expected =
                ArrayConverter.concatenate(
                        new byte[] {0x1A, 0x0C}, // tag and length
                        testString.getBytes(StandardCharsets.US_ASCII));

        byte[] actual =
                ArrayConverter.concatenate(
                        visibleString.getTagOctets().getValue(),
                        visibleString.getLengthOctets().getValue(),
                        visibleString.getContent().getValue());

        assertArrayEquals(expected, actual);
        assertEquals(testString, visibleString.getValue().getValue());
    }

    @Test
    public void testParsing() throws Exception {
        String testString = "Test VisibleString 123!";
        byte[] encoded =
                ArrayConverter.concatenate(
                        new byte[] {0x1A}, // VisibleString tag
                        new byte[] {(byte) testString.length()}, // length
                        testString.getBytes(StandardCharsets.US_ASCII));

        BufferedInputStream inputStream =
                new BufferedInputStream(new ByteArrayInputStream(encoded));

        Asn1VisibleString visibleString = new Asn1VisibleString("parsed");
        ParserHelper.parseAsn1VisibleString(visibleString, inputStream);

        assertEquals(testString, visibleString.getValue().getValue());
        assertEquals(
                TagClass.UNIVERSAL.getIntValue(),
                visibleString.getTagClass().getValue().intValue());
        assertEquals(
                TagConstructed.PRIMITIVE.getBooleanValue(),
                visibleString.getTagConstructed().getValue());
        assertEquals(
                UniversalTagNumber.VISIBLESTRING.getIntValue(),
                visibleString.getTagNumber().getValue().intValue());
    }

    @Test
    public void testParseViaHelper() throws Exception {
        String testString = "Another test!";
        byte[] encoded =
                ArrayConverter.concatenate(
                        new byte[] {0x1A}, // VisibleString tag
                        new byte[] {(byte) testString.length()}, // length
                        testString.getBytes(StandardCharsets.US_ASCII));

        BufferedInputStream inputStream =
                new BufferedInputStream(new ByteArrayInputStream(encoded));

        Asn1Field field =
                ParserHelper.parseTagNumberField(
                        inputStream, TagClass.UNIVERSAL, UniversalTagNumber.VISIBLESTRING);

        assertNotNull(field);
        assertEquals(Asn1VisibleString.class, field.getClass());
        Asn1VisibleString visibleString = (Asn1VisibleString) field;
        assertEquals(testString, visibleString.getValue().getValue());
    }
}

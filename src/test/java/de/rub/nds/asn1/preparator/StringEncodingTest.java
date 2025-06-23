/*
 * ASN.1-Attacker - A Library for Arbitrary ASN.1 Structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import static org.junit.jupiter.api.Assertions.*;

import de.rub.nds.asn1.model.Asn1BmpString;
import de.rub.nds.asn1.model.Asn1T61String;
import de.rub.nds.asn1.model.Asn1UniversalString;
import de.rub.nds.asn1.parser.ParserHelper;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

public class StringEncodingTest {

    @Test
    public void testBmpStringEncoding() {
        // BmpString should use UTF-16BE encoding
        String testString = "Hello";
        Asn1BmpString bmpString = new Asn1BmpString("bmpString");
        bmpString = Asn1PreparatorHelper.prepareField(bmpString, testString);

        // Expected encoding in UTF-16BE
        byte[] expected = testString.getBytes(StandardCharsets.UTF_16BE);
        assertArrayEquals(expected, bmpString.getContent().getValue());

        // Test with special characters
        String specialString = "Hello\u00A9\u00AE"; // Hello©®
        bmpString = new Asn1BmpString("bmpString");
        bmpString = Asn1PreparatorHelper.prepareField(bmpString, specialString);
        expected = specialString.getBytes(StandardCharsets.UTF_16BE);
        assertArrayEquals(expected, bmpString.getContent().getValue());
    }

    @Test
    public void testUniversalStringEncoding() throws Exception {
        // UniversalString should use UTF-32BE encoding
        String testString = "Hello";
        Asn1UniversalString universalString = new Asn1UniversalString("universalString");
        universalString = Asn1PreparatorHelper.prepareField(universalString, testString);

        // Expected encoding in UTF-32BE
        byte[] expected = testString.getBytes(Charset.forName("UTF-32BE"));
        assertArrayEquals(expected, universalString.getContent().getValue());

        // Test with special characters including emoji
        String specialString = "Hello\u00A9\uD83D\uDE00"; // Hello©😀
        universalString = new Asn1UniversalString("universalString");
        universalString = Asn1PreparatorHelper.prepareField(universalString, specialString);
        expected = specialString.getBytes(Charset.forName("UTF-32BE"));
        assertArrayEquals(expected, universalString.getContent().getValue());
    }

    @Test
    public void testT61StringEncodingConsistency() {
        // T61String should use ISO-8859-1 for both encoding and parsing
        String testString = "Test\u00E4\u00F6\u00FC"; // Test äöü

        // Encode using preparator
        byte[] encodedBytes = Asn1PreparatorHelper.encodeT61String(testString);

        // Expected ISO-8859-1 encoding
        byte[] expectedBytes = testString.getBytes(StandardCharsets.ISO_8859_1);

        // These should now be equal after the fix
        assertArrayEquals(expectedBytes, encodedBytes, "T61String should use ISO-8859-1 encoding");

        // Test parsing consistency
        Asn1T61String t61String = new Asn1T61String("t61String");
        t61String.setContent(encodedBytes);
        ParserHelper.parseT61StringContent(t61String);

        // The parsed value should match the original
        assertEquals(testString, t61String.getValue().getValue());
    }
}

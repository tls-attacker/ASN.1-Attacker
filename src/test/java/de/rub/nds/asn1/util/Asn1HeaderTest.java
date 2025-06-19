/*
 * ASN.1-Attacker - A Library for Arbitrary ASN.1 Structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.util;

import static org.junit.jupiter.api.Assertions.*;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import java.math.BigInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Asn1HeaderTest {

    private TagClass tagClass;
    private int tagNumber;
    private BigInteger length;
    private TagConstructed tagConstructed;
    private Asn1Header header;

    @BeforeEach
    public void setUp() {
        tagClass = TagClass.UNIVERSAL;
        tagNumber = 6;
        length = BigInteger.valueOf(100);
        tagConstructed = TagConstructed.CONSTRUCTED;
        header = new Asn1Header(tagClass, tagNumber, length, tagConstructed);
    }

    @Test
    public void testConstructor() {
        assertNotNull(header);
        assertEquals(tagClass, header.getTagClass());
        assertEquals(tagNumber, header.getTagNumber());
        assertEquals(length, header.getLength());
        assertEquals(tagConstructed, header.getTagConstructed());
    }

    @Test
    public void testConstructorWithNullValues() {
        Asn1Header nullHeader = new Asn1Header(null, 0, null, null);
        assertNull(nullHeader.getTagClass());
        assertEquals(0, nullHeader.getTagNumber());
        assertNull(nullHeader.getLength());
        assertNull(nullHeader.getTagConstructed());
    }

    @Test
    public void testConstructorWithAllTagClasses() {
        for (TagClass tc : TagClass.values()) {
            Asn1Header h = new Asn1Header(tc, tagNumber, length, tagConstructed);
            assertEquals(tc, h.getTagClass());
        }
    }

    @Test
    public void testConstructorWithAllTagConstructedValues() {
        for (TagConstructed tc : TagConstructed.values()) {
            Asn1Header h = new Asn1Header(tagClass, tagNumber, length, tc);
            assertEquals(tc, h.getTagConstructed());
        }
    }

    @Test
    public void testGetTagNumber() {
        assertEquals(6, header.getTagNumber());

        // Test with different tag numbers
        Asn1Header header0 = new Asn1Header(tagClass, 0, length, tagConstructed);
        assertEquals(0, header0.getTagNumber());

        Asn1Header headerNegative = new Asn1Header(tagClass, -1, length, tagConstructed);
        assertEquals(-1, headerNegative.getTagNumber());

        Asn1Header headerMax = new Asn1Header(tagClass, Integer.MAX_VALUE, length, tagConstructed);
        assertEquals(Integer.MAX_VALUE, headerMax.getTagNumber());

        Asn1Header headerMin = new Asn1Header(tagClass, Integer.MIN_VALUE, length, tagConstructed);
        assertEquals(Integer.MIN_VALUE, headerMin.getTagNumber());
    }

    @Test
    public void testGetLength() {
        assertEquals(BigInteger.valueOf(100), header.getLength());

        // Test with different lengths
        Asn1Header header0 = new Asn1Header(tagClass, tagNumber, BigInteger.ZERO, tagConstructed);
        assertEquals(BigInteger.ZERO, header0.getLength());

        Asn1Header headerNegative =
                new Asn1Header(tagClass, tagNumber, BigInteger.valueOf(-1), tagConstructed);
        assertEquals(BigInteger.valueOf(-1), headerNegative.getLength());

        // Test with very large values
        BigInteger veryLarge = new BigInteger("999999999999999999999999999999999999999999");
        Asn1Header headerLarge = new Asn1Header(tagClass, tagNumber, veryLarge, tagConstructed);
        assertEquals(veryLarge, headerLarge.getLength());
    }

    @Test
    public void testGetTagClass() {
        assertEquals(TagClass.UNIVERSAL, header.getTagClass());

        // Test all tag classes
        Asn1Header headerApp =
                new Asn1Header(TagClass.APPLICATION, tagNumber, length, tagConstructed);
        assertEquals(TagClass.APPLICATION, headerApp.getTagClass());

        Asn1Header headerContext =
                new Asn1Header(TagClass.CONTEXT_SPECIFIC, tagNumber, length, tagConstructed);
        assertEquals(TagClass.CONTEXT_SPECIFIC, headerContext.getTagClass());

        Asn1Header headerPrivate =
                new Asn1Header(TagClass.PRIVATE, tagNumber, length, tagConstructed);
        assertEquals(TagClass.PRIVATE, headerPrivate.getTagClass());
    }

    @Test
    public void testGetTagConstructed() {
        assertEquals(TagConstructed.CONSTRUCTED, header.getTagConstructed());

        // Test primitive
        Asn1Header headerPrimitive =
                new Asn1Header(tagClass, tagNumber, length, TagConstructed.PRIMITIVE);
        assertEquals(TagConstructed.PRIMITIVE, headerPrimitive.getTagConstructed());
    }

    @Test
    public void testToString() {
        String expected =
                "Asn1Header [tagClass=UNIVERSAL, tagNumber=6, length=100, tagConstructed=CONSTRUCTED]";
        assertEquals(expected, header.toString());
    }

    @Test
    public void testToStringWithNullValues() {
        Asn1Header nullHeader = new Asn1Header(null, 42, null, null);
        String expected =
                "Asn1Header [tagClass=null, tagNumber=42, length=null, tagConstructed=null]";
        assertEquals(expected, nullHeader.toString());
    }

    @Test
    public void testToStringWithAllCombinations() {
        // Test all combinations of TagClass and TagConstructed
        for (TagClass tc : TagClass.values()) {
            for (TagConstructed tconst : TagConstructed.values()) {
                Asn1Header h = new Asn1Header(tc, 10, BigInteger.TEN, tconst);
                String str = h.toString();
                assertTrue(str.contains("tagClass=" + tc));
                assertTrue(str.contains("tagNumber=10"));
                assertTrue(str.contains("length=10"));
                assertTrue(str.contains("tagConstructed=" + tconst));
            }
        }
    }

    @Test
    public void testToStringFormat() {
        // Verify the exact format of toString
        String result = header.toString();
        assertTrue(result.startsWith("Asn1Header ["));
        assertTrue(result.endsWith("]"));
        assertTrue(result.contains("tagClass="));
        assertTrue(result.contains("tagNumber="));
        assertTrue(result.contains("length="));
        assertTrue(result.contains("tagConstructed="));

        // Count commas to ensure proper formatting
        long commaCount = result.chars().filter(ch -> ch == ',').count();
        assertEquals(3, commaCount);
    }

    @Test
    public void testImmutability() {
        // Create header with mutable BigInteger
        BigInteger mutableLength = new BigInteger("100");
        Asn1Header h = new Asn1Header(tagClass, tagNumber, mutableLength, tagConstructed);

        // Verify initial value
        assertEquals(new BigInteger("100"), h.getLength());

        // Modify the original reference (BigInteger is immutable, so this creates a new object)
        mutableLength = mutableLength.add(BigInteger.ONE);

        // Verify the header's value hasn't changed
        assertEquals(new BigInteger("100"), h.getLength());
    }
}

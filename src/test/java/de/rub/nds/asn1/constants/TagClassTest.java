/*
 * ASN.1-Attacker - A library for arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TagClassTest {

    /** Test of values method, of class TagClass. */
    @Test
    public void testValues() {
        assertEquals(4, TagClass.values().length, "There exist 4 tag classes");
    }

    /** Test of valueOf method, of class TagClass. */
    @Test
    public void testValueOf() {
        assertEquals(TagClass.UNIVERSAL, TagClass.valueOf("UNIVERSAL"));
        assertEquals(TagClass.APPLICATION, TagClass.valueOf("APPLICATION"));
        assertEquals(TagClass.CONTEXT_SPECIFIC, TagClass.valueOf("CONTEXT_SPECIFIC"));
        assertEquals(TagClass.PRIVATE, TagClass.valueOf("PRIVATE"));
    }

    /** Test of getIntValue method, of class TagClass. */
    @Test
    public void testGetIntValue() {
        assertEquals(0, TagClass.UNIVERSAL.getIntValue());
        assertEquals(1, TagClass.APPLICATION.getIntValue());
        assertEquals(2, TagClass.CONTEXT_SPECIFIC.getIntValue());
        assertEquals(3, TagClass.PRIVATE.getIntValue());
    }

    /** Test of getStringValue method, of class TagClass. */
    @Test
    public void testGetStringValue() {
        assertEquals("universal", TagClass.UNIVERSAL.getStringValue());
        assertEquals("application", TagClass.APPLICATION.getStringValue());
        assertEquals("context-specific", TagClass.CONTEXT_SPECIFIC.getStringValue());
        assertEquals("private", TagClass.PRIVATE.getStringValue());
    }

    /** Test of fromIntValue method, of class TagClass. */
    @Test
    public void testFromIntValue() {
        assertEquals(TagClass.UNIVERSAL, TagClass.fromIntValue(0));
        assertEquals(TagClass.APPLICATION, TagClass.fromIntValue(1));
        assertEquals(TagClass.CONTEXT_SPECIFIC, TagClass.fromIntValue(2));
        assertEquals(TagClass.PRIVATE, TagClass.fromIntValue(3));
    }

    /** Test of fromStringValue method, of class TagClass. */
    @Test
    public void testFromStringValue() {
        assertEquals(TagClass.UNIVERSAL, TagClass.fromStringValue("universal"));
        assertEquals(TagClass.APPLICATION, TagClass.fromStringValue("application"));
        assertEquals(TagClass.CONTEXT_SPECIFIC, TagClass.fromStringValue("context-specific"));
        assertEquals(TagClass.PRIVATE, TagClass.fromStringValue("private"));
    }

    /** Test of fromIdentifierByte method, of class TagClass. */
    @Test
    public void testFromIdentifierByte() {
        assertEquals(TagClass.UNIVERSAL, TagClass.fromIdentifierByte((byte) 0b00101010));
        assertEquals(TagClass.APPLICATION, TagClass.fromIdentifierByte((byte) 0b01101010));
        assertEquals(TagClass.CONTEXT_SPECIFIC, TagClass.fromIdentifierByte((byte) 0b10101010));
        assertEquals(TagClass.PRIVATE, TagClass.fromIdentifierByte((byte) 0b11101010));
    }
}

/*
 * ASN.1-Attacker - A Library for Arbitrary ASN.1 Structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TagConstructedTest {

    /** Test of values method, of class TagConstructed. */
    @Test
    public void testValues() {
        assertEquals(2, TagConstructed.values().length);
    }

    /** Test of valueOf method, of class TagConstructed. */
    @Test
    public void testValueOf() {
        assertEquals(TagConstructed.CONSTRUCTED, TagConstructed.valueOf("CONSTRUCTED"));
        assertEquals(TagConstructed.PRIMITIVE, TagConstructed.valueOf("PRIMITIVE"));
    }

    /** Test of getIntValue method, of class TagConstructed. */
    @Test
    public void testGetIntValue() {
        assertEquals(0, TagConstructed.PRIMITIVE.getIntValue());
        assertEquals(1, TagConstructed.CONSTRUCTED.getIntValue());
    }

    /** Test of getBooleanValue method, of class TagConstructed. */
    @Test
    public void testGetBooleanValue() {
        assertEquals(Boolean.FALSE, TagConstructed.PRIMITIVE.getBooleanValue());
        assertEquals(Boolean.TRUE, TagConstructed.CONSTRUCTED.getBooleanValue());
    }

    /** Test of fromIntValue method, of class TagConstructed. */
    @Test
    public void testFromIntValue() {
        assertEquals(TagConstructed.PRIMITIVE, TagConstructed.fromIntValue(0));
        assertEquals(TagConstructed.CONSTRUCTED, TagConstructed.fromIntValue(1));
        assertEquals(null, TagConstructed.fromIntValue(2));
        assertEquals(null, TagConstructed.fromIntValue(-1));
        assertEquals(null, TagConstructed.fromIntValue(Integer.MAX_VALUE));
        assertEquals(null, TagConstructed.fromIntValue(Integer.MIN_VALUE));
    }

    /** Test of fromBooleanValue method, of class TagConstructed. */
    @Test
    public void testFromBooleanValue() {
        assertEquals(TagConstructed.PRIMITIVE, TagConstructed.fromBooleanValue(false));
        assertEquals(TagConstructed.CONSTRUCTED, TagConstructed.fromBooleanValue(true));
    }

    /** Test of fromIdentifierByte method, of class TagConstructed. */
    @Test
    public void testFromIdentifierByte() {
        assertEquals(
                TagConstructed.PRIMITIVE, TagConstructed.fromIdentifierByte((byte) 0b00000000));
        assertEquals(
                TagConstructed.CONSTRUCTED, TagConstructed.fromIdentifierByte((byte) 0b00100000));
    }
}

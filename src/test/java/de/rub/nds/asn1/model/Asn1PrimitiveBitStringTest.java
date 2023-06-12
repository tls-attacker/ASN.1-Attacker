/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import de.rub.nds.modifiablevariable.util.Modifiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Asn1PrimitiveBitStringTest {

    private Asn1BitString asn1BitString;

    @BeforeEach
    public void setUp() {
        asn1BitString = new Asn1BitString("test");
    }

    @Test
    public void testSetGetUsedBits() {
        asn1BitString.setUsedBits(new byte[0]);
        assertArrayEquals(new byte[0], asn1BitString.getUsedBits().getValue());
        asn1BitString.setUsedBits(new byte[1]);
        assertArrayEquals(new byte[1], asn1BitString.getUsedBits().getValue());
    }

    @Test
    public void testSetUsedBits_Modifiable() {
        asn1BitString.setUsedBits(new byte[0]);
        assertArrayEquals(new byte[0], asn1BitString.getUsedBits().getValue());
        asn1BitString.setUsedBits(Modifiable.explicit(new byte[1]));
        assertArrayEquals(new byte[1], asn1BitString.getUsedBits().getValue());
    }

    @Test
    public void testSetGetUnusedBits() {
        asn1BitString.setUnusedBits((byte) 0x1F);
        assertEquals((byte) 0x1F, asn1BitString.getUnusedBits().getValue());
        asn1BitString.setUnusedBits((byte) 0x1D);
        assertEquals((byte) 0x1D, asn1BitString.getUnusedBits().getValue());
    }

    @Test
    public void testSetUnusedBits_Modifiable() {
        asn1BitString.setUnusedBits((byte) 0x1F);
        assertEquals((byte) 0x1F, asn1BitString.getUnusedBits().getValue());
        asn1BitString.setUnusedBits(Modifiable.explicit((byte) 0x1D));
        assertEquals((byte) 0x1D, asn1BitString.getUnusedBits().getValue());
    }

    @Test
    public void testSetGetPadding() {
        asn1BitString.setPadding((byte) 0x1F);
        assertEquals((byte) 0x1F, asn1BitString.getPadding().getValue());
        asn1BitString.setPadding((byte) 0x1D);
        assertEquals((byte) 0x1D, asn1BitString.getPadding().getValue());
    }

    @Test
    public void testSetPadding_Modifiable() {
        asn1BitString.setPadding((byte) 0x1F);
        assertEquals((byte) 0x1F, asn1BitString.getPadding().getValue());
        asn1BitString.setPadding(Modifiable.explicit((byte) 0x1D));
        assertEquals((byte) 0x1D, asn1BitString.getPadding().getValue());
    }
}

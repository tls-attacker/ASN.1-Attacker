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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.UniversalTagNumber;
import de.rub.nds.modifiablevariable.biginteger.ModifiableBigInteger;
import de.rub.nds.modifiablevariable.bool.ModifiableBoolean;
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;
import de.rub.nds.modifiablevariable.integer.ModifiableInteger;
import de.rub.nds.modifiablevariable.util.Modifiable;
import java.math.BigInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Asn1FieldTest {

    private Asn1Field field;

    @BeforeEach
    public void setUp() {
        field =
                new Asn1FieldImpl(
                        "field",
                        TagClass.PRIVATE,
                        TagConstructed.PRIMITIVE,
                        UniversalTagNumber.BIT_STRING);
    }

    @Test
    public void testIsCompatible() {
        assertTrue(
                field.matchesHeader(
                        TagClass.PRIVATE,
                        TagConstructed.PRIMITIVE.getBooleanValue(),
                        UniversalTagNumber.BIT_STRING.getIntValue()));
        assertFalse(
                field.matchesHeader(
                        TagClass.PRIVATE,
                        TagConstructed.PRIMITIVE.getBooleanValue(),
                        UniversalTagNumber.BMPSTRING.getIntValue()));
        assertFalse(
                field.matchesHeader(
                        TagClass.PRIVATE,
                        TagConstructed.CONSTRUCTED.getBooleanValue(),
                        UniversalTagNumber.BIT_STRING.getIntValue()));
        assertFalse(
                field.matchesHeader(
                        TagClass.APPLICATION,
                        TagConstructed.PRIMITIVE.getBooleanValue(),
                        UniversalTagNumber.BIT_STRING.getIntValue()));
    }

    @Test
    public void testIsAndSetOptional() {
        assertFalse(field.isOptional());
        field.setOptional(true);
        assertTrue(field.isOptional());
        field.setOptional(false);
        assertFalse(field.isOptional());
    }

    @Test
    public void testGetTagClass() {
        assertNull(field.getTagClass());
        field.setTagClass(0);
        assertEquals(0, field.getTagClass().getValue());
        field.setTagClass((ModifiableInteger) null);
        assertNull(field.getTagClass());
        field.setTagClass(Modifiable.explicit(1));
        assertEquals(1, field.getTagClass().getValue());
    }

    @Test
    public void testGetTagConstructed() {
        assertNull(field.getTagConstructed());
        field.setTagConstructed(true);
        assertEquals(true, field.getTagConstructed().getValue());
        field.setTagConstructed((ModifiableBoolean) null);
        assertNull(field.getTagConstructed());
        field.setTagConstructed(Modifiable.explicit(false));
        assertEquals(false, field.getTagConstructed().getValue());
    }

    @Test
    public void testGetTagNumber() {
        assertNull(field.getTagNumber());
        field.setTagNumber(0);
        assertEquals(0, field.getTagNumber().getValue());
        field.setTagNumber((ModifiableInteger) null);
        assertNull(field.getTagNumber());
        field.setTagNumber(Modifiable.explicit(1));
        assertEquals(1, field.getTagNumber().getValue());
    }

    @Test
    public void testGetLength() {
        assertNull(field.getLength());
        field.setLength(BigInteger.ZERO);
        assertEquals(BigInteger.ZERO, field.getLength().getValue());
        field.setLength((ModifiableBigInteger) null);
        assertNull(field.getLength());
        field.setLength(Modifiable.explicit(BigInteger.ONE));
        assertEquals(BigInteger.ONE, field.getLength().getValue());
    }

    @Test
    public void testGetContent() {
        assertNull(field.getContent());
        field.setContent(new byte[0]);
        assertArrayEquals(new byte[0], field.getContent().getValue());
        field.setContent((ModifiableByteArray) null);
        assertNull(field.getContent());
        field.setContent(Modifiable.explicit(new byte[0]));
        assertArrayEquals(new byte[0], field.getContent().getValue());
    }

    @Test
    public void testGetTagOctets() {
        assertNull(field.getTagOctets());
        field.setTagOctets(new byte[0]);
        assertArrayEquals(new byte[0], field.getTagOctets().getValue());
        field.setTagOctets((ModifiableByteArray) null);
        assertNull(field.getTagOctets());
        field.setTagOctets(Modifiable.explicit(new byte[0]));
        assertArrayEquals(new byte[0], field.getTagOctets().getValue());
    }

    @Test
    public void testGetLengthOctets() {
        assertNull(field.getLengthOctets());
        field.setLengthOctets(new byte[0]);
        assertArrayEquals(new byte[0], field.getLengthOctets().getValue());
        field.setLengthOctets((ModifiableByteArray) null);
        assertNull(field.getLengthOctets());
        field.setLengthOctets(Modifiable.explicit(new byte[0]));
        assertArrayEquals(new byte[0], field.getLengthOctets().getValue());
    }

    @Test
    public void testGetIdentifier() {
        assertEquals("field", field.getIdentifier());
    }

    @Test
    public void testSetIdentifier() {
        assertEquals("field", field.getIdentifier());
        field.setIdentifier("other");
        assertEquals("other", field.getIdentifier());
        field.setIdentifier(null);
        assertNull(field.getIdentifier());
    }

    public class Asn1FieldImpl extends Asn1Field {

        public Asn1FieldImpl(
                String identifier,
                TagClass tagClassType,
                TagConstructed tagConstructedType,
                UniversalTagNumber tagNummerType) {
            super(identifier, tagClassType, tagConstructedType, tagNummerType.getIntValue());
        }
    }
}

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
import static org.junit.jupiter.api.Assertions.assertNull;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.UniversalTagNumber;
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Asn1ContainerTest {

    private Asn1Container instance;

    @BeforeEach
    public void setUp() {
        instance = new Asn1ContainerImpl();
    }

    @Test
    public void testGetEncodedChildren() {
        assertNull(instance.getEncodedChildren());
        instance.setEncodedChildren(new byte[0]);
        assertArrayEquals(new byte[0], instance.getEncodedChildren().getValue());
        instance.setEncodedChildren((ModifiableByteArray) null);
        assertNull(instance.getEncodedChildren());
    }

    public class Asn1ContainerImpl extends Asn1Container {

        public Asn1ContainerImpl() {
            super("", TagClass.UNIVERSAL, TagConstructed.CONSTRUCTED, UniversalTagNumber.SEQUENCE);
        }
    }
}

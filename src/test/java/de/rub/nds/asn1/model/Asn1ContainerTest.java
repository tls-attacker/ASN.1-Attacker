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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.TagNumber;
import de.rub.nds.asn1.parser.Asn1Parser;
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;
import de.rub.nds.modifiablevariable.util.Modifiable;
import java.util.LinkedList;
import java.util.List;
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
        instance.setEncodedChildren(Modifiable.explicit(new byte[0]));
        assertArrayEquals(new byte[0], instance.getEncodedChildren().getValue());
    }

    @Test
    public void testGetAddChildren() {
        assertTrue(instance.getChildren().isEmpty());
        instance.addChild(new Asn1Boolean("test"));
        assertTrue(instance.getChildren().size() == 1);
        assertEquals("test", ((Asn1Boolean) (instance.getChildren().get(0))).getIdentifier());
    }

    @Test
    public void testSetChildren() {
        List<Asn1Encodable> children = new LinkedList<>();
        children.add(new Asn1Boolean("test1"));
        children.add(new Asn1Boolean("test2"));
        instance.setChildren(children);
        assertTrue(instance.getChildren().size() == 2);
        assertEquals("test1", ((Asn1Boolean) (instance.getChildren().get(0))).getIdentifier());
        assertEquals("test2", ((Asn1Boolean) (instance.getChildren().get(1))).getIdentifier());
    }

    @Test
    public void testClearChildren() {
        List<Asn1Encodable> children = new LinkedList<>();
        children.add(new Asn1Boolean("test1"));
        children.add(new Asn1Boolean("test2"));
        instance.setChildren(children);
        assertTrue(instance.getChildren().size() == 2);
        instance.clearChildren();
        assertTrue(instance.getChildren().isEmpty());
    }

    public class Asn1ContainerImpl extends Asn1Container {

        public Asn1ContainerImpl() {
            super("", TagClass.UNIVERSAL, TagConstructed.CONSTRUCTED, TagNumber.SEQUENCE);
        }

        @Override
        public Asn1Parser<?> getParser() {
            throw new UnsupportedOperationException("Unimplemented method 'getParser'");
        }
    }
}

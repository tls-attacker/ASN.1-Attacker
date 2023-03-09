/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import static org.junit.jupiter.api.Assertions.*;

import de.rub.nds.asn1.context.EmptyChooser;
import de.rub.nds.asn1.parser.Asn1AnyParser;
import de.rub.nds.asn1.preparator.Asn1BooleanPreparator;
import de.rub.nds.asn1.serializer.Asn1FieldSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Asn1AnyTest {

    private EmptyChooser chooser;
    private Asn1Any<EmptyChooser> any;

    @BeforeEach
    public void setUp() {
        chooser = new EmptyChooser();
        any = new Asn1Any<EmptyChooser>("someAny");
    }

    /** Test of isCompatible method, of class Asn1Any. */
    @Test
    public void testIsCompatible() {
        assertTrue(any.isCompatible(512, Boolean.TRUE, 1));
        assertTrue(any.isCompatible(0, Boolean.FALSE, 2));
        assertTrue(any.isCompatible(-1, Boolean.TRUE, -1));
    }

    /** Test of isOptional method, of class Asn1Any. */
    @Test
    public void testIsOptional() {
        assertFalse(any.isOptional());
        any.setOptional(true);
        assertTrue(any.isOptional());
    }

    /** Test of setOptional method, of class Asn1Any. */
    @Test
    public void testSetOptional() {
        assertFalse(any.isOptional());
        any.setOptional(true);
        assertTrue(any.isOptional());
        any.setOptional(false);
        assertFalse(any.isOptional());
    }

    /** Test of setInstantiation method, of class Asn1Any. */
    @Test
    public void testSetInstantiation() {
        assertNull(any.getInstantiation());
        Asn1Field<EmptyChooser> field = new Asn1Boolean<>("boolean");
        any.setInstantiation(field);
        assertEquals(field, any.getInstantiation());
        any.setInstantiation(null);
        assertNull(any.getInstantiation());
    }

    /** Test of getInstantiation method, of class Asn1Any. */
    @Test
    public void testGetInstantiation() {
        assertNull(any.getInstantiation());
        Asn1Field<EmptyChooser> field = new Asn1Boolean<>("boolean");
        any.setInstantiation(field);
        assertEquals(field, any.getInstantiation());
    }

    /** Test of getIdentifier method, of class Asn1Any. */
    @Test
    public void testGetIdentifier() {
        assertEquals("someAny", any.getIdentifier());
    }

    /** Test of setIdentifier method, of class Asn1Any. */
    @Test
    public void testSetIdentifier() {
        assertEquals("someAny", any.getIdentifier());
        any.setIdentifier("other");
        assertEquals("other", any.getIdentifier());
        any.setIdentifier(null);
        assertNull(any.getIdentifier());
    }

    /** Test of getSerializer method, of class Asn1Any. */
    @Test
    public void testGetGenericSerializer() {
        assertThrows(
                RuntimeException.class,
                () -> {
                    any.getSerializer();
                });
        Asn1Boolean<EmptyChooser> asn1Boolean = new Asn1Boolean<>("boolean");
        any.setInstantiation(asn1Boolean);
        assertTrue(any.getSerializer() instanceof Asn1FieldSerializer);
    }

    /** Test of getGenericPreparator method, of class Asn1Any. */
    @Test
    public void testGetGenericPreparator() {
        assertThrows(
                RuntimeException.class,
                () -> {
                    any.getPreparator(chooser);
                });
        Asn1Boolean<EmptyChooser> asn1Boolean = new Asn1Boolean<>("boolean");
        any.setInstantiation(asn1Boolean);
        assertTrue(any.getPreparator(chooser) instanceof Asn1BooleanPreparator);
    }

    /** Test of getParser method, of class Asn1Any. */
    @Test
    public void testGetParser() {
        assertTrue(any.getParser(chooser) instanceof Asn1AnyParser);
    }
}

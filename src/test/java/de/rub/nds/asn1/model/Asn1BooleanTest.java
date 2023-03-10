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
import de.rub.nds.asn1.handler.EmptyHandler;
import de.rub.nds.asn1.parser.Asn1BooleanParser;
import de.rub.nds.asn1.preparator.Asn1BooleanPreparator;
import de.rub.nds.modifiablevariable.bool.ModifiableBoolean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Asn1BooleanTest {

    private Asn1Boolean<EmptyChooser> asn1Boolean;

    @BeforeEach
    public void setUp() {
        asn1Boolean = new Asn1Boolean<>("test");
    }

    @Test
    public void testSetGetValue() {
        asn1Boolean.setValue(true);
        assertTrue(asn1Boolean.getValue().getValue());
        asn1Boolean.setValue(false);
        assertFalse(asn1Boolean.getValue().getValue());
    }

    @Test
    public void testSetValue_ModifiableBoolean() {
        asn1Boolean.setValue(new ModifiableBoolean(true));
        assertTrue(asn1Boolean.getValue().getValue());
        asn1Boolean.setValue(new ModifiableBoolean(false));
        assertFalse(asn1Boolean.getValue().getValue());
    }

    @Test
    public void testGetPreparator() {
        Asn1BooleanPreparator<EmptyChooser> preparator =
                asn1Boolean.getPreparator(new EmptyChooser());
        assertNotNull(preparator);
        assertTrue(preparator instanceof Asn1BooleanPreparator);
    }

    /** Test of getParser method, of class Asn1Boolean. */
    @Test
    public void testGetParser() {
        Asn1BooleanParser<EmptyChooser> parser = asn1Boolean.getParser(new EmptyChooser());
        assertNotNull(parser);
        assertTrue(parser instanceof Asn1BooleanParser);
    }

    /** Test of getHandler method, of class Asn1Boolean. */
    @Test
    public void testGetHandler() {
        EmptyHandler<EmptyChooser> handler =
                (EmptyHandler<EmptyChooser>) asn1Boolean.getHandler(new EmptyChooser());
        assertNotNull(handler);
        assertTrue(handler instanceof EmptyHandler);
    }
}

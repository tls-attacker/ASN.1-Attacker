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
import de.rub.nds.asn1.parser.Asn1NullParser;
import de.rub.nds.asn1.preparator.Asn1NullPreparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Asn1NullTest {

    private Asn1Null<EmptyChooser> asn1Null;

    @BeforeEach
    public void setUp() {
        asn1Null = new Asn1Null<EmptyChooser>("test");
    }

    @Test
    public void testGetPreparator() {
        Asn1NullPreparator<EmptyChooser> preparator = asn1Null.getPreparator(new EmptyChooser());
        assertNotNull(preparator);
        assertTrue(preparator instanceof Asn1NullPreparator);
    }

    @Test
    public void testGetParser() {
        Asn1NullParser<EmptyChooser> parser = asn1Null.getParser(new EmptyChooser());
        assertNotNull(parser);
        assertTrue(parser instanceof Asn1NullParser);
    }

    @Test
    public void testGetHandler() {
        EmptyHandler<EmptyChooser> handler =
                (EmptyHandler<EmptyChooser>) asn1Null.getHandler(new EmptyChooser());
        assertNotNull(handler);
        assertTrue(handler instanceof EmptyHandler);
    }
}

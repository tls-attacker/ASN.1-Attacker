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
import de.rub.nds.asn1.parser.Asn1IntegerParser;
import de.rub.nds.asn1.preparator.Asn1IntegerPreparator;
import de.rub.nds.modifiablevariable.util.Modifiable;
import java.math.BigInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author ic0ns
 */
public class Asn1IntegerTest {

    private Asn1Integer asn1Integer;

    @BeforeEach
    public void setUp() {
        asn1Integer = new Asn1Integer("test");
    }

    @Test
    public void testSetGetValue() {
        asn1Integer.setValue(BigInteger.ONE);
        assertEquals(BigInteger.ONE, asn1Integer.getValue().getValue());
        asn1Integer.setValue(BigInteger.TWO);
        assertEquals(BigInteger.TWO, asn1Integer.getValue().getValue());
    }

    @Test
    public void testSetValue_ModifiableBoolean() {
        asn1Integer.setValue(BigInteger.ONE);
        assertEquals(BigInteger.ONE, asn1Integer.getValue().getValue());
        asn1Integer.setValue(Modifiable.explicit(BigInteger.TEN));
        assertEquals(BigInteger.TEN, asn1Integer.getValue().getValue());
    }

    @Test
    public void testGetPreparator() {
        Asn1IntegerPreparator preparator = asn1Integer.getPreparator(new EmptyChooser());
        assertNotNull(preparator);
        assertTrue(preparator instanceof Asn1IntegerPreparator);
    }

    @Test
    public void testGetParser() {
        Asn1IntegerParser parser = asn1Integer.getParser(new EmptyChooser());
        assertNotNull(parser);
        assertTrue(parser instanceof Asn1IntegerParser);
    }

    @Test
    public void testGetHandler() {
        EmptyHandler handler = (EmptyHandler) asn1Integer.getHandler(new EmptyChooser());
        assertNotNull(handler);
        assertTrue(handler instanceof EmptyHandler);
    }
}

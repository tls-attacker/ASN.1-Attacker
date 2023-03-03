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
import de.rub.nds.asn1.parser.Asn1ExplicitParser;
import de.rub.nds.asn1.preparator.GenericAsn1ContainerPreparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Asn1ExplicitTest {

    private Asn1Explicit instance;

    @BeforeEach
    public void setUp() {
        instance = new Asn1Explicit("outter", new Asn1Boolean<>("inner"));
    }

    @Test
    public void testGetPreparator() {
        assertTrue(
                instance.getPreparator(new EmptyChooser())
                        instanceof GenericAsn1ContainerPreparator);
    }

    @Test
    public void testGetChild() {
        assertTrue(instance.getChild() instanceof Asn1Boolean);
    }

    @Test
    public void testGetParser() {
        assertTrue(instance.getParser(new EmptyChooser()) instanceof Asn1ExplicitParser);
    }

    @Test
    public void testGetHandler() {
        assertTrue(instance.getHandler(new EmptyChooser()) instanceof EmptyHandler);
    }
}

/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.rub.nds.modifiablevariable.util.Modifiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Asn1ObjectIdentifierTest {

    private Asn1ObjectIdentifier asn1ObjectIdentifier;

    @BeforeEach
    public void setUp() {
        asn1ObjectIdentifier = new Asn1ObjectIdentifier("test");
    }

    @Test
    public void testSetGetValue() {
        asn1ObjectIdentifier.setValue("1.2.3.4");
        assertEquals("1.2.3.4", asn1ObjectIdentifier.getValue().getValue());
        asn1ObjectIdentifier.setValue("4.3.2.1");
        assertEquals("4.3.2.1", asn1ObjectIdentifier.getValue().getValue());
    }

    @Test
    public void testSetValue_Modifiable() {
        asn1ObjectIdentifier.setValue("1.2.3.4");
        assertEquals("1.2.3.4", asn1ObjectIdentifier.getValue().getValue());
        asn1ObjectIdentifier.setValue(Modifiable.explicit("4.3.2.1"));
        assertEquals("4.3.2.1", asn1ObjectIdentifier.getValue().getValue());
    }
}

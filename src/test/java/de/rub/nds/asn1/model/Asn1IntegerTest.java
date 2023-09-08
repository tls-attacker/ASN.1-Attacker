/*
 * ASN.1-Attacker - A library for arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.rub.nds.modifiablevariable.util.Modifiable;
import java.math.BigInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void testSetValue_Modifiable() {
        asn1Integer.setValue(BigInteger.ONE);
        assertEquals(BigInteger.ONE, asn1Integer.getValue().getValue());
        asn1Integer.setValue(Modifiable.explicit(BigInteger.TEN));
        assertEquals(BigInteger.TEN, asn1Integer.getValue().getValue());
    }
}

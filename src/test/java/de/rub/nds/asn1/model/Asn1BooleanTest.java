/*
 * ASN.1-Attacker - A Library for Arbitrary ASN.1 Structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.rub.nds.modifiablevariable.bool.ModifiableBoolean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Asn1BooleanTest {

    private Asn1Boolean asn1Boolean;

    @BeforeEach
    public void setUp() {
        asn1Boolean = new Asn1Boolean("test");
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
}

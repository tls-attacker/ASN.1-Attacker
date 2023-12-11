/*
 * ASN.1-Attacker - A Library for Arbitrary ASN.1 Structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TimeAccurracyTest {

    /** Test of values method, of class TimeAccurracy. */
    @Test
    public void testValues() {
        assertEquals(4, TimeAccurracy.values().length);
    }

    /** Test of valueOf method, of class TimeAccurracy. */
    @Test
    public void testValueOf() {
        assertEquals(TimeAccurracy.HOURS, TimeAccurracy.valueOf("HOURS"));
        assertEquals(TimeAccurracy.MILLISECONDS, TimeAccurracy.valueOf("MILLISECONDS"));
        assertEquals(TimeAccurracy.MINUTES, TimeAccurracy.valueOf("MINUTES"));
        assertEquals(TimeAccurracy.SECONDS, TimeAccurracy.valueOf("SECONDS"));
    }
}

/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.handler;

import de.rub.nds.asn1.context.EmptyChooser;
import org.junit.jupiter.api.Test;

public class EmptyHandlerTest {

    /** Test of adjustContext method, of class EmptyHandler. */
    @Test
    public void testAdjustContext() {
        EmptyHandler instance = new EmptyHandler(new EmptyChooser());
        instance.adjustContext();
        // This should do nothing
    }
}

/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import org.junit.jupiter.api.BeforeEach;

public class Asn1NullTest {

    private Asn1Null asn1Null;

    @BeforeEach
    public void setUp() {
        asn1Null = new Asn1Null("test");
    }
}

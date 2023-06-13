/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Asn1SetParser extends Asn1FieldParser<Asn1Set> {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LogManager.getLogger();

    public Asn1SetParser(Asn1Set asn1Set) {
        super(asn1Set);
    }
}

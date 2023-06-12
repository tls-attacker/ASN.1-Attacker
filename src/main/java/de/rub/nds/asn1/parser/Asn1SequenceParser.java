/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1Sequence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Asn1SequenceParser extends Asn1FieldParser<Asn1Sequence> {

    private static final Logger LOGGER = LogManager.getLogger();

    private Asn1Sequence sequence;

    public Asn1SequenceParser(Asn1Sequence asn1Sequence) {
        super(asn1Sequence);
        this.sequence = asn1Sequence;
    }
}

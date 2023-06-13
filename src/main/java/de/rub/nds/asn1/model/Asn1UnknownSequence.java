/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.parser.Asn1UnknownSequenceParser;

public class Asn1UnknownSequence extends Asn1Sequence {

    public Asn1UnknownSequence(String identifier) {
        super(identifier);
    }

    public Asn1UnknownSequenceParser getParser() {
        return new Asn1UnknownSequenceParser(this);
    }
}

/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.parser.Asn1UnknownSetParser;

public class Asn1UnknownSet extends Asn1Set {

    public Asn1UnknownSet(String identifier) {
        super(identifier);
    }

    public Asn1UnknownSetParser getParser() {
        return new Asn1UnknownSetParser(this);
    }
}

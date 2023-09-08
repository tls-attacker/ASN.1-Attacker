/*
 * ASN.1-Attacker - A library for arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.parser.Asn1UnknownSetParser;
import java.util.ArrayList;
import java.util.List;

public class Asn1UnknownSet extends Asn1Set {

    private List<Asn1Encodable> children;

    public Asn1UnknownSet(String identifier) {
        super(identifier);
        children = new ArrayList<>();
    }

    public Asn1UnknownSetParser getParser() {
        return new Asn1UnknownSetParser(this);
    }

    public void addChild(Asn1UnknownField unknownFiled) {
        children.add(unknownFiled);
    }
}

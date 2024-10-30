/*
 * ASN.1-Attacker - A Library for Arbitrary ASN.1 Structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.parser.Asn1UnknownSequenceParser;
import jakarta.xml.bind.annotation.XmlAnyElement;
import java.util.ArrayList;
import java.util.List;

public class Asn1UnknownSequence extends Asn1Sequence {

    @XmlAnyElement(lax = true)
    private List<Asn1Encodable> children;

    public Asn1UnknownSequence(String identifier) {
        super(identifier);
        children = new ArrayList<>();
    }

    public Asn1UnknownSequenceParser getParser() {
        return new Asn1UnknownSequenceParser(this);
    }

    public void addChild(Asn1UnknownField unknownFiled) {
        children.add(unknownFiled);
    }
}

/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.model;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.TagNumber;
import de.rub.nds.asn1.parser.Asn1ExplicitParser;
import de.rub.nds.asn1.parser.Asn1FieldParser;
import de.rub.nds.asn1.preparator.GenericAsn1ContainerPreparator;
import de.rub.nds.asn1.preparator.Preparator;

public class Asn1Explicit extends Asn1Container {

    private Asn1Encodable child;

    public Asn1Explicit(String identifier, Asn1Encodable child) {
        super(identifier, TagClass.CONTEXT_SPECIFIC, TagConstructed.CONSTRUCTED, null);
        this.child = child;
        addChild(child);
    }

    @Override
    public Preparator getGenericPreparator() {
        return new GenericAsn1ContainerPreparator(this);
    }

    public Asn1Encodable getChild() {
        return child;
    }

    @Override
    public Asn1FieldParser<Asn1Explicit> getParser() {
        return new Asn1ExplicitParser(this);
    }

}

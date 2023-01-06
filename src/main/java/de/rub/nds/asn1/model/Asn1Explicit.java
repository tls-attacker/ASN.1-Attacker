/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.parser.Asn1ExplicitParser;
import de.rub.nds.asn1.preparator.GenericAsn1ContainerPreparator;
import de.rub.nds.asn1.preparator.Preparator;
import jakarta.xml.bind.annotation.XmlAnyElement;

public class Asn1Explicit<Chooser extends AbstractChooser> extends Asn1Container<Chooser> {

    @XmlAnyElement(lax = true)
    private Asn1Encodable child;

    public Asn1Explicit(String identifier, Asn1Encodable child) {
        super(identifier, TagClass.CONTEXT_SPECIFIC, TagConstructed.CONSTRUCTED, null);
        this.child = child;
        addChild(child);
    }

    @Override
    public Preparator getPreparator(Chooser chooser) {
        return new GenericAsn1ContainerPreparator(chooser, this);
    }

    public Asn1Encodable getChild() {
        return child;
    }

    @Override
    public Asn1ExplicitParser getParser(Chooser chooser) {
        return new Asn1ExplicitParser(chooser, this);
    }
}

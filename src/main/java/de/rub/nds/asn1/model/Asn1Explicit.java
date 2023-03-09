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
import de.rub.nds.asn1.handler.EmptyHandler;
import de.rub.nds.asn1.parser.Asn1ExplicitParser;
import de.rub.nds.asn1.preparator.GenericAsn1ContainerPreparator;
import jakarta.xml.bind.annotation.XmlAnyElement;

public class Asn1Explicit<Chooser extends AbstractChooser> extends Asn1Container<Chooser> {

    @XmlAnyElement(lax = true)
    private Asn1Encodable<Chooser> child;

    // TODO review whats going on here
    public Asn1Explicit(String identifier, Asn1Encodable<Chooser> child) {
        super(identifier, TagClass.CONTEXT_SPECIFIC, TagConstructed.CONSTRUCTED, null);
        this.child = child;
        addChild(child);
    }

    @Override
    public GenericAsn1ContainerPreparator<Chooser> getPreparator(Chooser chooser) {
        return new GenericAsn1ContainerPreparator<>(chooser, this);
    }

    public Asn1Encodable<Chooser> getChild() {
        return child;
    }

    @Override
    public Asn1ExplicitParser<Chooser> getParser(Chooser chooser) {
        return new Asn1ExplicitParser<>(chooser, this);
    }

    @Override
    public EmptyHandler<Chooser> getHandler(Chooser chooser) {
        return new EmptyHandler<>(chooser);
    }
}

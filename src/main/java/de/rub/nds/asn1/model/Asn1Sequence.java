/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.TagNumber;
import de.rub.nds.asn1.parser.Asn1FieldParser;
import de.rub.nds.asn1.parser.Asn1SequenceParser;
import de.rub.nds.asn1.preparator.GenericAsn1ContainerPreparator;
import de.rub.nds.asn1.preparator.Preparator;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Asn1Sequence extends Asn1Container {

    public Asn1Sequence(String identifier) {
        super(identifier, TagClass.UNIVERSAL, TagConstructed.CONSTRUCTED, TagNumber.SEQUENCE);
    }

    @Override
    public Preparator getGenericPreparator() {
        return new GenericAsn1ContainerPreparator(this);
    }

    @Override
    public Asn1FieldParser<Asn1Sequence> getParser() {
        return new Asn1SequenceParser(this);
    }
}

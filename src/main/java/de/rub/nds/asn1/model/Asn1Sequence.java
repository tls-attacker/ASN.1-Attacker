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
import de.rub.nds.asn1.constants.UniversalTagNumber;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Asn1Sequence extends Asn1Container {

    /** Private no-arg constructor to please JAXB */
    private Asn1Sequence() {
        super(null, TagClass.UNIVERSAL, TagConstructed.CONSTRUCTED, UniversalTagNumber.SEQUENCE);
    }

    public Asn1Sequence(String identifier) {
        super(
                identifier,
                TagClass.UNIVERSAL,
                TagConstructed.CONSTRUCTED,
                UniversalTagNumber.SEQUENCE);
    }

    public Asn1Sequence(String identifier, int implicitTagNumber) {
        super(identifier, implicitTagNumber);
    }
}

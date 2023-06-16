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

public abstract class Asn1Explicit<InnerField extends Asn1Field> extends Asn1Field {

    private final InnerField innerField;

    public Asn1Explicit(String identifier, Integer expectedTagNumber, InnerField innerField) {
        super(identifier, TagClass.CONTEXT_SPECIFIC, TagConstructed.CONSTRUCTED, expectedTagNumber);
        this.innerField = innerField;
    }

    public InnerField getInnerField() {
        return innerField;
    }
}

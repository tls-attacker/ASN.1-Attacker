/*
 * ASN.1-Attacker - A library for arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.UniversalTagNumber;

public class Asn1UnknownField extends Asn1Field implements PrimitiveAsn1Field {

    private byte[] contentConfig;

    /** Private no-arg constructor to please JAXB */
    private Asn1UnknownField() {
        super(null, null, null, (UniversalTagNumber) null);
    }

    public Asn1UnknownField(
            String identifier,
            TagClass tagClass,
            TagConstructed tagConstructed,
            Integer tagNumber) {
        super(identifier, tagClass, tagConstructed, tagNumber);
    }

    public byte[] getContentConfig() {
        return contentConfig;
    }

    public void setContentConfig(byte[] contentConfig) {
        this.contentConfig = contentConfig;
    }
}

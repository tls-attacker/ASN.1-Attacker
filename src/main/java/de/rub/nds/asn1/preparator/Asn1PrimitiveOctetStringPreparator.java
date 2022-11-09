/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.model.Asn1PrimitiveOctetString;

public class Asn1PrimitiveOctetStringPreparator extends Asn1FieldPreparator {

    private final Asn1PrimitiveOctetString asn1PrimitiveOctetString;

    public Asn1PrimitiveOctetStringPreparator(Asn1PrimitiveOctetString asn1PrimitiveOctetString) {
        super(asn1PrimitiveOctetString);
        this.asn1PrimitiveOctetString = asn1PrimitiveOctetString;
    }

    @Override
    protected byte[] encodeContent() {
        return this.asn1PrimitiveOctetString.getValue().getValue();
    }
}

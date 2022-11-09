/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.model.Asn1PrimitiveGeneralizedTime;

public class Asn1PrimitiveGeneralizedTimePreparator extends Asn1FieldPreparator {

    private final Asn1PrimitiveGeneralizedTime asn1PrimitiveGeneralizedTime;

    public Asn1PrimitiveGeneralizedTimePreparator(final Asn1PrimitiveGeneralizedTime asn1PrimitiveGeneralizedTime) {
        super(asn1PrimitiveGeneralizedTime);
        this.asn1PrimitiveGeneralizedTime = asn1PrimitiveGeneralizedTime;
    }

    @Override
    protected byte[] encodeContent() {
        return this.asn1PrimitiveGeneralizedTime.getValue().getOriginalValue().getBytes();
    }
}

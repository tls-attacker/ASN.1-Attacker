/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.model.Asn1PrimitiveGeneralizedTime;

public class Asn1PrimitiveGeneralizedTimePreparator<Chooser extends AbstractChooser>
        extends Asn1FieldPreparator<Chooser, Asn1PrimitiveGeneralizedTime<Chooser>> {

    private final Asn1PrimitiveGeneralizedTime asn1PrimitiveGeneralizedTime;

    public Asn1PrimitiveGeneralizedTimePreparator(
            Chooser chooser, final Asn1PrimitiveGeneralizedTime asn1PrimitiveGeneralizedTime) {
        super(chooser, asn1PrimitiveGeneralizedTime);
        this.asn1PrimitiveGeneralizedTime = asn1PrimitiveGeneralizedTime;
    }

    @Override
    protected byte[] encodeContent() {
        return this.asn1PrimitiveGeneralizedTime.getValue().getOriginalValue().getBytes();
    }
}

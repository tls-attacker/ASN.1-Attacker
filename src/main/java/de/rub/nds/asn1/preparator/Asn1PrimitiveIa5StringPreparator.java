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
import de.rub.nds.asn1.model.Asn1PrimitiveIa5String;

public class Asn1PrimitiveIa5StringPreparator<Chooser extends AbstractChooser>
        extends Asn1FieldPreparator<Chooser, Asn1PrimitiveIa5String<Chooser>> {

    private final Asn1PrimitiveIa5String asn1PrimitiveIa5String;

    public Asn1PrimitiveIa5StringPreparator(
            Chooser chooser, Asn1PrimitiveIa5String asn1PrimitiveIa5String) {
        super(chooser, asn1PrimitiveIa5String);
        this.asn1PrimitiveIa5String = asn1PrimitiveIa5String;
    }

    @Override
    protected byte[] encodeContent() {
        return this.asn1PrimitiveIa5String.getValue().getValue().getBytes();
    }
}

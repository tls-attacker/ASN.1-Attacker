/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.serializer.*;
import de.rub.nds.asn1.model.Asn1PrimitiveIa5String;

public class Asn1PrimitiveIa5StringPreparator extends Asn1FieldPreparator {

    private final Asn1PrimitiveIa5String asn1PrimitiveIa5String;

    public Asn1PrimitiveIa5StringPreparator(Asn1PrimitiveIa5String asn1PrimitiveIa5String) {
        super(asn1PrimitiveIa5String);
        this.asn1PrimitiveIa5String = asn1PrimitiveIa5String;
    }

    @Override
    public void updateLayers() {
        this.encodePrimitiveIa5String();
        super.updateLayers();
    }

    private void encodePrimitiveIa5String() {
        byte[] content = this.asn1PrimitiveIa5String.getValue().getBytes();
        this.asn1PrimitiveIa5String.setContent(content);
    }
}

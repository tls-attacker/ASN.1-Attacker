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
import de.rub.nds.asn1.model.Asn1PrimitiveUtf8String;

public class Asn1PrimitiveUtf8StringPreparator extends Asn1FieldPreparator {

    private final Asn1PrimitiveUtf8String asn1PrimitiveUtf8String;

    public Asn1PrimitiveUtf8StringPreparator(Asn1PrimitiveUtf8String asn1PrimitiveUtf8String) {
        super(asn1PrimitiveUtf8String);
        this.asn1PrimitiveUtf8String = asn1PrimitiveUtf8String;
    }

    @Override
    public void updateLayers() {
        this.encodePrimitiveUtf8String();
        super.updateLayers();
    }

    private void encodePrimitiveUtf8String() {
        byte[] content = this.asn1PrimitiveUtf8String.getValue().getBytes();
        this.asn1PrimitiveUtf8String.setContent(content);
    }
}

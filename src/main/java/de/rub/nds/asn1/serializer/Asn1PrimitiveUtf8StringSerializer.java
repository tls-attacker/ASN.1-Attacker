/*
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2020 Ruhr University Bochum, Paderborn University,
 * and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.rub.nds.asn1.serializer;

import de.rub.nds.asn1.model.Asn1PrimitiveUtf8String;

public class Asn1PrimitiveUtf8StringSerializer extends Asn1FieldSerializer {

    private final Asn1PrimitiveUtf8String asn1PrimitiveUtf8String;

    public Asn1PrimitiveUtf8StringSerializer(Asn1PrimitiveUtf8String asn1PrimitiveUtf8String) {
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

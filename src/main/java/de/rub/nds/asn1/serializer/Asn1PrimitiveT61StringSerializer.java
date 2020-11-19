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

import de.rub.nds.asn1.model.Asn1PrimitiveT61String;

public class Asn1PrimitiveT61StringSerializer extends Asn1FieldSerializer {

    private final Asn1PrimitiveT61String asn1PrimitiveT61String;

    public Asn1PrimitiveT61StringSerializer(Asn1PrimitiveT61String asn1PrimitiveT61String) {
        super(asn1PrimitiveT61String);
        this.asn1PrimitiveT61String = asn1PrimitiveT61String;
    }

    @Override
    public void updateLayers() {
        this.encodePrimitiveT61String();
        super.updateLayers();
    }

    private void encodePrimitiveT61String() {
        byte[] content = this.asn1PrimitiveT61String.getValue().getBytes();
        // Todo: Character set conversion
        this.asn1PrimitiveT61String.setContent(content);
    }
}

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

import de.rub.nds.asn1.model.Asn1PrimitiveGeneralizedTime;

public class Asn1PrimitiveGeneralizedTimeSerializer extends Asn1FieldSerializer {

    private final Asn1PrimitiveGeneralizedTime asn1PrimitiveGeneralizedTime;

    public Asn1PrimitiveGeneralizedTimeSerializer(final Asn1PrimitiveGeneralizedTime asn1PrimitiveGeneralizedTime) {
        super(asn1PrimitiveGeneralizedTime);
        this.asn1PrimitiveGeneralizedTime = asn1PrimitiveGeneralizedTime;
    }

    @Override
    public void updateLayers() {
        this.encodePrimitiveIa5String();
        super.updateLayers();
    }

    private void encodePrimitiveIa5String() {
        byte[] content = this.asn1PrimitiveGeneralizedTime.getValue().getBytes();
        this.asn1PrimitiveGeneralizedTime.setContent(content);
    }
}

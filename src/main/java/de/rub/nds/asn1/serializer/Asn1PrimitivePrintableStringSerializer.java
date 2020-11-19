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

import de.rub.nds.asn1.model.Asn1PrimitivePrintableString;

public class Asn1PrimitivePrintableStringSerializer extends Asn1FieldSerializer {

    private final Asn1PrimitivePrintableString asn1PrimitivePrintableString;

    public Asn1PrimitivePrintableStringSerializer(Asn1PrimitivePrintableString asn1PrimitivePrintableString) {
        super(asn1PrimitivePrintableString);
        this.asn1PrimitivePrintableString = asn1PrimitivePrintableString;
    }

    @Override
    public void updateLayers() {
        this.encodePrimitivePrintableString();
        super.updateLayers();
    }

    private void encodePrimitivePrintableString() {
        byte[] content = this.asn1PrimitivePrintableString.getValue().getBytes();
        this.asn1PrimitivePrintableString.setContent(content);
    }
}

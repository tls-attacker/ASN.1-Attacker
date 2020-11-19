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

import de.rub.nds.asn1.model.Asn1PrimitiveBitString;
import de.rub.nds.util.ByteArrayUtils;

public class Asn1PrimitiveBitStringSerializer extends Asn1FieldSerializer {

    private final Asn1PrimitiveBitString asn1PrimitiveBitString;

    public Asn1PrimitiveBitStringSerializer(final Asn1PrimitiveBitString asn1PrimitiveBitString) {
        super(asn1PrimitiveBitString);
        this.asn1PrimitiveBitString = asn1PrimitiveBitString;
    }

    @Override
    public void updateLayers() {
        this.encodePrimitiveBitString();
        super.updateLayers();
    }

    private void encodePrimitiveBitString() {
        byte[] content = new byte[] { (byte) this.asn1PrimitiveBitString.getUnusedBits() };
        content = ByteArrayUtils.merge(content, this.asn1PrimitiveBitString.getValue());
        this.asn1PrimitiveBitString.setContent(content);
    }
}

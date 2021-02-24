/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
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

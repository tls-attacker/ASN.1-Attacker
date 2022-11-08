/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.serializer;

import de.rub.nds.asn1.model.Asn1Boolean;

public class Asn1BooleanSerializer extends Asn1FieldSerializer {

    private final Asn1Boolean asn1Boolean;

    public Asn1BooleanSerializer(final Asn1Boolean asn1Boolean) {
        super(asn1Boolean);
        this.asn1Boolean = asn1Boolean;
    }

    @Override
    public void updateLayers() {
        this.encodeBoolean();
        super.updateLayers();
    }

    private void encodeBoolean() {
        byte[] content = new byte[] { 0 };
        if (this.asn1Boolean.getValue().getValue()) {
            content[0] = (byte) 0xFF;
        }
        this.asn1Boolean.setContent(content);
    }
}

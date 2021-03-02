/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.serializer;

import de.rub.nds.asn1.model.Asn1Integer;

public class Asn1IntegerSerializer extends Asn1FieldSerializer {

    private final Asn1Integer asn1Integer;

    public Asn1IntegerSerializer(final Asn1Integer asn1Integer) {
        super(asn1Integer);
        this.asn1Integer = asn1Integer;
    }

    @Override
    public void updateLayers() {
        this.encodeInteger();
        super.updateLayers();
    }

    private void encodeInteger() {
        byte[] content = this.asn1Integer.getValue().toByteArray();
        this.asn1Integer.setContent(content);
    }
}

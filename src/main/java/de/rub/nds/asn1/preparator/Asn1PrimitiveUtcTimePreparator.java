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
import de.rub.nds.asn1.model.Asn1PrimitiveUtcTime;

public class Asn1PrimitiveUtcTimePreparator extends Asn1FieldPreparator {

    private final Asn1PrimitiveUtcTime asn1PrimitiveUtcTime;

    public Asn1PrimitiveUtcTimePreparator(Asn1PrimitiveUtcTime asn1PrimitiveUtcTime) {
        super(asn1PrimitiveUtcTime);
        this.asn1PrimitiveUtcTime = asn1PrimitiveUtcTime;
    }

    @Override
    public void updateLayers() {
        this.encodePrimitiveUtcTime();
        super.updateLayers();
    }

    private void encodePrimitiveUtcTime() {
        byte[] content = this.asn1PrimitiveUtcTime.getValue().getBytes();
        this.asn1PrimitiveUtcTime.setContent(content);
    }
}

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
import de.rub.nds.asn1.model.Asn1PrimitiveOctetString;

public class Asn1PrimitiveOctetStringPreparator extends Asn1FieldPreparator {

    private final Asn1PrimitiveOctetString asn1PrimitiveOctetString;

    public Asn1PrimitiveOctetStringPreparator(Asn1PrimitiveOctetString asn1PrimitiveOctetString) {
        super(asn1PrimitiveOctetString);
        this.asn1PrimitiveOctetString = asn1PrimitiveOctetString;
    }

    @Override
    public void updateLayers() {
        this.encodePrimitiveOctetString();
        super.updateLayers();
    }

    private void encodePrimitiveOctetString() {
        byte[] content = this.asn1PrimitiveOctetString.getValue();
        this.asn1PrimitiveOctetString.setContent(content);
    }
}

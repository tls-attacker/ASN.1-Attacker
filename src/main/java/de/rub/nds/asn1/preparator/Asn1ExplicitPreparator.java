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
import de.rub.nds.asn1.model.Asn1Explicit;

public class Asn1ExplicitPreparator extends Asn1FieldPreparator {

    private final Asn1Explicit asn1Explicit;

    public Asn1ExplicitPreparator(final Asn1Explicit asn1Explicit) {
        super(asn1Explicit);
        this.asn1Explicit = asn1Explicit;
    }

    @Override
    public void updateLayers() {
        this.encodeExplicit();
        super.updateLayers();
    }

    private void encodeExplicit() {
        int offset = this.asn1Explicit.getOffset();
        this.asn1Explicit.getTagNumber().setOriginalValue(offset);
        this.asn1Explicit.setContent(this.asn1Explicit.getEncodedChildren());
    }
}

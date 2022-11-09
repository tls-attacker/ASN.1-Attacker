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
import de.rub.nds.asn1.model.Asn1RawField;
import de.rub.nds.util.ByteArrayUtils;

public class Asn1RawFieldPreparator extends Asn1Serializer {

    private final Asn1RawField rawField;

    public Asn1RawFieldPreparator(final Asn1RawField rawField) {
        this.rawField = rawField;
    }


    @Override
    public final byte[] serialize() {
        this.updateLayers();
        return ByteArrayUtils.merge(this.rawField.getIdentifierOctets().getValue(),
            this.rawField.getLengthOctets().getValue(), this.rawField.getContentOctets().getValue());
    }
}

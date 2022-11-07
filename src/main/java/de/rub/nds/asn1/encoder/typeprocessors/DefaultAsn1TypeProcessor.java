/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.encoder.typeprocessors;

import de.rub.nds.asn1.Asn1Encodable;

public class DefaultAsn1TypeProcessor extends Asn1TypeProcessor {

    public DefaultAsn1TypeProcessor(final Asn1Encodable asn1Encodable) {
        super(asn1Encodable);
    }

    @Override
    public byte[] encode() {
        return this.asn1Encodable.getSerializer().serialize();
    }
}

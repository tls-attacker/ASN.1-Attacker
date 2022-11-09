/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.model.Asn1Integer;

public class Asn1IntegerPreparator extends Asn1FieldPreparator {

    private final Asn1Integer asn1Integer;

    public Asn1IntegerPreparator(final Asn1Integer asn1Integer) {
        super(asn1Integer);
        this.asn1Integer = asn1Integer;
    }

    @Override
    protected byte[] encodeContent() {
        return this.asn1Integer.getValue().getByteArray();
    }
}

/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.model.Asn1Enumerated;

public class Asn1EnumeratedPreparator extends Asn1FieldPreparator {

    private final Asn1Enumerated asn1Enumerated;

    public Asn1EnumeratedPreparator(final Asn1Enumerated asn1Enumerated) {
        super(asn1Enumerated);
        this.asn1Enumerated = asn1Enumerated;
    }

    @Override
    protected byte[] encodeContent() {
        return this.asn1Enumerated.getValue().getByteArray();
    }
}

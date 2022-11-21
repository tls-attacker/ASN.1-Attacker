/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.model.Asn1Null;

public class Asn1NullPreparator extends Asn1FieldPreparator {

    public Asn1NullPreparator(final Asn1Null asn1null) {
        super(asn1null);
    }

    @Override
    protected byte[] encodeContent() {
        return new byte[0];
    }
}

/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.model.Asn1Boolean;

public class Asn1BooleanPreparator extends Asn1FieldPreparator {

    private final Asn1Boolean asn1Boolean;

    public Asn1BooleanPreparator(final Asn1Boolean asn1Boolean) {
        super(asn1Boolean);
        this.asn1Boolean = asn1Boolean;
    }

    @Override
    protected byte[] encodeContent() {
        if (this.asn1Boolean.getValue().getValue()) {
            return new byte[]{(byte) 0xFF};
        } else {
            return new byte[1];
        }
    }
}

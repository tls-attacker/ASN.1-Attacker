/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.model.Asn1UnknownField;

public class Asn1UnknownPreparator extends Asn1FieldPreparator {

    private final Asn1UnknownField asn1Unknown;

    public Asn1UnknownPreparator(final Asn1UnknownField asn1UnknownField) {
        super(asn1UnknownField);
        this.asn1Unknown = asn1UnknownField;
    }

    @Override
    protected byte[] encodeContent() {
        return asn1Unknown.getContentConfig();
    }
}

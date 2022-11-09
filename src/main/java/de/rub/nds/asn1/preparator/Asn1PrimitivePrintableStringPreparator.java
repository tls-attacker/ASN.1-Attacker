/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.model.Asn1PrimitivePrintableString;

public class Asn1PrimitivePrintableStringPreparator extends Asn1FieldPreparator {

    private final Asn1PrimitivePrintableString asn1PrimitivePrintableString;

    public Asn1PrimitivePrintableStringPreparator(Asn1PrimitivePrintableString asn1PrimitivePrintableString) {
        super(asn1PrimitivePrintableString);
        this.asn1PrimitivePrintableString = asn1PrimitivePrintableString;
    }

    @Override
    protected byte[] encodeContent() {
        return this.asn1PrimitivePrintableString.getValue().getValue().getBytes();
    }
}

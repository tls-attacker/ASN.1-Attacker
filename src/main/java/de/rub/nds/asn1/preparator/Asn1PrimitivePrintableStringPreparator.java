/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.model.Asn1PrimitivePrintableString;

public class Asn1PrimitivePrintableStringPreparator<Chooser extends AbstractChooser>
        extends Asn1FieldPreparator<Chooser, Asn1PrimitivePrintableString<Chooser>> {

    private final Asn1PrimitivePrintableString asn1PrimitivePrintableString;

    public Asn1PrimitivePrintableStringPreparator(
            Chooser chooser, Asn1PrimitivePrintableString asn1PrimitivePrintableString) {
        super(chooser, asn1PrimitivePrintableString);
        this.asn1PrimitivePrintableString = asn1PrimitivePrintableString;
    }

    @Override
    protected byte[] encodeContent() {
        return this.asn1PrimitivePrintableString.getValue().getValue().getBytes();
    }
}

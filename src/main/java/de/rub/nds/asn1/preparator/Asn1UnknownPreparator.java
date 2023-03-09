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
import de.rub.nds.asn1.model.Asn1UnknownField;

public class Asn1UnknownPreparator<Chooser extends AbstractChooser>
        extends Asn1FieldPreparator<Chooser, Asn1UnknownField<Chooser>> {

    private final Asn1UnknownField<Chooser> asn1Unknown;

    public Asn1UnknownPreparator(
            Chooser chooser, final Asn1UnknownField<Chooser> asn1UnknownField) {
        super(chooser, asn1UnknownField);
        this.asn1Unknown = asn1UnknownField;
    }

    @Override
    protected byte[] encodeContent() {
        return asn1Unknown.getContentConfig();
    }
}

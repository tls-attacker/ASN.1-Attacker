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
import de.rub.nds.asn1.model.Asn1Boolean;

public class Asn1BooleanPreparator<Chooser extends AbstractChooser>
        extends Asn1FieldPreparator<Chooser, Asn1Boolean<Chooser>> {

    private final Asn1Boolean<Chooser> asn1Boolean;

    public Asn1BooleanPreparator(Chooser chooser, final Asn1Boolean<Chooser> asn1Boolean) {
        super(chooser, asn1Boolean);
        this.asn1Boolean = asn1Boolean;
    }

    @Override
    protected byte[] encodeContent() {
        if (this.asn1Boolean.getValue().getValue()) {
            return new byte[] {(byte) 0xFF};
        } else {
            return new byte[1];
        }
    }
}

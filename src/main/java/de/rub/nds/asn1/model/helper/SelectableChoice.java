/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.model.helper;

import de.rub.nds.asn1.model.Asn1Field;
import de.rub.nds.modifiablevariable.HoldsModifiableVariable;

public class SelectableChoice {

    @HoldsModifiableVariable
    private final Asn1Field field;

    public SelectableChoice(Asn1Field field) {
        this.field = field;
    }

    public boolean isSelectable(byte[] tag) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    public Asn1Field getField() {
        return field;
    }
}

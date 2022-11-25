/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model.helper;

import de.rub.nds.asn1.model.Asn1Field;
import de.rub.nds.modifiablevariable.HoldsModifiableVariable;

public class SelectableChoice {

    @HoldsModifiableVariable private final Asn1Field field;

    public SelectableChoice(Asn1Field field) {
        this.field = field;
    }

    public boolean isSelectable(byte[] tag) {
        int tagNumber = field.getParser().parseTagNumber(tag);
        // TODO we currently only check tag number - maybe we should also check the rest of the tag
        if (field.getTagNumberType().getIntValue() == tagNumber) {
            return true;
        } else {
            return false;
        }
    }

    public Asn1Field getField() {
        return field;
    }
}

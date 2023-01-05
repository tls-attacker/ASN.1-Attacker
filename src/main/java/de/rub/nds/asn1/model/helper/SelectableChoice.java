/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model.helper;

import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.model.Asn1Field;
import de.rub.nds.modifiablevariable.HoldsModifiableVariable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class SelectableChoice<Context extends AbstractChooser> {

    @HoldsModifiableVariable private final Asn1Field field;

    /** Private no-arg constructor to please JAXB */
    private SelectableChoice() {
        field = null;
    }

    public SelectableChoice(Asn1Field field) {
        this.field = field;
    }

    public boolean isSelectable(Context context, byte[] tag) {
        int tagNumber = field.getParser(context).parseTagNumber(tag);
        boolean constructed = field.getParser(context).parseTagConstructed(tag[0]);
        int tagClass = field.getParser(context).parseTagClass(tag[0]);
        if (field.getTagNumberType().getIntValue() == tagNumber
                && field.getTagConstructedType().getBooleanValue() == constructed
                && tagClass == field.getTagClassType().getIntValue()) {
            return true;
        } else {
            return false;
        }
    }

    public Asn1Field getField() {
        return field;
    }
}

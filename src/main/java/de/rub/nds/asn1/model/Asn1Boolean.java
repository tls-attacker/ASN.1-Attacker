/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.model;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.TagNumber;
import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.bool.ModifiableBoolean;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Asn1Boolean extends Asn1Field {

    @XmlElement(name = "value")
    private ModifiableBoolean value;

    public Asn1Boolean() {
        super(TagClass.UNIVERSAL, TagConstructed.CONSTRUCTED, TagNumber.BOOLEAN);
    }

    public ModifiableBoolean getValue() {
        return value;
    }

    public void setValue(ModifiableBoolean value) {
        this.value = value;
    }

    public void setValue(boolean value) {
        this.value = ModifiableVariableFactory.safelySetValue(this.value, value);
    }

}

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
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;
import de.rub.nds.modifiablevariable.singlebyte.ModifiableByte;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Asn1PrimitiveBitString extends Asn1Field {

    @XmlElement(name = "unusedBits")
    private ModifiableByte unusedBits;

    @XmlElement(name = "value")
    private ModifiableByteArray value;

    public Asn1PrimitiveBitString() {
        super(TagClass.UNIVERSAL, TagConstructed.PRIMITIVE, TagNumber.BIT_STRING);
    }

    public ModifiableByte getUnusedBits() {
        return unusedBits;
    }

    public void setUnusedBits(ModifiableByte unusedBits) {
        this.unusedBits = unusedBits;
    }

    public ModifiableByteArray getValue() {
        return value;
    }

    public void setValue(ModifiableByteArray value) {
        this.value = value;
    }

}

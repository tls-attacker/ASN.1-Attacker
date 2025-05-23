/*
 * ASN.1-Attacker - A Library for Arbitrary ASN.1 Structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.UniversalTagNumber;
import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;
import de.rub.nds.modifiablevariable.singlebyte.ModifiableByte;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/** Unused bits | Used bits | padding */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Asn1BitString extends Asn1Field implements PrimitiveAsn1Field {

    @XmlElement(name = "unusedBits")
    private ModifiableByte unusedBits;

    @XmlElement(name = "usedBits")
    private ModifiableByteArray usedBits;

    @XmlElement(name = "padding")
    private ModifiableByte padding;

    /** Private no-arg constructor to please JAXB */
    @SuppressWarnings("unused")
    private Asn1BitString() {
        super(null, TagClass.UNIVERSAL, TagConstructed.PRIMITIVE, UniversalTagNumber.BIT_STRING);
    }

    public Asn1BitString(String identifier) {
        super(
                identifier,
                TagClass.UNIVERSAL,
                TagConstructed.PRIMITIVE,
                UniversalTagNumber.BIT_STRING);
    }

    public Asn1BitString(String identifier, int implicitTagNumber) {
        super(identifier, TagClass.CONTEXT_SPECIFIC, TagConstructed.PRIMITIVE, implicitTagNumber);
    }

    public ModifiableByte getUnusedBits() {
        return unusedBits;
    }

    public void setUnusedBits(ModifiableByte unusedBits) {
        this.unusedBits = unusedBits;
    }

    public void setUnusedBits(byte unusedBits) {
        this.unusedBits = ModifiableVariableFactory.safelySetValue(this.unusedBits, unusedBits);
    }

    public ModifiableByteArray getUsedBits() {
        return usedBits;
    }

    public void setUsedBits(ModifiableByteArray usedBits) {
        this.usedBits = usedBits;
    }

    public void setUsedBits(byte[] usedBits) {
        this.usedBits = ModifiableVariableFactory.safelySetValue(this.usedBits, usedBits);
    }

    public ModifiableByte getPadding() {
        return padding;
    }

    public void setPadding(ModifiableByte padding) {
        this.padding = padding;
    }

    public void setPadding(byte padding) {
        this.padding = ModifiableVariableFactory.safelySetValue(this.padding, padding);
    }
}

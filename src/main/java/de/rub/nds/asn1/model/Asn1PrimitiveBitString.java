/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.TagNumber;
import de.rub.nds.asn1.parser.Asn1FieldParser;
import de.rub.nds.asn1.parser.Asn1PrimitiveBitStringParser;
import de.rub.nds.asn1.preparator.Asn1PrimitiveBitStringPreparator;
import de.rub.nds.asn1.preparator.Preparator;
import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;
import de.rub.nds.modifiablevariable.singlebyte.ModifiableByte;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Asn1PrimitiveBitString extends Asn1Field {

    @XmlElement(name = "unusedBits")
    private ModifiableByte unusedBits;

    @XmlElement(name = "value")
    private ModifiableByteArray value;

    /** Private no-arg constructor to please JAXB */
    private Asn1PrimitiveBitString() {
        super(null);
    }

    public Asn1PrimitiveBitString(String identifier) {
        super(identifier, TagClass.UNIVERSAL, TagConstructed.PRIMITIVE, TagNumber.BIT_STRING);
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

    public ModifiableByteArray getValue() {
        return value;
    }

    public void setValue(ModifiableByteArray value) {
        this.value = value;
    }

    public void setValue(byte[] value) {
        this.value = ModifiableVariableFactory.safelySetValue(this.value, value);
    }

    @Override
    public Preparator getGenericPreparator() {
        return new Asn1PrimitiveBitStringPreparator(this);
    }

    @Override
    public Asn1FieldParser<?> getParser() {
        return new Asn1PrimitiveBitStringParser(this);
    }
}

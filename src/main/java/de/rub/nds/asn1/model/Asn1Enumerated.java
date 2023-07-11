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
import de.rub.nds.asn1.constants.UniversalTagNumber;
import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.biginteger.ModifiableBigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Asn1Enumerated extends Asn1Field implements PrimitiveAsn1Field {

    @XmlElement(name = "value")
    private ModifiableBigInteger value;

    public Asn1Enumerated(String identifier) {
        super(identifier, TagClass.UNIVERSAL, TagConstructed.PRIMITIVE, UniversalTagNumber.ENUMERATED);
    }

    /** Private no-arg constructor to please JAXB */
    private Asn1Enumerated() {
        super(null, TagClass.UNIVERSAL, TagConstructed.PRIMITIVE, UniversalTagNumber.ENUMERATED);
    }

    public Asn1Enumerated(String identifier, int implicitTagNumber) {
        super(identifier, TagClass.UNIVERSAL, TagConstructed.PRIMITIVE, implicitTagNumber);
    }

    public ModifiableBigInteger getValue() {
        return value;
    }

    public void setValue(ModifiableBigInteger value) {
        this.value = value;
    }

    public void setValue(BigInteger value) {
        this.value = ModifiableVariableFactory.safelySetValue(this.value, value);
    }
}

/*
 * ASN.1-Attacker - A library for arbitrary ASN.1 structures
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
import de.rub.nds.modifiablevariable.string.ModifiableString;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Asn1BmpString extends Asn1Field implements PrimitiveAsn1Field {

    @XmlElement(name = "value")
    private ModifiableString value;

    /** Private no-arg constructor to please JAXB */
    private Asn1BmpString() {
        super(null, TagClass.UNIVERSAL, TagConstructed.PRIMITIVE, UniversalTagNumber.BMPSTRING);
    }

    public Asn1BmpString(String identifier) {
        super(
                identifier,
                TagClass.UNIVERSAL,
                TagConstructed.PRIMITIVE,
                UniversalTagNumber.BMPSTRING);
    }

    public Asn1BmpString(String identifier, int implicitTagNumber) {
        super(identifier, TagClass.CONTEXT_SPECIFIC, TagConstructed.PRIMITIVE, implicitTagNumber);
    }

    public ModifiableString getValue() {
        return value;
    }

    public void setValue(ModifiableString value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = ModifiableVariableFactory.safelySetValue(this.value, value);
    }
}

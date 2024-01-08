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
import de.rub.nds.asn1.time.TimeDecoder;
import de.rub.nds.asn1.time.TimeField;
import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.string.ModifiableString;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.joda.time.DateTime;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Asn1GeneralizedTime extends Asn1Field implements PrimitiveAsn1Field, TimeField {

    @XmlElement(name = "value")
    private ModifiableString value;

    /** Private no-arg constructor to please JAXB */
    @SuppressWarnings("unused")
    private Asn1GeneralizedTime() {
        super(
                null,
                TagClass.UNIVERSAL,
                TagConstructed.PRIMITIVE,
                UniversalTagNumber.GENERALIZEDTIME);
    }

    public Asn1GeneralizedTime(String identifier) {
        super(
                identifier,
                TagClass.UNIVERSAL,
                TagConstructed.PRIMITIVE,
                UniversalTagNumber.GENERALIZEDTIME);
    }

    public Asn1GeneralizedTime(String identifier, int implicitTagNumber) {
        super(identifier, TagClass.CONTEXT_SPECIFIC, TagConstructed.PRIMITIVE, implicitTagNumber);
    }

    public ModifiableString getValue() {
        return value;
    }

    public void setValue(ModifiableString value) {
        this.value = value;
    }

    @Override
    public void setValue(String value) {
        this.value = ModifiableVariableFactory.safelySetValue(this.value, value);
    }

    @Override
    public DateTime getTimeValue() {
        return TimeDecoder.decodeGeneralizedTimeUtc(value.getValue());
    }
}

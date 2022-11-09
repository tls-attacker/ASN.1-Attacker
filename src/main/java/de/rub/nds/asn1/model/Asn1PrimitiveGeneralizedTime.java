/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.serializer.Asn1PrimitiveGeneralizedTimeSerializer;
import de.rub.nds.modifiablevariable.string.ModifiableString;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Asn1PrimitiveGeneralizedTime extends Asn1Field {

    @XmlElement(name = "value")
    private ModifiableString value;

    public Asn1PrimitiveGeneralizedTime() {
        super(TagClass.UNIVERSAL,TagConstructed.PRIMITIVE,TagNumber.GENERALIZEDTIME);
    }

    public ModifiableString getValue() {
        return value;
    }

    public void setValue(ModifiableString value) {
        this.value = value;
    }

    @Override
    public Asn1PrimitiveGeneralizedTimeSerializer getSerializer() {
        return new Asn1PrimitiveGeneralizedTimeSerializer(this);
    }
}

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
import de.rub.nds.asn1.serializer.Asn1BooleanSerializer;
import de.rub.nds.asn1.serializer.Asn1Serializer;
import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.bool.ModifiableBoolean;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Asn1Boolean extends Asn1Field {

    private static final int TAG_CLASS = TagClass.UNIVERSAL.getIntValue();

    private static final boolean TAG_CONSTRUCTED = TagConstructed.PRIMITIVE.getBooleanValue();

    private static final int TAG_NUMBER = TagNumber.BOOLEAN.getIntValue();

    @XmlElement(name = "value")
    private ModifiableBoolean value;

    public Asn1Boolean() {
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

    @Override
    public Asn1Serializer getSerializer() {
        return new Asn1BooleanSerializer(this);
    }
}

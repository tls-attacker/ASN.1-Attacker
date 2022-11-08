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
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.serializer.Asn1PrimitiveOctetStringSerializer;
import de.rub.nds.asn1.serializer.Asn1Serializer;
import de.rub.nds.modifiablevariable.util.UnformattedByteArrayAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Asn1PrimitiveOctetString extends Asn1Field {

    public static final int TAG_CLASS = TagClass.UNIVERSAL.getIntValue();

    public static final boolean IS_CONSTRUCTED = false;

    public static final int TAG_NUMBER = TagNumber.OCTET_STRING.getIntValue();

    @XmlJavaTypeAdapter(UnformattedByteArrayAdapter.class)
    @XmlElement(name = "value")
    private byte[] value = new byte[0];

    public Asn1PrimitiveOctetString() {
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    @Override
    public Asn1Serializer getSerializer() {
        return new Asn1PrimitiveOctetStringSerializer(this);
    }
}

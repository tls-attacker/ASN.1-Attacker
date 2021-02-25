/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.model;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.serializer.Asn1RawBytesSerializer;
import de.rub.nds.asn1.serializer.Asn1Serializer;
import de.rub.nds.modifiablevariable.util.ByteArrayAdapter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Asn1RawBytes extends Asn1Field {

    public static final int TAG_CLASS = TagClass.PRIVATE.getIntValue();

    public static final boolean IS_CONSTRUCTED = false;

    public static final int TAG_NUMBER = TagNumber.OCTET_STRING.getIntValue();

    @XmlJavaTypeAdapter(ByteArrayAdapter.class)
    @XmlElement(name = "value")
    private byte[] value = new byte[0];

    public Asn1RawBytes() {
        super(TAG_CLASS, IS_CONSTRUCTED, TAG_NUMBER);
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    @Override
    public Asn1Serializer getSerializer() {
        return new Asn1RawBytesSerializer(this);
    }
}

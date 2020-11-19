/*
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2020 Ruhr University Bochum, Paderborn University,
 * and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.rub.nds.asn1.model;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.serializer.Asn1BooleanSerializer;
import de.rub.nds.asn1.serializer.Asn1Serializer;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Asn1Boolean extends Asn1Field {

    private static final int TAG_CLASS = TagClass.UNIVERSAL.getIntValue();

    private static final boolean TAG_CONSTRUCTED = TagConstructed.PRIMITIVE.getBooleanValue();

    private static final int TAG_NUMBER = TagNumber.BOOLEAN.getIntValue();

    @XmlElement(name = "value")
    private boolean value = false;

    public Asn1Boolean() {
        super(TAG_CLASS, TAG_CONSTRUCTED, TAG_NUMBER);
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public Asn1Serializer getSerializer() {
        return new Asn1BooleanSerializer(this);
    }
}

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
import de.rub.nds.asn1.serializer.Asn1IntegerSerializer;
import de.rub.nds.asn1.serializer.Asn1Serializer;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Asn1Integer extends Asn1Field {

    private static final int TAG_CLASS = TagClass.UNIVERSAL.getIntValue();

    private static final boolean TAG_CONSTRUCTED = TagConstructed.PRIMITIVE.getBooleanValue();

    private static final int TAG_NUMBER = TagNumber.INTEGER.getIntValue();

    @XmlElement(name = "value")
    private BigInteger value = BigInteger.ZERO;

    public Asn1Integer() {
        super(TAG_CLASS, TAG_CONSTRUCTED, TAG_NUMBER);
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    @Override
    public Asn1Serializer getSerializer() {
        return new Asn1IntegerSerializer(this);
    }
}

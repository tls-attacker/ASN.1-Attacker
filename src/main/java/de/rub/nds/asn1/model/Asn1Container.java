/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.model;

import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1.serializer.Asn1Serializer;
import de.rub.nds.asn1.serializer.DefaultAsn1ContainerSerializer;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Asn1Container extends Asn1Field {

    @XmlTransient
    private byte[] encodedChildren = new byte[0];

    public Asn1Container() {
        this(0, false, 0);
    }

    public Asn1Container(final int tagClass, final boolean tagConstructed, final int tagNumber) {
        super(tagClass, tagConstructed, tagNumber);
    }

    public byte[] getEncodedChildren() {
        return encodedChildren;
    }

    public void setEncodedChildren(byte[] encodedChildren) {
        this.encodedChildren = encodedChildren;
    }

    public abstract void addChild(final Asn1Encodable child);

    public abstract void setChildren(final List<Asn1Encodable> children);

    public abstract List<Asn1Encodable> getChildren();

    public abstract void clearChildren();

    @Override
    public Asn1Serializer getSerializer() {
        return new DefaultAsn1ContainerSerializer(this);
    }
}

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
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Asn1Container extends Asn1Field {

    @XmlTransient
    private ModifiableByteArray encodedChildren;

    public Asn1Container() {
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

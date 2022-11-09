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
import de.rub.nds.asn1.serializer.Asn1FieldSerializer;
import de.rub.nds.asn1.serializer.GenericAsn1ContainerSerializer;
import de.rub.nds.modifiablevariable.HoldsModifiableVariable;
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.util.Collection;
import java.util.LinkedList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Asn1Container extends Asn1Field {

    @XmlTransient
    private ModifiableByteArray encodedChildren;

    @XmlAnyElement(lax = true)
    @HoldsModifiableVariable
    private Collection<Asn1Encodable> children = new LinkedList<>();

    public Asn1Container() {
    }

    public ModifiableByteArray getEncodedChildren() {
        return encodedChildren;
    }

    public void setEncodedChildren(ModifiableByteArray encodedChildren) {
        this.encodedChildren = encodedChildren;
    }

    public void addChild(final Asn1Encodable child) {
        this.children.add(child);
    }

    public Collection<Asn1Encodable> getChildren() {
        return children;
    }

    public void setChildren(Collection<Asn1Encodable> children) {
        this.children = children;
    }

    public void clearChildren() {
        this.children.clear();
    }

    @Override
    public Asn1FieldSerializer getSerializer() {
        return new GenericAsn1ContainerSerializer(this);
    }

}

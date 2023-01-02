/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.TagNumber;
import de.rub.nds.asn1.preparator.GenericAsn1ContainerPreparator;
import de.rub.nds.asn1.preparator.Preparator;
import de.rub.nds.modifiablevariable.HoldsModifiableVariable;
import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.LinkedList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Asn1Container extends Asn1Field {

    private ModifiableByteArray encodedChildren;

    @XmlElementWrapper @XmlElementRef @HoldsModifiableVariable
    private Collection<Asn1Encodable> children;

    public Asn1Container(
            String identifier,
            TagClass tagClassType,
            TagConstructed tagConstructedType,
            TagNumber tagNummerType) {
        super(identifier, tagClassType, tagConstructedType, tagNummerType);
        this.children = new LinkedList<>();
    }

    public ModifiableByteArray getEncodedChildren() {
        return encodedChildren;
    }

    public void setEncodedChildren(ModifiableByteArray encodedChildren) {
        this.encodedChildren = encodedChildren;
    }

    public void setEncodedChildren(byte[] encodedChildren) {
        this.encodedChildren =
                ModifiableVariableFactory.safelySetValue(this.encodedChildren, encodedChildren);
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
    public Preparator getGenericPreparator() {
        return new GenericAsn1ContainerPreparator(this);
    }
}

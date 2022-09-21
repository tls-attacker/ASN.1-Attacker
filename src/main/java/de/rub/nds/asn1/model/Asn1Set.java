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
import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.modifiablevariable.HoldsModifiableVariable;
import java.util.LinkedList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Asn1Set extends Asn1Container {

    public static final int TAG_CLASS = TagClass.UNIVERSAL.getIntValue();

    public static final boolean IS_CONSTRUCTED = true;

    public static final int TAG_NUMBER = TagNumber.SET.getIntValue();

    @XmlAnyElement(lax = true)
    @HoldsModifiableVariable
    private List<Asn1Encodable> children = new LinkedList<>();

    public Asn1Set() {
        super(TAG_CLASS, IS_CONSTRUCTED, TAG_NUMBER);
    }

    @Override
    public void addChild(final Asn1Encodable child) {
        this.children.add(child);
    }

    @Override
    public List<Asn1Encodable> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<Asn1Encodable> children) {
        this.children = children;
    }

    @Override
    public void clearChildren() {
        this.children.clear();
    }
}

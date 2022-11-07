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
import de.rub.nds.asn1.serializer.Asn1ImplicitSerializer;
import de.rub.nds.asn1.serializer.Asn1Serializer;
import de.rub.nds.modifiablevariable.HoldsModifiableVariable;
import jakarta.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Asn1Implicit extends Asn1Container {

    public static final int TAG_CLASS = TagClass.CONTEXT_SPECIFIC.getIntValue();

    public static final boolean IS_CONSTRUCTED = true;

    @XmlElement(name = "offset")
    private int offset = 0;

    @XmlAnyElement(lax = true)
    @HoldsModifiableVariable
    private List<Asn1Encodable> children = new LinkedList<>();

    public Asn1Implicit() {
        super(TAG_CLASS, IS_CONSTRUCTED, 0);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
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

    @Override
    public Asn1Serializer getSerializer() {
        return new Asn1ImplicitSerializer(this);
    }
}

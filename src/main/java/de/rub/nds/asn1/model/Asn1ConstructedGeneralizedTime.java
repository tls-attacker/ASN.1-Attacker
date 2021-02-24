/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.model;

import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagNumber;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlAnyElement;

public class Asn1ConstructedGeneralizedTime extends Asn1Container {

    public static final int TAG_CLASS = TagClass.UNIVERSAL.getIntValue();

    public static final boolean IS_CONSTRUCTED = true;

    public static final int TAG_NUMBER = TagNumber.GENERALIZEDTIME.getIntValue();

    @XmlAnyElement(lax = true)
    private List<Asn1Encodable> children = new LinkedList<>();

    public Asn1ConstructedGeneralizedTime() {
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

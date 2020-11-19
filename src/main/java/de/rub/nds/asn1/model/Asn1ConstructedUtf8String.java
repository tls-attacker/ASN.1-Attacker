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

import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagNumber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Asn1ConstructedUtf8String extends Asn1Container {

    public static final int TAG_CLASS = TagClass.UNIVERSAL.getIntValue();

    public static final boolean IS_CONSTRUCTED = true;

    public static final int TAG_NUMBER = TagNumber.UTF8STRING.getIntValue();

    @XmlAnyElement(lax = true)
    private List<Asn1Encodable> children = new LinkedList<>();

    public Asn1ConstructedUtf8String() {
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

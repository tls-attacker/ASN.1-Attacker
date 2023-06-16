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
import de.rub.nds.asn1.constants.UniversalTagNumber;
import de.rub.nds.modifiablevariable.HoldsModifiableVariable;
import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Asn1Container extends Asn1Field implements ConstructedAsn1Field {

    private ModifiableByteArray encodedChildren;

    @XmlElementWrapper
    @XmlElements(
            value = {
                @XmlElement(type = Asn1Boolean.class, name = "Asn1Boolean"),
                @XmlElement(type = Asn1Container.class, name = "Asn1Container"),
                @XmlElement(type = PrimitiveAsn1Field.class, name = "Asn1Field"),
                @XmlElement(type = Asn1Integer.class, name = "Asn1Integer"),
                @XmlElement(type = Asn1Null.class, name = "Asn1Null"),
                @XmlElement(type = Asn1ObjectIdentifier.class, name = "Asn1ObjectIdentifier"),
                @XmlElement(type = Asn1BitString.class, name = "Asn1PrimitiveBitString"),
                @XmlElement(
                        type = Asn1GeneralizedTime.class,
                        name = "Asn1PrimitiveGeneralizedTime"),
                @XmlElement(type = Asn1Ia5String.class, name = "Asn1PrimitiveIa5String"),
                @XmlElement(type = Asn1OctetString.class, name = "Asn1PrimitiveOctetString"),
                @XmlElement(
                        type = Asn1PrintableString.class,
                        name = "Asn1PrimitivePrintableString"),
                @XmlElement(type = Asn1T61String.class, name = "Asn1PrimitiveT61String"),
                @XmlElement(type = Asn1UtcTime.class, name = "Asn1PrimitiveUtcTime"),
                @XmlElement(type = Asn1Utf8String.class, name = "Asn1PrimitiveUtf8String"),
                @XmlElement(type = Asn1Sequence.class, name = "Asn1Sequence"),
                @XmlElement(type = Asn1Set.class, name = "Asn1Set"),
                @XmlElement(type = Asn1UnknownField.class, name = "Asn1UnknownField")
            })
    @HoldsModifiableVariable
    private List<Asn1Encodable> children;

    public Asn1Container(
            String identifier,
            TagClass tagClassType,
            TagConstructed tagConstructedType,
            UniversalTagNumber tagNummerType) {
        super(identifier, tagClassType, tagConstructedType, tagNummerType);
        this.children = new LinkedList<>();
    }

    /** Private no-arg constructor to please JAXB */
    private Asn1Container() {
        super(null, null, null, (UniversalTagNumber) null);
    }

    public Asn1Container(String identifier, int implicitTagNumber) {
        super(identifier, TagClass.CONTEXT_SPECIFIC, TagConstructed.CONSTRUCTED, implicitTagNumber);
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

    public List<Asn1Encodable> getChildren() {
        return children;
    }

    public void setChildren(List<Asn1Encodable> children) {
        this.children = children;
    }

    public void clearChildren() {
        this.children.clear();
    }
}

/*
 * ASN.1-Attacker - A Library for Arbitrary ASN.1 Structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.UniversalTagNumber;
import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Asn1Container extends Asn1Field implements ConstructedAsn1Field {

    private ModifiableByteArray encodedChildren;

    public Asn1Container(
            String identifier,
            TagClass tagClassType,
            TagConstructed tagConstructedType,
            UniversalTagNumber tagNummerType) {
        super(identifier, tagClassType, tagConstructedType, tagNummerType);
    }

    /** Private no-arg constructor to please JAXB */
    private Asn1Container() {
        super();
    }

    public Asn1Container(String identifier, int implicitTagNumber) {
        super(identifier, TagClass.CONTEXT_SPECIFIC, TagConstructed.CONSTRUCTED, implicitTagNumber);
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
}

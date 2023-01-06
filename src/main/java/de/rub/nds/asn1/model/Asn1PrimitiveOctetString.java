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
import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.parser.Asn1PrimitiveOctetStringParser;
import de.rub.nds.asn1.preparator.Asn1PrimitiveOctetStringPreparator;
import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Asn1PrimitiveOctetString<Chooser extends AbstractChooser> extends Asn1Field<Chooser> {

    @XmlElement(name = "value")
    private ModifiableByteArray value;

    /** Private no-arg constructor to please JAXB */
    private Asn1PrimitiveOctetString() {
        super(null);
    }

    public Asn1PrimitiveOctetString(String identifier) {
        super(identifier, TagClass.UNIVERSAL, TagConstructed.PRIMITIVE, TagNumber.OCTET_STRING);
    }

    public ModifiableByteArray getValue() {
        return value;
    }

    public void setValue(ModifiableByteArray value) {
        this.value = value;
    }

    public void setValue(byte[] value) {
        this.value = ModifiableVariableFactory.safelySetValue(this.value, value);
    }

    @Override
    public Asn1PrimitiveOctetStringPreparator getPreparator(Chooser chooser) {
        return new Asn1PrimitiveOctetStringPreparator(chooser, this);
    }

    @Override
    public Asn1PrimitiveOctetStringParser getParser(Chooser chooser) {
        return new Asn1PrimitiveOctetStringParser(chooser, this);
    }
}

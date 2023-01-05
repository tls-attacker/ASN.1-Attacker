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
import de.rub.nds.asn1.parser.Asn1BooleanParser;
import de.rub.nds.asn1.parser.Asn1FieldParser;
import de.rub.nds.asn1.preparator.Asn1BooleanPreparator;
import de.rub.nds.asn1.preparator.Preparator;
import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.bool.ModifiableBoolean;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Asn1Boolean extends Asn1Field {

    @XmlElement(name = "value")
    private ModifiableBoolean value;

    /** Private no-arg constructor to please JAXB */
    private Asn1Boolean() {
        super(null);
    }

    public Asn1Boolean(String identifier) {
        super(identifier, TagClass.UNIVERSAL, TagConstructed.PRIMITIVE, TagNumber.BOOLEAN);
    }

    public ModifiableBoolean getValue() {
        return value;
    }

    public void setValue(ModifiableBoolean value) {
        this.value = value;
    }

    public void setValue(boolean value) {
        this.value = ModifiableVariableFactory.safelySetValue(this.value, value);
    }

    @Override
    public Preparator getPreparator() {
        return new Asn1BooleanPreparator(this);
    }

    @Override
    public Asn1FieldParser<Asn1Boolean> getParser() {
        return new Asn1BooleanParser(this);
    }
}

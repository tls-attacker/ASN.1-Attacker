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
import de.rub.nds.asn1.handler.EmptyHandler;
import de.rub.nds.asn1.handler.Handler;
import de.rub.nds.asn1.parser.Asn1PrimitiveT61StringParser;
import de.rub.nds.asn1.preparator.Asn1PrimitiveT61StringPreparator;
import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.string.ModifiableString;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Asn1PrimitiveT61String<Chooser extends AbstractChooser> extends Asn1Field<Chooser> {

    @XmlElement(name = "value")
    private ModifiableString value;

    /** Private no-arg constructor to please JAXB */
    private Asn1PrimitiveT61String() {
        super(null);
    }

    public Asn1PrimitiveT61String(String identifier) {
        super(identifier, TagClass.UNIVERSAL, TagConstructed.PRIMITIVE, TagNumber.T61STRING);
    }

    public ModifiableString getValue() {
        return value;
    }

    public void setValue(ModifiableString value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = ModifiableVariableFactory.safelySetValue(this.value, value);
    }

    @Override
    public Asn1PrimitiveT61StringPreparator<Chooser> getPreparator(Chooser chooser) {
        return new Asn1PrimitiveT61StringPreparator<>(chooser, this);
    }

    @Override
    public Asn1PrimitiveT61StringParser<Chooser> getParser(Chooser chooser) {
        return new Asn1PrimitiveT61StringParser<>(chooser, this);
    }

    @Override
    public Handler<Chooser> getHandler(Chooser chooser) {
        return new EmptyHandler<>(chooser);
    }
}

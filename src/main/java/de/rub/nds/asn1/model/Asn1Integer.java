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
import de.rub.nds.asn1.parser.Asn1IntegerParser;
import de.rub.nds.asn1.preparator.Asn1IntegerPreparator;
import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.biginteger.ModifiableBigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Asn1Integer<Chooser extends AbstractChooser> extends Asn1Field<Chooser> {

    @XmlElement(name = "value")
    private ModifiableBigInteger value;

    public Asn1Integer(String identifier) {
        super(identifier, TagClass.UNIVERSAL, TagConstructed.PRIMITIVE, TagNumber.INTEGER);
    }

    /** Private no-arg constructor to please JAXB */
    private Asn1Integer() {
        super(null);
    }

    public ModifiableBigInteger getValue() {
        return value;
    }

    public void setValue(ModifiableBigInteger value) {
        this.value = value;
    }

    public void setValue(BigInteger value) {
        this.value = ModifiableVariableFactory.safelySetValue(this.value, value);
    }

    @Override
    public Asn1IntegerPreparator getPreparator(Chooser chooser) {
        return new Asn1IntegerPreparator(chooser, this);
    }

    @Override
    public Asn1IntegerParser getParser(Chooser chooser) {
        return new Asn1IntegerParser(chooser, this);
    }

    @Override
    public EmptyHandler getHandler(Chooser chooser) {
        return new EmptyHandler(chooser);
    }
}

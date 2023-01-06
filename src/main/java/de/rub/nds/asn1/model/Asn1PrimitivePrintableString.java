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
import de.rub.nds.asn1.parser.Asn1PrimitivePrintableStringParser;
import de.rub.nds.asn1.preparator.Asn1PrimitivePrintableStringPreparator;
import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.string.ModifiableString;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Asn1PrimitivePrintableString<Chooser extends AbstractChooser>
        extends Asn1Field<Chooser> {

    @XmlElement(name = "value")
    private ModifiableString value;

    /** Private no-arg constructor to please JAXB */
    private Asn1PrimitivePrintableString() {
        super(null);
    }

    public Asn1PrimitivePrintableString(String identifier) {
        super(identifier, TagClass.UNIVERSAL, TagConstructed.PRIMITIVE, TagNumber.PRINTABLESTRING);
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
    public Asn1PrimitivePrintableStringPreparator getPreparator(Chooser chooser) {
        return new Asn1PrimitivePrintableStringPreparator(chooser, this);
    }

    @Override
    public Asn1PrimitivePrintableStringParser getParser(Chooser chooser) {
        return new Asn1PrimitivePrintableStringParser(chooser, this);
    }
}

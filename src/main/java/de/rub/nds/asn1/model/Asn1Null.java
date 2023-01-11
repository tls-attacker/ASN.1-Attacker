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
import de.rub.nds.asn1.parser.Asn1NullParser;
import de.rub.nds.asn1.preparator.Asn1NullPreparator;
import de.rub.nds.asn1.preparator.Preparator;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Asn1Null<Chooser extends AbstractChooser> extends Asn1Field<Chooser> {

    /** Private no-arg constructor to please JAXB */
    private Asn1Null() {
        super(null);
    }

    public Asn1Null(String identifier) {
        super(identifier, TagClass.UNIVERSAL, TagConstructed.PRIMITIVE, TagNumber.NULL);
    }

    @Override
    public Preparator getPreparator(Chooser chooser) {
        return new Asn1NullPreparator(chooser, this);
    }

    @Override
    public Asn1NullParser getParser(Chooser chooser) {
        return new Asn1NullParser(chooser, this);
    }
    
    @Override
    public Handler getHandler(Chooser chooser) {
        return new EmptyHandler(chooser);
    }
}

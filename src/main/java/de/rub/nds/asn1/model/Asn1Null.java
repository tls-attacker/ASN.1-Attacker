/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.model;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.TagNumber;
import de.rub.nds.asn1.preparator.Asn1NullPreparator;
import de.rub.nds.asn1.preparator.Preparator;
import de.rub.nds.asn1.serializer.Asn1FieldSerializer;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Asn1Null extends Asn1Field {

    public Asn1Null(String identifier) {
        super(identifier, TagClass.UNIVERSAL, TagConstructed.CONSTRUCTED, TagNumber.NULL);
    }

    @Override
    public Asn1FieldSerializer getGenericSerializer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Preparator getGenericPreparator() {
        return new Asn1NullPreparator(asn1Integer);
    }

}
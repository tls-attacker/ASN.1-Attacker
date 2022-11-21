/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.model;

import de.rub.nds.asn1.preparator.Preparator;
import de.rub.nds.asn1.serializer.Asn1FieldSerializer;
import de.rub.nds.modifiablevariable.HoldsModifiableVariable;
import jakarta.xml.bind.annotation.XmlAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Asn1Any implements Asn1Encodable {

    private static final Logger LOGGER = LogManager.getLogger();

    @HoldsModifiableVariable
    private Asn1Field instantiation;

    @XmlAttribute
    private String identifier;

    public Asn1Any(String identifier) {
        this.identifier = identifier;
    }

    public void setInstantiation(Asn1Field instantiation) {
        this.instantiation = instantiation;
    }

    public Asn1Field getInstantiation() {
        return instantiation;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public Asn1FieldSerializer getSerializer() {
        if (instantiation != null) {
            return instantiation.getSerializer();
        } else {
            throw new RuntimeException("Tried to access serializer of any element before choosing instantiation");
        }
    }

    @Override
    public Preparator getPreparator() {
        if (instantiation != null) {
            return instantiation.getPreparator();
        } else {
            throw new RuntimeException("Tried to access preparator of any element before choosing instantiation");
        }
    }

}

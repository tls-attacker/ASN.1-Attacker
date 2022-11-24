/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.model;

import de.rub.nds.asn1.parser.Asn1Parser;
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

    private boolean optional;

    public Asn1Any(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean isCompatible(int tagNumber) {
        return true;
    }

    @Override
    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
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
    public Asn1FieldSerializer getGenericSerializer() {
        if (instantiation != null) {
            return instantiation.getGenericSerializer();
        } else {
            throw new RuntimeException("Tried to access serializer of any element before choosing instantiation");
        }
    }

    @Override
    public Preparator getGenericPreparator() {
        if (instantiation != null) {
            return instantiation.getGenericPreparator();
        } else {
            throw new RuntimeException("Tried to access preparator of any element before choosing instantiation");
        }
    }

    @Override
    public Asn1Parser<?> getParser() {
        Asn1UnknownField unknown = new Asn1UnknownField("any");
        this.setInstantiation(unknown);
        return unknown.getParser();
    }
}

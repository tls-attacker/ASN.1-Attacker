/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.parser.Asn1AnyParser;
import de.rub.nds.asn1.preparator.Preparator;
import de.rub.nds.asn1.serializer.Asn1FieldSerializer;
import de.rub.nds.modifiablevariable.HoldsModifiableVariable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Asn1Any<Chooser extends AbstractChooser> implements Asn1Encodable<Chooser> {

    private static final Logger LOGGER = LogManager.getLogger();

    @HoldsModifiableVariable private Asn1Field instantiation;

    @XmlAttribute(name = "identifier")
    private String identifier;

    private boolean optional;

    /** Private no-arg constructor to please JAXB */
    private Asn1Any() {}

    public Asn1Any(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean isCompatible(Integer tagNumber, Boolean constructed, Integer classType) {
        LOGGER.debug("ANY is always compatible");
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
    @XmlTransient
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
            throw new RuntimeException(
                    "Tried to access serializer of any element before choosing instantiation");
        }
    }

    @Override
    public Preparator getPreparator(Chooser chooser) {
        if (instantiation != null) {
            return instantiation.getPreparator(chooser);
        } else {
            throw new RuntimeException(
                    "Tried to access preparator of any element before choosing instantiation");
        }
    }

    @Override
    public Asn1AnyParser<Chooser> getParser(Chooser chooser) {
        return new Asn1AnyParser<Chooser>(chooser, this);
    }
}

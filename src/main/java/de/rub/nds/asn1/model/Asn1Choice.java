/*
 * ASN.1-Attacker - A Library for Arbitrary ASN.1 Structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Asn1Choice implements Asn1Encodable {

    private static final Logger LOGGER = LogManager.getLogger();

    private boolean optional;
    private String identifier;

    private List<Asn1Encodable> selecteableEncodables;

    private Asn1Encodable selectedChoice;

    public Asn1Choice(String identifier, Asn1Encodable... selectableEncodables) {
        this.identifier = identifier;
        this.selecteableEncodables = List.of(selectableEncodables);
    }

    public List<Asn1Encodable> getSelecteableEncodables() {
        return selecteableEncodables;
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
    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean otpional) {
        this.optional = otpional;
    }

    @Override
    public boolean matchesHeader(TagClass classType, Boolean constructed, Integer tagNumber) {
        for (Asn1Encodable asn1Encodable : selecteableEncodables) {
            if (asn1Encodable.matchesHeader(classType, constructed, tagNumber)) {
                return true;
            }
        }
        return false;
    }

    public Asn1Encodable getSelectedChoice() {
        return selectedChoice;
    }

    public void makeSelection(TagClass classType, Boolean constructed, Integer tagNumber) {
        for (Asn1Encodable asn1Encodable : selecteableEncodables) {
            if (asn1Encodable.matchesHeader(classType, constructed, tagNumber)) {
                selectedChoice = asn1Encodable;
                return;
            }
        }
        LOGGER.warn(
                "Could not make selection in "
                        + getIdentifier()
                        + " for tagNumber "
                        + tagNumber
                        + " constructed "
                        + constructed
                        + " classType "
                        + classType);
        selectedChoice = null;
    }

    public void makeSelection(Asn1Encodable asn1Encodable) {
        if (selecteableEncodables.stream()
                .anyMatch(encodable -> encodable.getClass().equals(asn1Encodable.getClass()))) {
            selectedChoice = asn1Encodable;
        } else {
            throw new RuntimeException("Could not make selection");
        }
    }

    @Override
    public ModifiableByteArray getTagOctets() {
        return selectedChoice.getTagOctets();
    }

    @Override
    public ModifiableByteArray getLengthOctets() {
        return selectedChoice.getLengthOctets();
    }

    @Override
    public ModifiableByteArray getContent() {
        return selectedChoice.getContent();
    }
}

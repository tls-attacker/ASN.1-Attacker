/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.model.helper.SelectableChoice;
import de.rub.nds.asn1.preparator.Preparator;
import de.rub.nds.asn1.serializer.Asn1FieldSerializer;
import de.rub.nds.modifiablevariable.HoldsModifiableVariable;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import java.io.BufferedInputStream;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Asn1Choice implements Asn1Encodable {

    private static final Logger LOGGER = LogManager.getLogger();

    @HoldsModifiableVariable
    private Asn1Field selectedChoice;

    @XmlElementWrapper
    @XmlElementRef
    @HoldsModifiableVariable
    private final List<SelectableChoice> choiceList;

    @XmlAttribute
    private String identifier;

    public Asn1Choice(String identifier, Asn1Field... fields) {
        this.identifier = identifier;
        choiceList = new LinkedList<>();
        for (Asn1Field field : fields) {
            choiceList.add(new SelectableChoice(field));
        }
    }

    public boolean canMakeValidChoice(BufferedInputStream stream) {
        byte[] tag = null; // = //read tag from stream;
        for (SelectableChoice choice : choiceList) {
            if (choice.isSelectable(tag)) {
                return true;
            }
        }
        return false;
    }

    public void makeSelection(BufferedInputStream stream) {
        byte[] tag = null; // = //read tag from stream;
        for (SelectableChoice choice : choiceList) {
            if (choice.isSelectable(tag)) {
                selectedChoice = choice.getField();
                return;
            }
        }
        LOGGER.warn("Could not make a valid choice");
    }

    public Asn1Field getSelectedChoice() {
        return selectedChoice;
    }

    public void setSelectedChoice(Asn1Field selectedChoice) {
        this.selectedChoice = selectedChoice;
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
        if (selectedChoice != null) {
            return selectedChoice.getGenericSerializer();
        } else {
            throw new RuntimeException("Tried to access serializer of choice before selecting a choice");
        }
    }

    @Override
    public Preparator getGenericPreparator() {
        if (selectedChoice != null) {
            return selectedChoice.getGenericPreparator();
        } else {
            throw new RuntimeException("Tried to access preparator of choice before selecting a choice");
        }
    }

}

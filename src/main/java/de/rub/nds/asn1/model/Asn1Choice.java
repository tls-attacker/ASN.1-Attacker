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
import de.rub.nds.asn1.model.helper.SelectableChoice;
import de.rub.nds.asn1.parser.Asn1ChoiceParser;
import de.rub.nds.asn1.preparator.Preparator;
import de.rub.nds.asn1.serializer.Asn1FieldSerializer;
import de.rub.nds.modifiablevariable.HoldsModifiableVariable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Asn1Choice<Chooser extends AbstractChooser>
        implements Asn1Encodable<Chooser> {

    private static final Logger LOGGER = LogManager.getLogger();

    @HoldsModifiableVariable private Asn1Field selectedChoice;

    @XmlElementWrapper @XmlElementRef @HoldsModifiableVariable
    private final List<SelectableChoice> choiceList;

    @XmlAttribute(name = "identifier")
    private String identifier;

    /** Private no-arg constructor to please JAXB */
    private Asn1Choice() {
        choiceList = null;
    }

    public Asn1Choice(String identifier, Asn1Field... fields) {
        this.identifier = identifier;
        choiceList = new LinkedList<>();
        for (Asn1Field field : fields) {
            choiceList.add(new SelectableChoice(field));
        }
    }

    public boolean canMakeValidChoice(Chooser chooser, byte[] tagOctets) {
        for (SelectableChoice choice : choiceList) {
            if (choice.isSelectable(chooser, tagOctets)) {
                return true;
            }
        }
        return false;
    }

    public void makeSelection(Chooser chooser, byte[] tagOctets) {
        for (SelectableChoice choice : choiceList) {
            if (choice.isSelectable(chooser, tagOctets)) {
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
    public Asn1FieldSerializer getSerializer() {
        if (selectedChoice != null) {
            return selectedChoice.getSerializer();
        } else {
            throw new RuntimeException(
                    "Tried to access serializer of choice before selecting a choice");
        }
    }

    @Override
    public Preparator getPreparator(Chooser chooser) {
        if (selectedChoice != null) {
            return selectedChoice.getPreparator(chooser);
        } else {
            throw new RuntimeException(
                    "Tried to access preparator of choice before selecting a choice");
        }
    }

    @Override
    public Asn1ChoiceParser getParser(Chooser chooser) {
        return new Asn1ChoiceParser(chooser, this);
    }

    @Override
    public boolean isOptional() {
        return false; // Are choices never optional? Idk
    }

    @Override
    public boolean isCompatible(Integer tagNumber, Boolean constructed, Integer classType) {
        for (SelectableChoice selectableChoice : choiceList) {
            if (selectableChoice.getField().isCompatible(tagNumber, constructed, classType)) {
                LOGGER.debug(
                        "{} is compatible within CHOICE",
                        selectableChoice.getField().getIdentifier());
                return true;
            }
        }
        LOGGER.debug("Did not find a selectable choice within CHOICE: {}", identifier);
        return false;
    }
}

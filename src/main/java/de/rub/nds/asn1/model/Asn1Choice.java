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
import java.io.BufferedInputStream;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Asn1Choice implements Asn1Encodable {

    private static final Logger LOGGER = LogManager.getLogger();

    private Asn1Field selectedChoice;

    private final List<SelectableChoice> choiceList;

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
                selectedChoice.setIdentifier(identifier);
                return;
            }
        }
        LOGGER.warn("Could not make a valid choice");
    }

    public Asn1Field getSelectedChoice() {
        return selectedChoice;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

}

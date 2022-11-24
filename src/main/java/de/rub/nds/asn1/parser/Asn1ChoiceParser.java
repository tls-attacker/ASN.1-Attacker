/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1Choice;
import de.rub.nds.asn1.model.Asn1Field;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Asn1ChoiceParser extends Asn1Parser<Asn1Choice> {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Asn1Choice choice;

    public Asn1ChoiceParser(Asn1Choice asn1Choice) {
        super(asn1Choice);
        this.choice = asn1Choice;
    }

    @Override
    public void parse(InputStream inputStream) {
        try {
            byte[] tagOctetes = parseTagOctets(inputStream);
            parseWithoutTag(inputStream, tagOctetes);
        } catch (IOException ex) {
            throw new ParserException(ex);
        }
    }

    @Override
    public void parseIndividualContentFields(InputStream inputStream) throws IOException {
        choice.getSelectedChoice().getParser()
            .parseIndividualContentFields(new ByteArrayInputStream(choice.getSelectedChoice().getContent().getValue()));
    }

    @Override
    public void parseWithoutTag(InputStream inputStream, byte[] tagOctets) {
        if (choice.canMakeValidChoice(tagOctets)) {
            choice.makeSelection(tagOctets);
        } else {
            throw new ParserException("Cannot make a valid choice");
        }
        try {
            Asn1Field selection = choice.getSelectedChoice();
            selection.setTagOctets(tagOctets);
            selection.setTagClass(this.parseTagClass(selection.getTagOctets().getValue()[0]));
            selection.setTagConstructed(this.parseTagConstructed(selection.getTagOctets().getValue()[0]));
            selection.setTagNumber(this.parseTagNumber(selection.getTagOctets().getValue()));
            selection.setLengthOctets(this.parseLengthOctets(inputStream));
            selection.setLength(this.parseLength(selection.getLengthOctets().getValue()));
            selection.setContent(this.parseContentOctets(selection.getLength().getValue(), inputStream));
            parseIndividualContentFields(inputStream);
        } catch (IOException ex) {
            throw new ParserException(ex);
        }
    }
}

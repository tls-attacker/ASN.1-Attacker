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
            if (choice.canMakeValidChoice(tagOctetes)) {
                choice.makeSelection(tagOctetes);
                Asn1Field selection = choice.getSelectedChoice();
                selection.setTagOctets(tagOctetes);
                selection.setTagClass(this.parseTagClass(selection.getTagOctets().getValue()[0]));
                selection.setTagConstructed(this.parseTagConstructed(selection.getTagOctets().getValue()[0]));
                selection.setTagNumber(this.parseTagNumber(selection.getTagOctets().getValue()));
                selection.setLengthOctets(this.parseLengthOctets(inputStream));
                selection.setLength(this.parseLength(selection.getLengthOctets().getValue()));
                selection.setContent(this.parseContentOctets(selection.getLength().getValue(), inputStream));
                selection.getParser().parseIndividualContentFields(new ByteArrayInputStream(selection.getContent().getValue()));
            } else {
                throw new ParserException("Cannot make a valid choice");
            }
        } catch (IOException ex) {
            throw new ParserException(ex);
        }
    }
}

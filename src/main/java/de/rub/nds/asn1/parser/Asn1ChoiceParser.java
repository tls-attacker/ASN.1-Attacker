/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.model.Asn1Choice;
import de.rub.nds.asn1.model.Asn1Field;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Asn1ChoiceParser<Chooser extends AbstractChooser>
        extends Asn1Parser<Chooser, Asn1Choice<Chooser>> {

    private static final Logger LOGGER = LogManager.getLogger();

    public Asn1ChoiceParser(Chooser chooser, Asn1Choice<Chooser> asn1Choice) {
        super(chooser, asn1Choice);
    }

    @Override
    public void parse(InputStream inputStream) {
        try {
            LOGGER.debug(
                    "Parsing: {} of type {}",
                    encodable.getIdentifier(),
                    encodable.getClass().getSimpleName());

            byte[] tagOctetes = parseTagOctets(inputStream);
            parseWithoutTag(inputStream, tagOctetes);
        } catch (IOException ex) {
            throw new ParserException(ex);
        }
    }

    @Override
    public void parseIndividualContentFields(InputStream inputStream) throws IOException {
        encodable
                .getSelectedChoice()
                .getParser(chooser)
                .parseIndividualContentFields(
                        new ByteArrayInputStream(
                                encodable.getSelectedChoice().getContent().getValue()));
        encodable.getSelectedChoice().getHandler(chooser).adjustContext();
    }

    @Override
    public void parseWithoutTag(InputStream inputStream, byte[] tagOctets) {
        if (encodable.canMakeValidChoice(chooser, tagOctets)) {
            encodable.makeSelection(chooser, tagOctets);
            LOGGER.debug(
                    "Selected {} of type {} for CHOICE",
                    encodable.getSelectedChoice().getIdentifier(),
                    encodable.getSelectedChoice().getClass().getSimpleName());
        } else {
            throw new ParserException("Cannot make a valid choice");
        }
        try {
            Asn1Field selection = encodable.getSelectedChoice();
            selection.setTagOctets(tagOctets);
            selection.setTagClass(this.parseTagClass(selection.getTagOctets().getValue()[0]));
            selection.setTagConstructed(
                    this.parseTagConstructed(selection.getTagOctets().getValue()[0]));
            selection.setTagNumber(this.parseTagNumber(selection.getTagOctets().getValue()));
            selection.setLengthOctets(this.parseLengthOctets(inputStream));
            selection.setLength(this.parseLength(selection.getLengthOctets().getValue()));
            selection.setContent(
                    this.parseContentOctets(selection.getLength().getValue(), inputStream));
            parseIndividualContentFields(inputStream);
        } catch (IOException ex) {
            throw new ParserException(ex);
        }
        LOGGER.trace("Finished parsing {}", encodable.getSelectedChoice().getIdentifier());
    }
}

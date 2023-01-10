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
import de.rub.nds.asn1.model.Asn1Any;
import de.rub.nds.asn1.model.Asn1Encodable;
import de.rub.nds.asn1.model.Asn1Sequence;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Asn1SequenceParser<Chooser extends AbstractChooser>
        extends Asn1FieldParser<Chooser, Asn1Sequence<Chooser>> {

    private static final Logger LOGGER = LogManager.getLogger();

    private Asn1Sequence<Chooser> sequence;

    public Asn1SequenceParser(Chooser chooser, Asn1Sequence<Chooser> asn1Sequence) {
        super(chooser, asn1Sequence);
        this.sequence = asn1Sequence;
    }

    @Override
    public void parseIndividualContentFields(InputStream inputStream) throws IOException {
        byte[] tagOctets = null;
        Integer tagNumber = null;
        Boolean constructed = null;
        Integer tagClass = null;

        for (Asn1Encodable<Chooser> tempEncodable : sequence.getChildren()) {
            if (tagNumber == null
                    && tagOctets == null
                    && constructed == null
                    && tagClass == null
                    && inputStream.available() > 0) {
                if (tempEncodable instanceof Asn1Any) {
                    LOGGER.debug("Parsing AnyField in sequence");
                    tempEncodable.getParser(chooser).parse(inputStream);
                    continue;
                } else {
                    tagOctets = parseTagOctets(inputStream);
                    tagNumber = parseTagNumber(tagOctets);
                    constructed = parseTagConstructed(tagOctets[0]);
                    tagClass = parseTagClass(tagOctets[0]);
                }
            }
            if (tagOctets != null && tempEncodable.isCompatible(tagNumber, constructed, tagClass)) {
                LOGGER.info(tempEncodable.getIdentifier() + " is compatible");
                tempEncodable.getParser(chooser).parseWithoutTag(inputStream, tagOctets);
                // Reset so the next element can get parsed
                tagNumber = null;
                tagOctets = null;
                constructed = null;
                tagClass = null;
            } else {
                if (!tempEncodable.isOptional()) {
                    throw new ParserException(
                            "Missing non-optional element: "
                                    + tempEncodable.getIdentifier()
                                    + ", bytes left in stream:"
                                    + inputStream.available());
                }
            }
        }

        if (inputStream.available() > 0) {
            byte[] remainingBytes = inputStream.readAllBytes();
            throw new ParserException(
                    "Unattributed bytes in stream: "
                            + ArrayConverter.bytesToHexString(remainingBytes));
        }
    }
}

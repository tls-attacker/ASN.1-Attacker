/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1Encodable;
import de.rub.nds.asn1.model.Asn1Sequence;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Asn1SequenceParser extends Asn1FieldParser<Asn1Sequence> {

    private static final Logger LOGGER = LogManager.getLogger();

    public Asn1SequenceParser(Asn1Sequence asn1Sequence) {
        super(asn1Sequence);
    }

    @Override
    public void parseIndividualContentFields(InputStream inputStream) throws IOException {
        byte[] tagOctets = null;
        Integer tagNumber = null;
        Boolean constructed = null;
        Integer tagClass = null;
        for (Asn1Encodable tempEncodable : encodable.getChildren()) {

            if (tagNumber == null
                    && tagOctets == null
                    && constructed == null
                    && tagClass == null
                    && inputStream.available() > 0) {
                tagOctets = parseTagOctets(inputStream);
                tagNumber = parseTagNumber(tagOctets);
                constructed = parseTagConstructed(tagOctets[0]);
                tagClass = parseTagClass(tagOctets[0]);
            }
            if (tagOctets != null && tempEncodable.isCompatible(tagNumber, constructed, tagClass)) {
                LOGGER.info(tempEncodable.getIdentifier() + " is compatible");
                tempEncodable.getParser().parseWithoutTag(inputStream, tagOctets);
                // Reset so the next element can get parsed
                tagNumber = null;
                tagOctets = null;
                constructed = null;
                tagClass = null;
            } else {
                if (!tempEncodable.isOptional()) {
                    throw new ParserException(
                            "Missing non-optional element: " + tempEncodable.getIdentifier());
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

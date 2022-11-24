/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1Encodable;
import de.rub.nds.asn1.model.Asn1Sequence;
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
        for (Asn1Encodable tempEncodable : encodable.getChildren()) {
            if (tagNumber == null && tagOctets == null) {
                tagOctets = parseTagOctets(inputStream);
                tagNumber = parseTagNumber(tagOctets);
            }
            if (tempEncodable.isCompatible(tagNumber)) {
                tempEncodable.getParser().parseWithoutTag(inputStream, tagOctets);
                // Reset tagNumber and tagOctets so the next element can get parsed
                tagNumber = null;
                tagOctets = null;
            } else {
                if (!tempEncodable.isOptional()) {
                    throw new ParserException("Missing non-optional element");
                }
            }
        }
        if (inputStream.available() > 0) {
            throw new ParserException("Unattributed bytes in stream");
        }
    }
}

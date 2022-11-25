/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1Boolean;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Asn1BooleanParser extends Asn1FieldParser<Asn1Boolean> {

    private static final Logger LOGGER = LogManager.getLogger();

    public Asn1BooleanParser(Asn1Boolean booleanField) {
        super(booleanField);
    }

    @Override
    public void parseIndividualContentFields(InputStream inputStream) throws IOException {
        if (inputStream.available() != 1) {
            throw new ParserException(
                    "ASN.1 boolean has incorrect size. Expected \'1\' but found "
                            + inputStream.available());
        }
        if (encodable.getContent().getValue()[0] == (byte) 0xFF) {
            encodable.setValue(true);
        } else if (encodable.getContent().getValue()[0] == (byte) 0x00) {
            encodable.setValue(false);
        } else {
            LOGGER.warn("asn1Boolean is not DER encoded. Assuming \"false\".");
            encodable.setValue(false);
        }
    }
}

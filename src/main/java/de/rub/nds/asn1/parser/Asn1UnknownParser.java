/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1UnknownField;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Asn1UnknownParser extends Asn1FieldParser<Asn1UnknownField> {

    private static final Logger LOGGER = LogManager.getLogger();

    public Asn1UnknownParser(Asn1UnknownField asn1Unknown) {
        super(asn1Unknown);
    }

    @Override
    public void parseIndividualContentFields(InputStream inputStream) throws IOException {
        byte[] readAllBytes = inputStream.readAllBytes();
        encodable.setContentConfig(readAllBytes);
    }
}

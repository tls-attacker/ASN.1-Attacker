/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.context.AbstractContext;
import de.rub.nds.asn1.model.Asn1UnknownField;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Asn1UnknownParser<Context extends AbstractContext>
        extends Asn1FieldParser<Context, Asn1UnknownField<Context>> {

    private static final Logger LOGGER = LogManager.getLogger();

    public Asn1UnknownParser(Context context, Asn1UnknownField asn1Unknown) {
        super(context, asn1Unknown);
    }

    @Override
    public void parseIndividualContentFields(InputStream inputStream) throws IOException {
        byte[] readAllBytes = inputStream.readAllBytes();
        encodable.setContentConfig(readAllBytes);
    }
}

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
import de.rub.nds.asn1.model.Asn1Null;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Asn1NullParser<Chooser extends AbstractChooser>
        extends Asn1FieldParser<Chooser, Asn1Null<Chooser>> {

    private static final Logger LOGGER = LogManager.getLogger();

    public Asn1NullParser(Chooser chooser, Asn1Null<Chooser> nullField) {
        super(chooser, nullField);
    }

    @Override
    public void parseIndividualContentFields(InputStream inputStream) throws IOException {
        if (inputStream.available() > 0) {
            throw new ParserException("NullField contains data");
        }
    }
}

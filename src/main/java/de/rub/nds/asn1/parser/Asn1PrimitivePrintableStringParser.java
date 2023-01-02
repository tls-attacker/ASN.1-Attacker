/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1PrimitivePrintableString;
import java.io.IOException;
import java.io.InputStream;

public class Asn1PrimitivePrintableStringParser
        extends Asn1FieldParser<Asn1PrimitivePrintableString> {

    public Asn1PrimitivePrintableStringParser(
            Asn1PrimitivePrintableString asn1PrimitivePrintableString) {
        super(asn1PrimitivePrintableString);
    }

    @Override
    public void parseIndividualContentFields(InputStream inputStream) throws IOException {
        encodable.setValue(new String(inputStream.readAllBytes()));
    }
}

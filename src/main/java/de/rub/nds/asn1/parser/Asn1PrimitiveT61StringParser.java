/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1PrimitiveT61String;
import java.io.IOException;
import java.io.InputStream;

public class Asn1PrimitiveT61StringParser extends Asn1FieldParser<Asn1PrimitiveT61String> {

    public Asn1PrimitiveT61StringParser(Asn1PrimitiveT61String asn1PrimitiveT61String) {
        super(asn1PrimitiveT61String);
    }

    @Override
    public void parseIndividualContentFields(InputStream byteArrayInputStream) throws IOException {
        encodable.setValue(new String(byteArrayInputStream.readAllBytes()));
    }
}

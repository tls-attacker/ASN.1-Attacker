/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1Integer;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public class Asn1IntegerParser extends Asn1FieldParser<Asn1Integer> {

    public Asn1IntegerParser(Asn1Integer asn1Integer) {
        super(asn1Integer);
    }

    @Override
    public void parseIndividualContentFields(InputStream byteArrayInputStream) throws IOException {
        byte[] allBytes = byteArrayInputStream.readAllBytes();
        encodable.setValue(new BigInteger(allBytes));
    }
}

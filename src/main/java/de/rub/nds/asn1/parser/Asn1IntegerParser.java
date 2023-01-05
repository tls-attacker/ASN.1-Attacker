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
import de.rub.nds.asn1.model.Asn1Integer;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public class Asn1IntegerParser<Context extends AbstractContext>
        extends Asn1FieldParser<Context, Asn1Integer<Context>> {

    public Asn1IntegerParser(Context context, Asn1Integer asn1Integer) {
        super(context, asn1Integer);
    }

    @Override
    public void parseIndividualContentFields(InputStream byteArrayInputStream) throws IOException {
        byte[] allBytes = byteArrayInputStream.readAllBytes();
        encodable.setValue(new BigInteger(allBytes));
    }
}

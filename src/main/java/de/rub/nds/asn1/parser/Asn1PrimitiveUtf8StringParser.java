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
import de.rub.nds.asn1.model.Asn1PrimitiveUtf8String;
import java.io.IOException;
import java.io.InputStream;

public class Asn1PrimitiveUtf8StringParser<Context extends AbstractContext>
        extends Asn1FieldParser<Context, Asn1PrimitiveUtf8String<Context>> {

    public Asn1PrimitiveUtf8StringParser(
            Context context, Asn1PrimitiveUtf8String asn1PrimitiveUtf8String) {
        super(context, asn1PrimitiveUtf8String);
    }

    @Override
    public void parseIndividualContentFields(InputStream byteArrayInputStream) throws IOException {
        encodable.setValue(new String(byteArrayInputStream.readAllBytes()));
    }
}

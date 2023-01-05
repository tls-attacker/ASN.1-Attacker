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
import de.rub.nds.asn1.model.Asn1PrimitiveOctetString;
import java.io.IOException;
import java.io.InputStream;

public class Asn1PrimitiveOctetStringParser<Context extends AbstractChooser>
        extends Asn1FieldParser<Context, Asn1PrimitiveOctetString<Context>> {

    public Asn1PrimitiveOctetStringParser(
            Context context, Asn1PrimitiveOctetString asn1PrimitiveOctetString) {
        super(context, asn1PrimitiveOctetString);
    }

    @Override
    public void parseIndividualContentFields(InputStream stream) throws IOException {
        encodable.setValue(stream.readAllBytes());
    }
}

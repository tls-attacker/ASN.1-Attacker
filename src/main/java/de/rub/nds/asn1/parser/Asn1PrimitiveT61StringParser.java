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
import de.rub.nds.asn1.model.Asn1PrimitiveT61String;
import java.io.IOException;
import java.io.InputStream;

public class Asn1PrimitiveT61StringParser<Chooser extends AbstractChooser>
        extends Asn1FieldParser<Chooser, Asn1PrimitiveT61String<Chooser>> {

    public Asn1PrimitiveT61StringParser(
            Chooser chooser, Asn1PrimitiveT61String asn1PrimitiveT61String) {
        super(chooser, asn1PrimitiveT61String);
    }

    @Override
    public void parseIndividualContentFields(InputStream byteArrayInputStream) throws IOException {
        encodable.setValue(new String(byteArrayInputStream.readAllBytes()));
    }
}

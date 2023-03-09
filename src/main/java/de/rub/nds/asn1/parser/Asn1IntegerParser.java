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
import de.rub.nds.asn1.model.Asn1Integer;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public class Asn1IntegerParser<Chooser extends AbstractChooser>
        extends Asn1FieldParser<Chooser, Asn1Integer<Chooser>> {

    public Asn1IntegerParser(Chooser chooser, Asn1Integer<Chooser> asn1Integer) {
        super(chooser, asn1Integer);
    }

    @Override
    public void parseIndividualContentFields(InputStream byteArrayInputStream) throws IOException {
        byte[] allBytes = byteArrayInputStream.readAllBytes();
        encodable.setValue(new BigInteger(allBytes));
    }
}

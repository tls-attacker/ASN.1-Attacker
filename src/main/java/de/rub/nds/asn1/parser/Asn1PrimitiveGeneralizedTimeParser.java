/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1PrimitiveGeneralizedTime;
import java.io.IOException;
import java.io.InputStream;

public class Asn1PrimitiveGeneralizedTimeParser extends Asn1FieldParser<Asn1PrimitiveGeneralizedTime> {

    public Asn1PrimitiveGeneralizedTimeParser(Asn1PrimitiveGeneralizedTime asn1PrimitiveGeneralized) {
        super(asn1PrimitiveGeneralized);
    }

    @Override
    public void parseIndividualContentFields(InputStream byteArrayInputStream) throws IOException {
        encodable.setValue(new String(byteArrayInputStream.readAllBytes()));
    }

}

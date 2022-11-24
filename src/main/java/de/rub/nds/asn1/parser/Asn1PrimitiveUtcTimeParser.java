/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1PrimitiveUtcTime;
import java.io.IOException;
import java.io.InputStream;

public class Asn1PrimitiveUtcTimeParser extends Asn1FieldParser<Asn1PrimitiveUtcTime> {

    public Asn1PrimitiveUtcTimeParser(Asn1PrimitiveUtcTime asn1PrimitiveUtcTime) {
        super(asn1PrimitiveUtcTime);
    }

    @Override
    public void parseIndividualContentFields(InputStream byteArrayInputStream) throws IOException {
        encodable.setValue(new String(byteArrayInputStream.readAllBytes()));
    }

}

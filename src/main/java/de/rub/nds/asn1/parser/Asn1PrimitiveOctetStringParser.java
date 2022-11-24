/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1PrimitiveOctetString;
import java.io.IOException;
import java.io.InputStream;

public class Asn1PrimitiveOctetStringParser extends Asn1FieldParser<Asn1PrimitiveOctetString> {

    public Asn1PrimitiveOctetStringParser(Asn1PrimitiveOctetString asn1PrimitiveOctetString) {
        super(asn1PrimitiveOctetString);
    }

    @Override
    public void parseIndividualContentFields(InputStream stream) throws IOException {
        encodable.setValue(stream.readAllBytes());
    }

}

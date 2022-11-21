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
import java.io.InputStream;

public class Asn1PrimitiveOctetStringParser extends Asn1Parser<Asn1PrimitiveOctetString> {

    private final String identifier;

    public Asn1PrimitiveOctetStringParser(String identifier, InputStream inputStream) {
        super(inputStream);
        this.identifier = identifier;
    }

    @Override
    public Asn1PrimitiveOctetString parse() {
        Asn1PrimitiveOctetString asn1PrimitiveOctetString = new Asn1PrimitiveOctetString(identifier);
        genericParse(asn1PrimitiveOctetString);
        return asn1PrimitiveOctetString;
    }

}

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
import java.io.InputStream;

public class Asn1PrimitiveUtcTimeParser extends Asn1Parser<Asn1PrimitiveUtcTime> {

    private final String identifier;

    public Asn1PrimitiveUtcTimeParser(String identifier, InputStream inputStream) {
        super(inputStream);
        this.identifier = identifier;
    }

    @Override
    public Asn1PrimitiveUtcTime parse() {
        Asn1PrimitiveUtcTime asn1PrimitiveUtcTime = new Asn1PrimitiveUtcTime(identifier);
        genericParse(asn1PrimitiveUtcTime);
        return asn1PrimitiveUtcTime;
    }

}

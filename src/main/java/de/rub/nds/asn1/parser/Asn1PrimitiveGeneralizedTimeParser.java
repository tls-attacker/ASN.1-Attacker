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
import java.io.InputStream;

public class Asn1PrimitiveGeneralizedTimeParser extends Asn1Parser<Asn1PrimitiveGeneralizedTime> {

    private final String identifier;

    public Asn1PrimitiveGeneralizedTimeParser(String identifier, InputStream inputStream) {
        super(inputStream);
        this.identifier = identifier;
    }

    @Override
    public Asn1PrimitiveGeneralizedTime parse() {
        Asn1PrimitiveGeneralizedTime asn1PrimitiveGeneralizedTime = new Asn1PrimitiveGeneralizedTime(identifier);
        genericParse(asn1PrimitiveGeneralizedTime);
        return asn1PrimitiveGeneralizedTime;
    }

}

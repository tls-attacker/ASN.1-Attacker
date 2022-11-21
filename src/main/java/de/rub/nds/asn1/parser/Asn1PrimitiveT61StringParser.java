/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1PrimitiveT61String;
import java.io.InputStream;

public class Asn1PrimitiveT61StringParser extends Asn1Parser<Asn1PrimitiveT61String> {

    private final String identifier;

    public Asn1PrimitiveT61StringParser(String identifier, InputStream inputStream) {
        super(inputStream);
        this.identifier = identifier;
    }

    @Override
    public Asn1PrimitiveT61String parse() {
        Asn1PrimitiveT61String asn1PrimitiveT61String = new Asn1PrimitiveT61String(identifier);
        genericParse(asn1PrimitiveT61String);
        return asn1PrimitiveT61String;
    }

}

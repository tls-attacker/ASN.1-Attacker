/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1PrimitiveIa5String;
import java.io.InputStream;

public class Asn1PrimitiveIa5StringParser extends Asn1Parser<Asn1PrimitiveIa5String> {

    public Asn1PrimitiveIa5StringParser(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public Asn1PrimitiveIa5String parse() {
        Asn1PrimitiveIa5String asn1PrimitiveIa5String = new Asn1PrimitiveIa5String();
        genericParse(asn1PrimitiveIa5String);
        return asn1PrimitiveIa5String;
    }

}

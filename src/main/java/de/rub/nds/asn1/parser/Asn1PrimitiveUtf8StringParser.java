/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1PrimitiveUtf8String;
import java.io.InputStream;

public class Asn1PrimitiveUtf8StringParser extends Asn1Parser<Asn1PrimitiveUtf8String> {

    public Asn1PrimitiveUtf8StringParser(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public Asn1PrimitiveUtf8String parse() {
        Asn1PrimitiveUtf8String asn1PrimitiveUtf8String = new Asn1PrimitiveUtf8String();
        genericParse(asn1PrimitiveUtf8String);
        return asn1PrimitiveUtf8String;
    }

}

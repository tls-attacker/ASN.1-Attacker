/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1Implicit;
import java.io.InputStream;

public class Asn1ImplicitParser extends Asn1Parser<Asn1Implicit> {

    public Asn1ImplicitParser(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public Asn1Implicit parse() {
        Asn1Implicit asn1Implicit = new Asn1Implicit();
        genericParse(asn1Implicit);
        return asn1Implicit;
    }
}

/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1Explicit;
import java.io.InputStream;

public class Asn1ExplicitParser extends Asn1Parser<Asn1Explicit> {

    public Asn1ExplicitParser(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public Asn1Explicit parse() {
        Asn1Explicit asn1Explicit = new Asn1Explicit();
        genericParse(asn1Explicit);
        return asn1Explicit;
    }
}

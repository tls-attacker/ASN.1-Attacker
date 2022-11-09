/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1Integer;
import java.io.InputStream;

public class Asn1IntegerParser extends Asn1Parser<Asn1Integer> {

    public Asn1IntegerParser(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public Asn1Integer parse() {
        Asn1Integer asn1Integer = new Asn1Integer();
        genericParse(asn1Integer);
        return asn1Integer;
    }
}

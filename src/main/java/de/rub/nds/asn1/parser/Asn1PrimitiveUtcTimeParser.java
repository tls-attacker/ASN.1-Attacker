/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
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

    public Asn1PrimitiveUtcTimeParser(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public Asn1PrimitiveUtcTime parse() {
        Asn1PrimitiveUtcTime asn1PrimitiveUtcTime = new Asn1PrimitiveUtcTime();
        genericParse(asn1PrimitiveUtcTime);
        return asn1PrimitiveUtcTime;
    }

}

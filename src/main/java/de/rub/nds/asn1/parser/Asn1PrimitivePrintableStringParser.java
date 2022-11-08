/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1PrimitivePrintableString;
import java.io.InputStream;

public class Asn1PrimitivePrintableStringParser extends Asn1Parser<Asn1PrimitivePrintableString> {

    public Asn1PrimitivePrintableStringParser(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public Asn1PrimitivePrintableString parse() {
        Asn1PrimitivePrintableString asn1PrintableString = new Asn1PrimitivePrintableString();
        genericParse(asn1PrintableString);
        return asn1PrintableString;
    }

}

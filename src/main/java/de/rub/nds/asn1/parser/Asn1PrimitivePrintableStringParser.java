/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
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

    private final String identifier;

    public Asn1PrimitivePrintableStringParser(String identifier, InputStream inputStream) {
        super(inputStream);
        this.identifier = identifier;
    }

    @Override
    public Asn1PrimitivePrintableString parse() {
        Asn1PrimitivePrintableString asn1PrintableString = new Asn1PrimitivePrintableString(identifier);
        genericParse(asn1PrintableString);
        return asn1PrintableString;
    }

}

/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1PrimitiveBitString;
import java.io.InputStream;

public class Asn1PrimitiveBitStringParser extends Asn1Parser<Asn1PrimitiveBitString> {

    private final String identifier;

    public Asn1PrimitiveBitStringParser(String identifier, InputStream inputStream) {
        super(inputStream);
        this.identifier = identifier;
    }

    @Override
    public Asn1PrimitiveBitString parse() {
        Asn1PrimitiveBitString asn1PrimitiveBitString = new Asn1PrimitiveBitString(identifier);
        genericParse(asn1PrimitiveBitString);
        return asn1PrimitiveBitString;
    }

}

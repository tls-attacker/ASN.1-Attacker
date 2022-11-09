/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1EncapsulatingBitString;
import java.io.InputStream;

public class Asn1EncapsulatingBitStringParser extends Asn1Parser<Asn1EncapsulatingBitString> {

    public Asn1EncapsulatingBitStringParser(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public Asn1EncapsulatingBitString parse() {
        Asn1EncapsulatingBitString asn1EncapsulatingBitString = new Asn1EncapsulatingBitString();
        genericParse(asn1EncapsulatingBitString);
        return asn1EncapsulatingBitString;
    }
}

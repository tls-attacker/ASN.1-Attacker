/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1RawBytes;
import java.io.InputStream;

public class Asn1RawBytesParser extends Asn1Parser<Asn1RawBytes> {

    private final String identifier;
    
    public Asn1RawBytesParser(String identifier, InputStream inputStream) {
        super(inputStream);
        this.identifier = identifier;
    }

    @Override
    public Asn1RawBytes parse() {
        Asn1RawBytes asn1RawBytes = new Asn1RawBytes(identifier);
        genericParse(asn1RawBytes);
        return asn1RawBytes;
    }

}

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
import java.io.IOException;
import java.io.InputStream;

public class Asn1PrimitiveBitStringParser extends Asn1FieldParser<Asn1PrimitiveBitString> {

    public Asn1PrimitiveBitStringParser(Asn1PrimitiveBitString asn1PrimitiveBitString) {
        super(asn1PrimitiveBitString);
    }

    @Override
    public void parseIndividualContentFields(InputStream byteArrayInputStream) throws IOException {
        encodable.setValue(byteArrayInputStream.readAllBytes()); //TODO fix unused bits
        encodable.setUnusedBits((byte)0); // TODO not correct
    }

}

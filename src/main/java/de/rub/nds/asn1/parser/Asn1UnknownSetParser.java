/*
 * ASN.1-Attacker - A Library for Arbitrary ASN.1 Structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1UnknownField;
import de.rub.nds.asn1.model.Asn1UnknownSet;
import de.rub.nds.protocol.exception.ParserException;
import java.io.BufferedInputStream;
import java.io.IOException;

public class Asn1UnknownSetParser extends Asn1Parser<Asn1UnknownSet> {

    public Asn1UnknownSetParser(Asn1UnknownSet asn1Set) {
        super(asn1Set);
    }

    @Override
    public void parse(BufferedInputStream inputStream) {
        try {
            while (inputStream.available() > 0) {
                Asn1UnknownField unknownFiled = new Asn1UnknownField("unknown", null, null, null);
                ParserHelper.parseStructure(unknownFiled, inputStream);
                encodable.addChild(unknownFiled);
            }
        } catch (IOException e) {
            throw new ParserException("Cannot parse unknown Asn1Sequence", e);
        }
    }
}

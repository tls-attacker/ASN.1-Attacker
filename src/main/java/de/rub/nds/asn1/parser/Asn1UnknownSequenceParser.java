/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1UnknownField;
import de.rub.nds.asn1.model.Asn1UnknownSequence;
import de.rub.nds.protocol.exception.ParserException;
import java.io.IOException;
import java.io.InputStream;

public class Asn1UnknownSequenceParser extends Asn1Parser<Asn1UnknownSequence> {

    public Asn1UnknownSequenceParser(Asn1UnknownSequence asn1Sequence) {
        super(asn1Sequence);
    }

    @Override
    public void parse(InputStream inputStream) {
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

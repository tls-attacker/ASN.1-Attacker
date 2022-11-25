/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1Encodable;
import de.rub.nds.asn1.model.Asn1Sequence;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Asn1SequenceOfParser extends Asn1FieldParser<Asn1Sequence> {

    private static final Logger LOGGER = LogManager.getLogger();

    public Asn1SequenceOfParser(Asn1Sequence asn1Sequence) {
        super(asn1Sequence);
    }

    @Override
    public void parseIndividualContentFields(InputStream inputStream) throws IOException {
        List<Asn1Encodable> childrenList = new LinkedList<>();
        do {
            Asn1Encodable freshElement = createFreshElement();
            freshElement.getParser().parse(inputStream);
            childrenList.add(freshElement);
        } while (inputStream.available() > 0);
        encodable.setChildren(childrenList);
    }

    protected abstract Asn1Encodable createFreshElement();
}

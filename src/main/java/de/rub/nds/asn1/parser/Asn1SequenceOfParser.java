/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.model.Asn1Encodable;
import de.rub.nds.asn1.model.Asn1Sequence;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Asn1SequenceOfParser<Chooser extends AbstractChooser>
        extends Asn1FieldParser<Chooser, Asn1Sequence<Chooser>> {

    private static final Logger LOGGER = LogManager.getLogger();

    public Asn1SequenceOfParser(Chooser chooser, Asn1Sequence asn1Sequence) {
        super(chooser, asn1Sequence);
    }

    @Override
    public void parseIndividualContentFields(InputStream inputStream) throws IOException {
        List<Asn1Encodable<Chooser>> childrenList = new LinkedList<>();
        do {
            Asn1Encodable freshElement = createFreshElement();
            freshElement.getParser(chooser).parse(inputStream);
            childrenList.add(freshElement);
        } while (inputStream.available() > 0);
        encodable.setChildren(childrenList);
    }

    protected abstract Asn1Encodable createFreshElement();
}

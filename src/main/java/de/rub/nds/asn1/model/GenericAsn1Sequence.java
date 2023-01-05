/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.parser.GenericAsn1SequenceParser;

public class GenericAsn1Sequence<Context extends AbstractChooser> extends Asn1Sequence<Context> {

    public GenericAsn1Sequence(String identifier) {
        super(identifier);
    }

    @Override
    public GenericAsn1SequenceParser getParser(Context context) {
        return new GenericAsn1SequenceParser(context, this);
    }
}

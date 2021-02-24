/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public abstract class FieldTranslator<T extends Asn1Encodable> {

    protected FieldTranslator(final IntermediateAsn1Field intermediateAsn1Field) {

    }

    public abstract T translate(final String identifier, final String type);
}

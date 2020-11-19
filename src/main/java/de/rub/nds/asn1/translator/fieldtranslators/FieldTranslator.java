/*
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2020 Ruhr University Bochum, Paderborn University,
 * and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public abstract class FieldTranslator<T extends Asn1Encodable> {

    protected FieldTranslator(final IntermediateAsn1Field intermediateAsn1Field) {

    }

    public abstract T translate(final String identifier, final String type);
}

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

import de.rub.nds.asn1.model.Asn1ConstructedT61String;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1ConstructedT61StringFT extends Asn1FieldFT<Asn1ConstructedT61String> {

    public Asn1ConstructedT61StringFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1ConstructedT61String());
    }

    protected Asn1ConstructedT61StringFT(final IntermediateAsn1Field intermediateAsn1Field,
        final Asn1ConstructedT61String asn1ConstructedT61String) {
        super(intermediateAsn1Field, asn1ConstructedT61String);
    }
}

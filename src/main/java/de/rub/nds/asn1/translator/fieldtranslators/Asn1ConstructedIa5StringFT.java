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

import de.rub.nds.asn1.model.Asn1ConstructedIa5String;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1ConstructedIa5StringFT extends Asn1FieldFT<Asn1ConstructedIa5String> {

    public Asn1ConstructedIa5StringFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1ConstructedIa5String());
    }

    protected Asn1ConstructedIa5StringFT(final IntermediateAsn1Field intermediateAsn1Field,
        final Asn1ConstructedIa5String asn1ConstructedIa5String) {
        super(intermediateAsn1Field, asn1ConstructedIa5String);
    }
}

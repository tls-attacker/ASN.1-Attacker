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

import de.rub.nds.asn1.model.Asn1ConstructedBitString;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1ConstructedBitStringFT extends Asn1FieldFT<Asn1ConstructedBitString> {

    public Asn1ConstructedBitStringFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1ConstructedBitString());
    }

    protected Asn1ConstructedBitStringFT(final IntermediateAsn1Field intermediateAsn1Field,
        final Asn1ConstructedBitString asn1ConstructedBitString) {
        super(intermediateAsn1Field, asn1ConstructedBitString);
    }
}

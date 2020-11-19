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

import de.rub.nds.asn1.model.Asn1Set;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1SetFT extends Asn1FieldFT<Asn1Set> {

    public Asn1SetFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1Set());
    }

    protected Asn1SetFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1Set asn1Set) {
        super(intermediateAsn1Field, asn1Set);
    }
}

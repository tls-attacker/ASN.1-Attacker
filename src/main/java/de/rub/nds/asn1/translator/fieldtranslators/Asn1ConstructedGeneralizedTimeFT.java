/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1ConstructedGeneralizedTime;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1ConstructedGeneralizedTimeFT extends Asn1FieldFT<Asn1ConstructedGeneralizedTime> {

    public Asn1ConstructedGeneralizedTimeFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1ConstructedGeneralizedTime());
    }

    protected Asn1ConstructedGeneralizedTimeFT(final IntermediateAsn1Field intermediateAsn1Field,
        final Asn1ConstructedGeneralizedTime asn1ConstructedGeneralizedTime) {
        super(intermediateAsn1Field, asn1ConstructedGeneralizedTime);
    }
}

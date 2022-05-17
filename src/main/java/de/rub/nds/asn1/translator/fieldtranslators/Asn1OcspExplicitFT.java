/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1Explicit;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1OcspExplicitFT extends Asn1FieldFT<Asn1Explicit> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1Explicit asn1Explicit;

    public Asn1OcspExplicitFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1Explicit());
    }

    protected Asn1OcspExplicitFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1Explicit asn1Explicit) {
        super(intermediateAsn1Field, asn1Explicit);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1Explicit = asn1Explicit;
    }

    @Override
    public Asn1Explicit translate(final String identifier, final String type) {
        this.asn1Explicit.setOffset(this.intermediateAsn1Field.getTagNumber());
        return super.translate(identifier, type);
    }
}

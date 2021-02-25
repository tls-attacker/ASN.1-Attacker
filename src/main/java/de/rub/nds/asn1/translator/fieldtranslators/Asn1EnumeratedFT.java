/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1Enumerated;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;
import java.math.BigInteger;

public final class Asn1EnumeratedFT extends Asn1FieldFT<Asn1Enumerated> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1Enumerated asn1Enumerated;

    public Asn1EnumeratedFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1Enumerated());
    }

    protected Asn1EnumeratedFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1Enumerated asn1Enumerated) {
        super(intermediateAsn1Field, asn1Enumerated);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1Enumerated = asn1Enumerated;
    }

    @Override
    public Asn1Enumerated translate(final String identifier, final String type) {
        BigInteger value = new BigInteger(this.intermediateAsn1Field.getContent());
        this.asn1Enumerated.setValue(value);
        return super.translate(identifier, type);
    }
}

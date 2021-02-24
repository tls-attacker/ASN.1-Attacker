/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1ConstructedUtf8String;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1ConstructedUtf8StringFT extends Asn1FieldFT<Asn1ConstructedUtf8String> {

    public Asn1ConstructedUtf8StringFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1ConstructedUtf8String());
    }

    protected Asn1ConstructedUtf8StringFT(final IntermediateAsn1Field intermediateAsn1Field,
        final Asn1ConstructedUtf8String asn1ConstructedUtf8String) {
        super(intermediateAsn1Field, asn1ConstructedUtf8String);
    }
}

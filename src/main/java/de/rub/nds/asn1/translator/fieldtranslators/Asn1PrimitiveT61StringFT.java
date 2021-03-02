/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1PrimitiveT61String;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;
import java.io.UnsupportedEncodingException;

public final class Asn1PrimitiveT61StringFT extends Asn1FieldFT<Asn1PrimitiveT61String> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1PrimitiveT61String asn1PrimitiveT61String;

    public Asn1PrimitiveT61StringFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1PrimitiveT61String());
    }

    protected Asn1PrimitiveT61StringFT(final IntermediateAsn1Field intermediateAsn1Field,
        final Asn1PrimitiveT61String asn1PrimitiveT61String) {
        super(intermediateAsn1Field, asn1PrimitiveT61String);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1PrimitiveT61String = asn1PrimitiveT61String;
    }

    @Override
    public Asn1PrimitiveT61String translate(final String identifier, final String type) {
        try {
            // Todo: Conversion of T.61 character set
            String value = new String(this.intermediateAsn1Field.getContent(), "UTF-8");
            this.asn1PrimitiveT61String.setValue(value);
            return super.translate(identifier, type);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}

/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1PrimitiveUtf8String;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;
import java.io.UnsupportedEncodingException;

public final class Asn1PrimitiveUtf8StringFT extends Asn1FieldFT<Asn1PrimitiveUtf8String> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1PrimitiveUtf8String asn1PrimitiveUtf8String;

    public Asn1PrimitiveUtf8StringFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1PrimitiveUtf8String());
    }

    protected Asn1PrimitiveUtf8StringFT(final IntermediateAsn1Field intermediateAsn1Field,
        final Asn1PrimitiveUtf8String asn1PrimitiveUtf8String) {
        super(intermediateAsn1Field, asn1PrimitiveUtf8String);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1PrimitiveUtf8String = asn1PrimitiveUtf8String;
    }

    @Override
    public Asn1PrimitiveUtf8String translate(final String identifier, final String type) {
        try {
            String value = new String(this.intermediateAsn1Field.getContent(), "UTF-8");
            this.asn1PrimitiveUtf8String.setValue(value);
            return super.translate(identifier, type);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}

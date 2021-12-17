/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1PrimitiveUtcTime;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;
import java.io.UnsupportedEncodingException;

public final class Asn1PrimitiveUtcTimeFT extends Asn1FieldFT<Asn1PrimitiveUtcTime> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1PrimitiveUtcTime asn1PrimitiveUtcTime;

    public Asn1PrimitiveUtcTimeFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1PrimitiveUtcTime());
    }

    protected Asn1PrimitiveUtcTimeFT(final IntermediateAsn1Field intermediateAsn1Field,
        final Asn1PrimitiveUtcTime asn1PrimitiveUtcTime) {
        super(intermediateAsn1Field, asn1PrimitiveUtcTime);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1PrimitiveUtcTime = asn1PrimitiveUtcTime;
    }

    @Override
    public Asn1PrimitiveUtcTime translate(final String identifier, final String type) {
        try {
            String value = new String(this.intermediateAsn1Field.getContent(), "UTF-8");
            this.asn1PrimitiveUtcTime.setValue(value);
            return super.translate(identifier, type);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}

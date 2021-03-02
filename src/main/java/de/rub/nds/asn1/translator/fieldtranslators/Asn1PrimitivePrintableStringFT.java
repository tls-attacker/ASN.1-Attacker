/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1PrimitivePrintableString;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;
import java.io.UnsupportedEncodingException;

public final class Asn1PrimitivePrintableStringFT extends Asn1FieldFT<Asn1PrimitivePrintableString> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1PrimitivePrintableString asn1PrimitivePrintableString;

    public Asn1PrimitivePrintableStringFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1PrimitivePrintableString());
    }

    protected Asn1PrimitivePrintableStringFT(final IntermediateAsn1Field intermediateAsn1Field,
        final Asn1PrimitivePrintableString asn1PrimitivePrintableString) {
        super(intermediateAsn1Field, asn1PrimitivePrintableString);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1PrimitivePrintableString = asn1PrimitivePrintableString;
    }

    @Override
    public Asn1PrimitivePrintableString translate(final String identifier, final String type) {
        try {
            String value = new String(this.intermediateAsn1Field.getContent(), "UTF-8");
            this.asn1PrimitivePrintableString.setValue(value);
            return super.translate(identifier, type);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}

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
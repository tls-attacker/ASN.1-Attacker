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

import de.rub.nds.asn1.model.Asn1PrimitiveIa5String;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;
import java.io.UnsupportedEncodingException;

public final class Asn1PrimitiveIa5StringFT extends Asn1FieldFT<Asn1PrimitiveIa5String> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1PrimitiveIa5String asn1PrimitiveIa5String;

    public Asn1PrimitiveIa5StringFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1PrimitiveIa5String());
    }

    protected Asn1PrimitiveIa5StringFT(final IntermediateAsn1Field intermediateAsn1Field,
        final Asn1PrimitiveIa5String asn1PrimitiveIa5String) {
        super(intermediateAsn1Field, asn1PrimitiveIa5String);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1PrimitiveIa5String = asn1PrimitiveIa5String;
    }

    @Override
    public Asn1PrimitiveIa5String translate(final String identifier, final String type) {
        try {
            String value = new String(this.intermediateAsn1Field.getContent(), "UTF-8");
            this.asn1PrimitiveIa5String.setValue(value);
            return super.translate(identifier, type);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}

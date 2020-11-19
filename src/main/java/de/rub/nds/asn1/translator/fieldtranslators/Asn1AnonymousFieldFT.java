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

import de.rub.nds.asn1.model.Asn1AnonymousField;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1AnonymousFieldFT extends Asn1FieldFT<Asn1AnonymousField> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1AnonymousField asn1AnonymousField;

    public Asn1AnonymousFieldFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1AnonymousField());
    }

    protected Asn1AnonymousFieldFT(final IntermediateAsn1Field intermediateAsn1Field,
        final Asn1AnonymousField asn1AnonymousField) {
        super(intermediateAsn1Field, asn1AnonymousField);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1AnonymousField = asn1AnonymousField;
    }

    @Override
    public Asn1AnonymousField translate(final String identifier, final String type) {
        this.asn1AnonymousField.setContent(intermediateAsn1Field.getContent());
        return super.translate(identifier, type);
    }
}

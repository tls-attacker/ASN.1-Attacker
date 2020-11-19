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

import de.rub.nds.asn1.model.Asn1Field;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public abstract class Asn1FieldFT<T extends Asn1Field> extends FieldTranslator<T> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1Field asn1Field;

    protected Asn1FieldFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1Field asn1Field) {
        super(intermediateAsn1Field);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1Field = asn1Field;
    }

    @Override
    public T translate(final String identifier, final String type) {
        this.asn1Field.setTagClass(this.intermediateAsn1Field.getTagClass());
        this.asn1Field.setTagConstructed(this.intermediateAsn1Field.getTagConstructed());
        this.asn1Field.setTagNumber(this.intermediateAsn1Field.getTagNumber());
        this.asn1Field.setIdentifier(identifier);
        this.asn1Field.setType(type);
        return (T) this.asn1Field;
    }
}

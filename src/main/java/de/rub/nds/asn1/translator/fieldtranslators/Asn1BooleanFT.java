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

import de.rub.nds.asn1.model.Asn1Boolean;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1BooleanFT extends Asn1FieldFT<Asn1Boolean> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1Boolean asn1Boolean;

    public Asn1BooleanFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1Boolean());
    }

    protected Asn1BooleanFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1Boolean asn1Boolean) {
        super(intermediateAsn1Field, asn1Boolean);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1Boolean = asn1Boolean;
    }

    @Override
    public Asn1Boolean translate(final String identifier, final String type) {
        byte[] content = intermediateAsn1Field.getContent();
        this.asn1Boolean.setValue((content != null && content.length > 0 && content[0] != 0) ? true : false);
        return super.translate(identifier, type);
    }
}

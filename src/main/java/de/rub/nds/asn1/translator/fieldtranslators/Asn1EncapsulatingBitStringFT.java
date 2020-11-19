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

import de.rub.nds.asn1.model.Asn1EncapsulatingBitString;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1EncapsulatingBitStringFT extends Asn1FieldFT<Asn1EncapsulatingBitString> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1EncapsulatingBitString asn1EncapsulatingBitString;

    public Asn1EncapsulatingBitStringFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1EncapsulatingBitString());
    }

    protected Asn1EncapsulatingBitStringFT(final IntermediateAsn1Field intermediateAsn1Field,
        final Asn1EncapsulatingBitString asn1EncapsulatingBitString) {
        super(intermediateAsn1Field, asn1EncapsulatingBitString);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1EncapsulatingBitString = asn1EncapsulatingBitString;
    }

    @Override
    public Asn1EncapsulatingBitString translate(final String identifier, final String type) {
        this.asn1EncapsulatingBitString.setContent(this.intermediateAsn1Field.getContent());
        return super.translate(identifier, type);
    }
}

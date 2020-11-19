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

import de.rub.nds.asn1.model.Asn1EncapsulatingOctetString;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1EncapsulatingOctetStringFT extends Asn1FieldFT<Asn1EncapsulatingOctetString> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1EncapsulatingOctetString asn1EncapsulatingOctetString;

    public Asn1EncapsulatingOctetStringFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1EncapsulatingOctetString());
    }

    protected Asn1EncapsulatingOctetStringFT(final IntermediateAsn1Field intermediateAsn1Field,
        final Asn1EncapsulatingOctetString asn1EncapsulatingOctetString) {
        super(intermediateAsn1Field, asn1EncapsulatingOctetString);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1EncapsulatingOctetString = asn1EncapsulatingOctetString;
    }

    @Override
    public Asn1EncapsulatingOctetString translate(final String identifier, final String type) {
        this.asn1EncapsulatingOctetString.setContent(this.intermediateAsn1Field.getContent());
        return super.translate(identifier, type);
    }
}

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

import de.rub.nds.asn1.model.Asn1Null;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;
import java.math.BigInteger;

public final class Asn1OcspCertificateGoodNullFT extends Asn1FieldFT<Asn1Null> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1Null asn1Null;

    public Asn1OcspCertificateGoodNullFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1Null());
    }

    protected Asn1OcspCertificateGoodNullFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1Null asn1Null) {
        super(intermediateAsn1Field, asn1Null);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1Null = asn1Null;
    }

    @Override
    public Asn1Null translate(final String identifier, final String type) {
        this.asn1Null.setIdentifierOctets(BigInteger.valueOf(this.intermediateAsn1Field.getTag()).toByteArray());
        return super.translate(identifier, type);
    }
}

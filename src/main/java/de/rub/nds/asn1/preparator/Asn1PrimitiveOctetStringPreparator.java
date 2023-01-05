/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.context.AbstractContext;
import de.rub.nds.asn1.model.Asn1PrimitiveOctetString;

public class Asn1PrimitiveOctetStringPreparator<Context extends AbstractContext>
        extends Asn1FieldPreparator<Context, Asn1PrimitiveOctetString> {

    private final Asn1PrimitiveOctetString asn1PrimitiveOctetString;

    public Asn1PrimitiveOctetStringPreparator(
            Context context, Asn1PrimitiveOctetString asn1PrimitiveOctetString) {
        super(context, asn1PrimitiveOctetString);
        this.asn1PrimitiveOctetString = asn1PrimitiveOctetString;
    }

    @Override
    protected byte[] encodeContent() {
        return this.asn1PrimitiveOctetString.getValue().getValue();
    }
}

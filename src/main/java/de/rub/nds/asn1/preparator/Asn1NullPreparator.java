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
import de.rub.nds.asn1.model.Asn1Null;

public class Asn1NullPreparator<Context extends AbstractContext>
        extends Asn1FieldPreparator<Context, Asn1Null> {

    public Asn1NullPreparator(Context context, final Asn1Null asn1null) {
        super(context, asn1null);
    }

    @Override
    protected byte[] encodeContent() {
        return new byte[0];
    }
}

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
import de.rub.nds.asn1.model.Asn1PrimitiveUtcTime;

public class Asn1PrimitiveUtcTimePreparator<Context extends AbstractContext>
        extends Asn1FieldPreparator<Context, Asn1PrimitiveUtcTime> {

    private final Asn1PrimitiveUtcTime asn1PrimitiveUtcTime;

    public Asn1PrimitiveUtcTimePreparator(
            Context context, Asn1PrimitiveUtcTime asn1PrimitiveUtcTime) {
        super(context, asn1PrimitiveUtcTime);
        this.asn1PrimitiveUtcTime = asn1PrimitiveUtcTime;
    }

    @Override
    protected byte[] encodeContent() {
        return this.asn1PrimitiveUtcTime.getValue().getValue().getBytes();
    }
}

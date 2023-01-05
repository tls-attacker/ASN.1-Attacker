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
import de.rub.nds.asn1.model.Asn1PrimitiveT61String;

public class Asn1PrimitiveT61StringPreparator<Context extends AbstractContext>
        extends Asn1FieldPreparator<Context, Asn1PrimitiveT61String> {

    private final Asn1PrimitiveT61String asn1PrimitiveT61String;

    public Asn1PrimitiveT61StringPreparator(
            Context context, Asn1PrimitiveT61String asn1PrimitiveT61String) {
        super(context, asn1PrimitiveT61String);
        this.asn1PrimitiveT61String = asn1PrimitiveT61String;
    }

    @Override
    protected byte[] encodeContent() {
        byte[] content = this.asn1PrimitiveT61String.getValue().getValue().getBytes();
        // Todo: Character set conversion
        return content;
    }
}

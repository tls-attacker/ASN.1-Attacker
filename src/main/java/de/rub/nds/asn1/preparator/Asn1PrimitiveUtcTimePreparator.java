/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.model.Asn1PrimitiveUtcTime;

public class Asn1PrimitiveUtcTimePreparator<Chooser extends AbstractChooser>
        extends Asn1FieldPreparator<Chooser, Asn1PrimitiveUtcTime<Chooser>> {

    private final Asn1PrimitiveUtcTime<Chooser> asn1PrimitiveUtcTime;

    public Asn1PrimitiveUtcTimePreparator(
            Chooser chooser, Asn1PrimitiveUtcTime<Chooser> asn1PrimitiveUtcTime) {
        super(chooser, asn1PrimitiveUtcTime);
        this.asn1PrimitiveUtcTime = asn1PrimitiveUtcTime;
    }

    @Override
    protected byte[] encodeContent() {
        return this.asn1PrimitiveUtcTime.getValue().getValue().getBytes();
    }
}

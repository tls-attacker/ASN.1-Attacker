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
import de.rub.nds.asn1.model.Asn1Integer;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import java.math.BigInteger;

public class Asn1IntegerPreparator<Chooser extends AbstractChooser>
        extends Asn1FieldPreparator<Chooser, Asn1Integer<Chooser>> {

    private final Asn1Integer asn1Integer;

    public Asn1IntegerPreparator(Chooser chooser, final Asn1Integer asn1Integer) {
        super(chooser, asn1Integer);
        this.asn1Integer = asn1Integer;
    }

    @Override
    protected byte[] encodeContent() {
        return encode(this.asn1Integer.getValue().getValue());
    }

    private byte[] encode(BigInteger bigInt) {
        int size = bigInt.toByteArray().length;
        if (bigInt.testBit(size * 8)) {
            return ArrayConverter.bigIntegerToByteArray(bigInt, size + 1, true);
        } else {
            return bigInt.toByteArray();
        }
    }
}

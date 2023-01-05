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
import de.rub.nds.asn1.model.Asn1PrimitiveBitString;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Asn1PrimitiveBitStringPreparator<Context extends AbstractContext>
        extends Asn1FieldPreparator<Context, Asn1PrimitiveBitString> {

    private final Asn1PrimitiveBitString asn1PrimitiveBitString;

    public Asn1PrimitiveBitStringPreparator(
            Context context, final Asn1PrimitiveBitString asn1PrimitiveBitString) {
        super(context, asn1PrimitiveBitString);
        this.asn1PrimitiveBitString = asn1PrimitiveBitString;
    }

    @Override
    protected byte[] encodeContent() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(new byte[] {this.asn1PrimitiveBitString.getUnusedBits().getValue()});
            outputStream.write(this.asn1PrimitiveBitString.getValue().getValue());
            return outputStream.toByteArray();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

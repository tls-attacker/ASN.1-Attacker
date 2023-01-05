/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.model.Asn1PrimitiveBitString;
import java.io.IOException;
import java.io.InputStream;

public class Asn1PrimitiveBitStringParser<Context extends AbstractChooser>
        extends Asn1FieldParser<Context, Asn1PrimitiveBitString<Context>> {

    public Asn1PrimitiveBitStringParser(
            Context context, Asn1PrimitiveBitString asn1PrimitiveBitString) {
        super(context, asn1PrimitiveBitString);
    }

    @Override
    public void parseIndividualContentFields(InputStream byteArrayInputStream) throws IOException {
        encodable.setValue(byteArrayInputStream.readAllBytes()); // TODO fix unused bits
        encodable.setUnusedBits((byte) 0); // TODO not correct
    }
}

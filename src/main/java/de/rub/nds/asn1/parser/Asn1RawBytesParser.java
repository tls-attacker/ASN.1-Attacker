/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1RawBytes;
import java.io.InputStream;

public class Asn1RawBytesParser extends Asn1Parser<Asn1RawBytes> {

    public Asn1RawBytesParser(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public Asn1RawBytes parse() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

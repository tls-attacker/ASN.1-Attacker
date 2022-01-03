/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.parser.contentunpackers;

public class DefaultContentUnpacker extends ContentUnpacker {

    static {
        ContentUnpackerRegister.getInstance().registerContentUnpacker(new DefaultContentUnpacker());
    }

    @Override
    public byte[] unpack(final byte[] content) {
        return content;
    }
}

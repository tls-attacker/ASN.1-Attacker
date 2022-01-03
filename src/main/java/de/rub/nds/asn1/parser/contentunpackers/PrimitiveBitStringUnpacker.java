/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.parser.contentunpackers;

public class PrimitiveBitStringUnpacker extends ContentUnpacker {

    static {
        ContentUnpackerRegister.getInstance().registerContentUnpacker(new DefaultContentUnpacker());
    }

    @Override
    public byte[] unpack(final byte[] content) {
        byte[] unpacked = new byte[0];
        if (content.length > 1) {
            unpacked = new byte[content.length - 1];
            System.arraycopy(content, 1, unpacked, 0, unpacked.length);
        }
        return unpacked;
    }
}

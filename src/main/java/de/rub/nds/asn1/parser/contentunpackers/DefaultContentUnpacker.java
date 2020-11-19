/*
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2020 Ruhr University Bochum, Paderborn University,
 * and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
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

/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.util;

import java.math.BigInteger;

public final class Asn1Header {

    private final int tag;
    private final BigInteger length;
    private final boolean isConstructed;

    public Asn1Header(int tag, BigInteger length, boolean isConstructed) {
        this.tag = tag;
        this.length = length;
        this.isConstructed = isConstructed;
    }

    public final int getTag() {
        return tag;
    }

    public final BigInteger getLength() {
        return length;
    }

    public final boolean isConstructed() {
        return isConstructed;
    }

    @Override
    public String toString() {
        return "Asn1Header{"
                + "tag="
                + tag
                + ", length="
                + length
                + ", isConstructed="
                + isConstructed
                + '}';
    }
}

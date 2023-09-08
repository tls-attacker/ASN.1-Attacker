/*
 * ASN.1-Attacker - A library for arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.util;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import java.math.BigInteger;

public final class Asn1Header {

    private final TagClass tagClass;
    private final int tagNumber;
    private final BigInteger length;
    private final TagConstructed tagConstructed;

    public Asn1Header(
            TagClass tagClass, int tagNumber, BigInteger length, TagConstructed tagConstructed) {
        this.tagClass = tagClass;
        this.tagNumber = tagNumber;
        this.length = length;
        this.tagConstructed = tagConstructed;
    }

    public final int getTagNumber() {
        return tagNumber;
    }

    public final BigInteger getLength() {
        return length;
    }

    public TagClass getTagClass() {
        return tagClass;
    }

    public TagConstructed getTagConstructed() {
        return tagConstructed;
    }
}

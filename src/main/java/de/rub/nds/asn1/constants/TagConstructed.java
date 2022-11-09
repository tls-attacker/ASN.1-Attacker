/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.constants;

public enum TagConstructed {
    PRIMITIVE(0, false),
    CONSTRUCTED(1, true);

    private final int intValue;

    private final boolean booleanValue;

    TagConstructed(final int intValue, final boolean booleanValue) {
        this.intValue = intValue;
        this.booleanValue = booleanValue;
    }

    public int getIntValue() {
        return this.intValue;
    }

    public boolean getBooleanValue() {
        return this.booleanValue;
    }

    public static TagConstructed fromIntValue(final int intValue) {
        TagConstructed tagConstructed = TagConstructed.PRIMITIVE;
        for (TagConstructed currentTagConstructed : TagConstructed.values()) {
            if (currentTagConstructed.getIntValue() == intValue) {
                tagConstructed = currentTagConstructed;
                break;
            }
        }
        return tagConstructed;
    }

    public static TagConstructed fromBooleanValue(final boolean booleanValue) {
        TagConstructed tagConstructed = TagConstructed.PRIMITIVE;
        for (TagConstructed currentTagConstructed : TagConstructed.values()) {
            if (currentTagConstructed.getBooleanValue() == booleanValue) {
                tagConstructed = currentTagConstructed;
                break;
            }
        }
        return tagConstructed;
    }

    public static TagConstructed fromIdentifierByte(final byte identifierByte) {
        return fromBooleanValue((identifierByte & 0x20) != 0);
    }
}

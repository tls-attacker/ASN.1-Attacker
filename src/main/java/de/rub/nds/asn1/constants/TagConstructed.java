/*
 * ASN.1-Attacker - A library for arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.constants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum TagConstructed {
    PRIMITIVE(0, false),
    CONSTRUCTED(1, true);

    private static final Logger LOGGER = LogManager.getLogger();

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
        for (TagConstructed currentTagConstructed : TagConstructed.values()) {
            if (currentTagConstructed.getIntValue() == intValue) {
                return currentTagConstructed;
            }
        }
        LOGGER.warn("Could not convert int value to TagConstructed");
        return null;
    }

    public static TagConstructed fromBooleanValue(final boolean booleanValue) {
        for (TagConstructed currentTagConstructed : TagConstructed.values()) {
            if (currentTagConstructed.getBooleanValue() == booleanValue) {
                return currentTagConstructed;
            }
        }
        return null;
    }

    public static TagConstructed fromIdentifierByte(final byte identifierByte) {
        return fromBooleanValue((identifierByte & 0x20) != 0);
    }
}

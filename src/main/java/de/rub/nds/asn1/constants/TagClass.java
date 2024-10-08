/*
 * ASN.1-Attacker - A Library for Arbitrary ASN.1 Structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.constants;

public enum TagClass {
    UNIVERSAL(0, "universal"),
    APPLICATION(1, "application"),
    CONTEXT_SPECIFIC(2, "context-specific"),
    PRIVATE(3, "private");

    private final int intValue;

    private final String stringValue;

    TagClass(int intValue, String stringValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
    }

    public int getIntValue() {
        return this.intValue;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public static TagClass fromIntValue(int intValue) {
        for (TagClass currentTagClass : TagClass.values()) {
            if (currentTagClass.getIntValue() == intValue) {
                return currentTagClass;
            }
        }
        return null;
    }

    public static TagClass fromStringValue(String stringValue) {
        for (TagClass currentTagClass : TagClass.values()) {
            if (currentTagClass.getStringValue().equalsIgnoreCase(stringValue)) {
                return currentTagClass;
            }
        }
        return null;
    }

    public static TagClass fromIdentifierByte(byte identifierByte) {
        return fromIntValue((identifierByte >> 6) & 0x03);
    }
}

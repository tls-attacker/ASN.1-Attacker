/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.constants;

public enum UniversalTagNumber {
    END_OF_CONTENT(0, "end-of-content"),
    BOOLEAN(1, "boolean"),
    INTEGER(2, "integer"),
    BIT_STRING(3, "bit string"),
    OCTET_STRING(4, "octet string"),
    NULL(5, "null"),
    OBJECT_IDENTIFIER(6, "object identifier"),
    OBJECT_DESCRIPTOR(7, "object descriptor"),
    EXTERNAL(8, "external"),
    REAL(9, "real"),
    ENUMERATED(10, "enumerated"),
    EMBEDDED_PDV(11, "embedded pdv"),
    UTF8STRING(12, "utf8string"),
    RELATIVE_OID(13, "relative-oid"),
    SEQUENCE(16, "sequence"),
    SET(17, "set"),
    NUMERICSTRING(18, "numericstring"),
    PRINTABLESTRING(19, "printablestring"),
    T61STRING(20, "t61string"),
    VIDEOTEXSTRING(21, "videotexstring"),
    IA5STRING(22, "ia5string"),
    UTCTIME(23, "utctime"),
    GENERALIZEDTIME(24, "generalizedtime"),
    GRAPHICSTRING(25, "graphicstring"),
    VISIBLESTRING(26, "visiblestring"),
    GENERALSTRING(27, "generalstring"),
    UNIVERSALSTRING(28, "universalstring"),
    CHARACTER_STRING(29, "character string"),
    BMPSTRING(30, "bmpstring");

    private final Integer intValue;

    private final String stringValue;

    private UniversalTagNumber(Integer intValue, String stringValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
    }

    public Integer getIntValue() {
        return this.intValue;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public static UniversalTagNumber fromIntValue(int intValue) {
        for (UniversalTagNumber currentTagClass : UniversalTagNumber.values()) {
            if (currentTagClass.getIntValue() == intValue) {
                return currentTagClass;
            }
        }
        return null;
    }

    public static UniversalTagNumber fromStringValue(String stringValue) {
        for (UniversalTagNumber currentTagClass : UniversalTagNumber.values()) {
            if (currentTagClass.getStringValue().equalsIgnoreCase(stringValue)) {
                return currentTagClass;
            }
        }
        return null;
    }

    public static UniversalTagNumber fromIdentifierByte(final byte identifierByte) {
        return fromIntValue(identifierByte & 0b00011111);
    }
}

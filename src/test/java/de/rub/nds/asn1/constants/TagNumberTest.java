/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TagNumberTest {

    /** Test of values method, of class TagNumber. */
    @Test
    public void testValues() {
        assertEquals(29, TagNumber.values().length);
    }

    /** Test of valueOf method, of class TagNumber. */
    @Test
    public void testValueOf() {
        assertEquals(TagNumber.END_OF_CONTENT, TagNumber.valueOf("END_OF_CONTENT"));
        assertEquals(TagNumber.BOOLEAN, TagNumber.valueOf("BOOLEAN"));
        assertEquals(TagNumber.INTEGER, TagNumber.valueOf("INTEGER"));
        assertEquals(TagNumber.BIT_STRING, TagNumber.valueOf("BIT_STRING"));
        assertEquals(TagNumber.OCTET_STRING, TagNumber.valueOf("OCTET_STRING"));
        assertEquals(TagNumber.NULL, TagNumber.valueOf("NULL"));
        assertEquals(TagNumber.OBJECT_IDENTIFIER, TagNumber.valueOf("OBJECT_IDENTIFIER"));
        assertEquals(TagNumber.OBJECT_DESCRIPTOR, TagNumber.valueOf("OBJECT_DESCRIPTOR"));
        assertEquals(TagNumber.EXTERNAL, TagNumber.valueOf("EXTERNAL"));
        assertEquals(TagNumber.REAL, TagNumber.valueOf("REAL"));
        assertEquals(TagNumber.ENUMERATED, TagNumber.valueOf("ENUMERATED"));
        assertEquals(TagNumber.EMBEDDED_PDV, TagNumber.valueOf("EMBEDDED_PDV"));
        assertEquals(TagNumber.UTF8STRING, TagNumber.valueOf("UTF8STRING"));
        assertEquals(TagNumber.RELATIVE_OID, TagNumber.valueOf("RELATIVE_OID"));
        assertEquals(TagNumber.SEQUENCE, TagNumber.valueOf("SEQUENCE"));
        assertEquals(TagNumber.SET, TagNumber.valueOf("SET"));
        assertEquals(TagNumber.NUMERICSTRING, TagNumber.valueOf("NUMERICSTRING"));
        assertEquals(TagNumber.PRINTABLESTRING, TagNumber.valueOf("PRINTABLESTRING"));
        assertEquals(TagNumber.T61STRING, TagNumber.valueOf("T61STRING"));
        assertEquals(TagNumber.VIDEOTEXSTRING, TagNumber.valueOf("VIDEOTEXSTRING"));
        assertEquals(TagNumber.IA5STRING, TagNumber.valueOf("IA5STRING"));
        assertEquals(TagNumber.UTCTIME, TagNumber.valueOf("UTCTIME"));
        assertEquals(TagNumber.GENERALIZEDTIME, TagNumber.valueOf("GENERALIZEDTIME"));
        assertEquals(TagNumber.GRAPHICSTRING, TagNumber.valueOf("GRAPHICSTRING"));
        assertEquals(TagNumber.VISIBLESTRING, TagNumber.valueOf("VISIBLESTRING"));
        assertEquals(TagNumber.GENERALSTRING, TagNumber.valueOf("GENERALSTRING"));
        assertEquals(TagNumber.UNIVERSALSTRING, TagNumber.valueOf("UNIVERSALSTRING"));
        assertEquals(TagNumber.CHARACTER_STRING, TagNumber.valueOf("CHARACTER_STRING"));
        assertEquals(TagNumber.BMPSTRING, TagNumber.valueOf("BMPSTRING"));
    }

    /** Test of getIntValue method, of class TagNumber. */
    @Test
    public void testGetIntValue() {
        assertEquals(0, TagNumber.END_OF_CONTENT.getIntValue());
        assertEquals(1, TagNumber.BOOLEAN.getIntValue());
        assertEquals(2, TagNumber.INTEGER.getIntValue());
        assertEquals(3, TagNumber.BIT_STRING.getIntValue());
        assertEquals(4, TagNumber.OCTET_STRING.getIntValue());
        assertEquals(5, TagNumber.NULL.getIntValue());
        assertEquals(6, TagNumber.OBJECT_IDENTIFIER.getIntValue());
        assertEquals(7, TagNumber.OBJECT_DESCRIPTOR.getIntValue());
        assertEquals(8, TagNumber.EXTERNAL.getIntValue());
        assertEquals(9, TagNumber.REAL.getIntValue());
        assertEquals(10, TagNumber.ENUMERATED.getIntValue());
        assertEquals(11, TagNumber.EMBEDDED_PDV.getIntValue());
        assertEquals(12, TagNumber.UTF8STRING.getIntValue());
        assertEquals(13, TagNumber.RELATIVE_OID.getIntValue());
        assertEquals(16, TagNumber.SEQUENCE.getIntValue());
        assertEquals(17, TagNumber.SET.getIntValue());
        assertEquals(18, TagNumber.NUMERICSTRING.getIntValue());
        assertEquals(19, TagNumber.PRINTABLESTRING.getIntValue());
        assertEquals(20, TagNumber.T61STRING.getIntValue());
        assertEquals(21, TagNumber.VIDEOTEXSTRING.getIntValue());
        assertEquals(22, TagNumber.IA5STRING.getIntValue());
        assertEquals(23, TagNumber.UTCTIME.getIntValue());
        assertEquals(24, TagNumber.GENERALIZEDTIME.getIntValue());
        assertEquals(25, TagNumber.GRAPHICSTRING.getIntValue());
        assertEquals(26, TagNumber.VISIBLESTRING.getIntValue());
        assertEquals(27, TagNumber.GENERALSTRING.getIntValue());
        assertEquals(28, TagNumber.UNIVERSALSTRING.getIntValue());
        assertEquals(29, TagNumber.CHARACTER_STRING.getIntValue());
        assertEquals(30, TagNumber.BMPSTRING.getIntValue());
    }

    /** Test of getStringValue method, of class TagNumber. */
    @Test
    public void testGetStringValue() {
        assertEquals("end-of-content", TagNumber.END_OF_CONTENT.getStringValue());
        assertEquals("boolean", TagNumber.BOOLEAN.getStringValue());
        assertEquals("integer", TagNumber.INTEGER.getStringValue());
        assertEquals("bit string", TagNumber.BIT_STRING.getStringValue());
        assertEquals("octet string", TagNumber.OCTET_STRING.getStringValue());
        assertEquals("null", TagNumber.NULL.getStringValue());
        assertEquals("object identifier", TagNumber.OBJECT_IDENTIFIER.getStringValue());
        assertEquals("object descriptor", TagNumber.OBJECT_DESCRIPTOR.getStringValue());
        assertEquals("external", TagNumber.EXTERNAL.getStringValue());
        assertEquals("real", TagNumber.REAL.getStringValue());
        assertEquals("enumerated", TagNumber.ENUMERATED.getStringValue());
        assertEquals("embedded pdv", TagNumber.EMBEDDED_PDV.getStringValue());
        assertEquals("utf8string", TagNumber.UTF8STRING.getStringValue());
        assertEquals("relative-oid", TagNumber.RELATIVE_OID.getStringValue());
        assertEquals("sequence", TagNumber.SEQUENCE.getStringValue());
        assertEquals("set", TagNumber.SET.getStringValue());
        assertEquals("numericstring", TagNumber.NUMERICSTRING.getStringValue());
        assertEquals("printablestring", TagNumber.PRINTABLESTRING.getStringValue());
        assertEquals("t61string", TagNumber.T61STRING.getStringValue());
        assertEquals("videotexstring", TagNumber.VIDEOTEXSTRING.getStringValue());
        assertEquals("ia5string", TagNumber.IA5STRING.getStringValue());
        assertEquals("utctime", TagNumber.UTCTIME.getStringValue());
        assertEquals("generalizedtime", TagNumber.GENERALIZEDTIME.getStringValue());
        assertEquals("graphicstring", TagNumber.GRAPHICSTRING.getStringValue());
        assertEquals("visiblestring", TagNumber.VISIBLESTRING.getStringValue());
        assertEquals("generalstring", TagNumber.GENERALSTRING.getStringValue());
        assertEquals("universalstring", TagNumber.UNIVERSALSTRING.getStringValue());
        assertEquals("character string", TagNumber.CHARACTER_STRING.getStringValue());
        assertEquals("bmpstring", TagNumber.BMPSTRING.getStringValue());
    }

    /** Test of fromIntValue method, of class TagNumber. */
    @Test
    public void testFromIntValue() {
        assertEquals(TagNumber.END_OF_CONTENT, TagNumber.fromIntValue(0));
        assertEquals(TagNumber.BOOLEAN, TagNumber.fromIntValue(1));
        assertEquals(TagNumber.INTEGER, TagNumber.fromIntValue(2));
        assertEquals(TagNumber.BIT_STRING, TagNumber.fromIntValue(3));
        assertEquals(TagNumber.OCTET_STRING, TagNumber.fromIntValue(4));
        assertEquals(TagNumber.NULL, TagNumber.fromIntValue(5));
        assertEquals(TagNumber.OBJECT_IDENTIFIER, TagNumber.fromIntValue(6));
        assertEquals(TagNumber.OBJECT_DESCRIPTOR, TagNumber.fromIntValue(7));
        assertEquals(TagNumber.EXTERNAL, TagNumber.fromIntValue(8));
        assertEquals(TagNumber.REAL, TagNumber.fromIntValue(9));
        assertEquals(TagNumber.ENUMERATED, TagNumber.fromIntValue(10));
        assertEquals(TagNumber.EMBEDDED_PDV, TagNumber.fromIntValue(11));
        assertEquals(TagNumber.UTF8STRING, TagNumber.fromIntValue(12));
        assertEquals(TagNumber.RELATIVE_OID, TagNumber.fromIntValue(13));
        assertEquals(TagNumber.SEQUENCE, TagNumber.fromIntValue(16));
        assertEquals(TagNumber.SET, TagNumber.fromIntValue(17));
        assertEquals(TagNumber.NUMERICSTRING, TagNumber.fromIntValue(18));
        assertEquals(TagNumber.PRINTABLESTRING, TagNumber.fromIntValue(19));
        assertEquals(TagNumber.T61STRING, TagNumber.fromIntValue(20));
        assertEquals(TagNumber.VIDEOTEXSTRING, TagNumber.fromIntValue(21));
        assertEquals(TagNumber.IA5STRING, TagNumber.fromIntValue(22));
        assertEquals(TagNumber.UTCTIME, TagNumber.fromIntValue(23));
        assertEquals(TagNumber.GENERALIZEDTIME, TagNumber.fromIntValue(24));
        assertEquals(TagNumber.GRAPHICSTRING, TagNumber.fromIntValue(25));
        assertEquals(TagNumber.VISIBLESTRING, TagNumber.fromIntValue(26));
        assertEquals(TagNumber.GENERALSTRING, TagNumber.fromIntValue(27));
        assertEquals(TagNumber.UNIVERSALSTRING, TagNumber.fromIntValue(28));
        assertEquals(TagNumber.CHARACTER_STRING, TagNumber.fromIntValue(29));
        assertEquals(TagNumber.BMPSTRING, TagNumber.fromIntValue(30));
    }

    /** Test of fromStringValue method, of class TagNumber. */
    @Test
    public void testFromStringValue() {
        assertEquals(TagNumber.END_OF_CONTENT, TagNumber.fromStringValue("end-of-content"));
        assertEquals(TagNumber.BOOLEAN, TagNumber.fromStringValue("boolean"));
        assertEquals(TagNumber.INTEGER, TagNumber.fromStringValue("integer"));
        assertEquals(TagNumber.BIT_STRING, TagNumber.fromStringValue("bit string"));
        assertEquals(TagNumber.OCTET_STRING, TagNumber.fromStringValue("octet string"));
        assertEquals(TagNumber.NULL, TagNumber.fromStringValue("null"));
        assertEquals(TagNumber.OBJECT_IDENTIFIER, TagNumber.fromStringValue("object identifier"));
        assertEquals(TagNumber.OBJECT_DESCRIPTOR, TagNumber.fromStringValue("object descriptor"));
        assertEquals(TagNumber.EXTERNAL, TagNumber.fromStringValue("external"));
        assertEquals(TagNumber.REAL, TagNumber.fromStringValue("real"));
        assertEquals(TagNumber.ENUMERATED, TagNumber.fromStringValue("enumerated"));
        assertEquals(TagNumber.EMBEDDED_PDV, TagNumber.fromStringValue("embedded pdv"));
        assertEquals(TagNumber.UTF8STRING, TagNumber.fromStringValue("utf8string"));
        assertEquals(TagNumber.RELATIVE_OID, TagNumber.fromStringValue("relative-oid"));
        assertEquals(TagNumber.SEQUENCE, TagNumber.fromStringValue("sequence"));
        assertEquals(TagNumber.SET, TagNumber.fromStringValue("set"));
        assertEquals(TagNumber.NUMERICSTRING, TagNumber.fromStringValue("numericstring"));
        assertEquals(TagNumber.PRINTABLESTRING, TagNumber.fromStringValue("printablestring"));
        assertEquals(TagNumber.T61STRING, TagNumber.fromStringValue("t61string"));
        assertEquals(TagNumber.VIDEOTEXSTRING, TagNumber.fromStringValue("videotexstring"));
        assertEquals(TagNumber.IA5STRING, TagNumber.fromStringValue("ia5string"));
        assertEquals(TagNumber.UTCTIME, TagNumber.fromStringValue("utctime"));
        assertEquals(TagNumber.GENERALIZEDTIME, TagNumber.fromStringValue("generalizedtime"));
        assertEquals(TagNumber.GRAPHICSTRING, TagNumber.fromStringValue("graphicstring"));
        assertEquals(TagNumber.VISIBLESTRING, TagNumber.fromStringValue("visiblestring"));
        assertEquals(TagNumber.GENERALSTRING, TagNumber.fromStringValue("generalstring"));
        assertEquals(TagNumber.UNIVERSALSTRING, TagNumber.fromStringValue("universalstring"));
        assertEquals(TagNumber.CHARACTER_STRING, TagNumber.fromStringValue("character string"));
        assertEquals(TagNumber.BMPSTRING, TagNumber.fromStringValue("bmpstring"));
    }
}

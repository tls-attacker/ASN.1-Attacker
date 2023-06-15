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
        assertEquals(29, UniversalTagNumber.values().length);
    }

    /** Test of valueOf method, of class TagNumber. */
    @Test
    public void testValueOf() {
        assertEquals(
                UniversalTagNumber.END_OF_CONTENT, UniversalTagNumber.valueOf("END_OF_CONTENT"));
        assertEquals(UniversalTagNumber.BOOLEAN, UniversalTagNumber.valueOf("BOOLEAN"));
        assertEquals(UniversalTagNumber.INTEGER, UniversalTagNumber.valueOf("INTEGER"));
        assertEquals(UniversalTagNumber.BIT_STRING, UniversalTagNumber.valueOf("BIT_STRING"));
        assertEquals(UniversalTagNumber.OCTET_STRING, UniversalTagNumber.valueOf("OCTET_STRING"));
        assertEquals(UniversalTagNumber.NULL, UniversalTagNumber.valueOf("NULL"));
        assertEquals(
                UniversalTagNumber.OBJECT_IDENTIFIER,
                UniversalTagNumber.valueOf("OBJECT_IDENTIFIER"));
        assertEquals(
                UniversalTagNumber.OBJECT_DESCRIPTOR,
                UniversalTagNumber.valueOf("OBJECT_DESCRIPTOR"));
        assertEquals(UniversalTagNumber.EXTERNAL, UniversalTagNumber.valueOf("EXTERNAL"));
        assertEquals(UniversalTagNumber.REAL, UniversalTagNumber.valueOf("REAL"));
        assertEquals(UniversalTagNumber.ENUMERATED, UniversalTagNumber.valueOf("ENUMERATED"));
        assertEquals(UniversalTagNumber.EMBEDDED_PDV, UniversalTagNumber.valueOf("EMBEDDED_PDV"));
        assertEquals(UniversalTagNumber.UTF8STRING, UniversalTagNumber.valueOf("UTF8STRING"));
        assertEquals(UniversalTagNumber.RELATIVE_OID, UniversalTagNumber.valueOf("RELATIVE_OID"));
        assertEquals(UniversalTagNumber.SEQUENCE, UniversalTagNumber.valueOf("SEQUENCE"));
        assertEquals(UniversalTagNumber.SET, UniversalTagNumber.valueOf("SET"));
        assertEquals(UniversalTagNumber.NUMERICSTRING, UniversalTagNumber.valueOf("NUMERICSTRING"));
        assertEquals(
                UniversalTagNumber.PRINTABLESTRING, UniversalTagNumber.valueOf("PRINTABLESTRING"));
        assertEquals(UniversalTagNumber.T61STRING, UniversalTagNumber.valueOf("T61STRING"));
        assertEquals(
                UniversalTagNumber.VIDEOTEXSTRING, UniversalTagNumber.valueOf("VIDEOTEXSTRING"));
        assertEquals(UniversalTagNumber.IA5STRING, UniversalTagNumber.valueOf("IA5STRING"));
        assertEquals(UniversalTagNumber.UTCTIME, UniversalTagNumber.valueOf("UTCTIME"));
        assertEquals(
                UniversalTagNumber.GENERALIZEDTIME, UniversalTagNumber.valueOf("GENERALIZEDTIME"));
        assertEquals(UniversalTagNumber.GRAPHICSTRING, UniversalTagNumber.valueOf("GRAPHICSTRING"));
        assertEquals(UniversalTagNumber.VISIBLESTRING, UniversalTagNumber.valueOf("VISIBLESTRING"));
        assertEquals(UniversalTagNumber.GENERALSTRING, UniversalTagNumber.valueOf("GENERALSTRING"));
        assertEquals(
                UniversalTagNumber.UNIVERSALSTRING, UniversalTagNumber.valueOf("UNIVERSALSTRING"));
        assertEquals(
                UniversalTagNumber.CHARACTER_STRING,
                UniversalTagNumber.valueOf("CHARACTER_STRING"));
        assertEquals(UniversalTagNumber.BMPSTRING, UniversalTagNumber.valueOf("BMPSTRING"));
    }

    /** Test of getIntValue method, of class TagNumber. */
    @Test
    public void testGetIntValue() {
        assertEquals(0, UniversalTagNumber.END_OF_CONTENT.getIntValue());
        assertEquals(1, UniversalTagNumber.BOOLEAN.getIntValue());
        assertEquals(2, UniversalTagNumber.INTEGER.getIntValue());
        assertEquals(3, UniversalTagNumber.BIT_STRING.getIntValue());
        assertEquals(4, UniversalTagNumber.OCTET_STRING.getIntValue());
        assertEquals(5, UniversalTagNumber.NULL.getIntValue());
        assertEquals(6, UniversalTagNumber.OBJECT_IDENTIFIER.getIntValue());
        assertEquals(7, UniversalTagNumber.OBJECT_DESCRIPTOR.getIntValue());
        assertEquals(8, UniversalTagNumber.EXTERNAL.getIntValue());
        assertEquals(9, UniversalTagNumber.REAL.getIntValue());
        assertEquals(10, UniversalTagNumber.ENUMERATED.getIntValue());
        assertEquals(11, UniversalTagNumber.EMBEDDED_PDV.getIntValue());
        assertEquals(12, UniversalTagNumber.UTF8STRING.getIntValue());
        assertEquals(13, UniversalTagNumber.RELATIVE_OID.getIntValue());
        assertEquals(16, UniversalTagNumber.SEQUENCE.getIntValue());
        assertEquals(17, UniversalTagNumber.SET.getIntValue());
        assertEquals(18, UniversalTagNumber.NUMERICSTRING.getIntValue());
        assertEquals(19, UniversalTagNumber.PRINTABLESTRING.getIntValue());
        assertEquals(20, UniversalTagNumber.T61STRING.getIntValue());
        assertEquals(21, UniversalTagNumber.VIDEOTEXSTRING.getIntValue());
        assertEquals(22, UniversalTagNumber.IA5STRING.getIntValue());
        assertEquals(23, UniversalTagNumber.UTCTIME.getIntValue());
        assertEquals(24, UniversalTagNumber.GENERALIZEDTIME.getIntValue());
        assertEquals(25, UniversalTagNumber.GRAPHICSTRING.getIntValue());
        assertEquals(26, UniversalTagNumber.VISIBLESTRING.getIntValue());
        assertEquals(27, UniversalTagNumber.GENERALSTRING.getIntValue());
        assertEquals(28, UniversalTagNumber.UNIVERSALSTRING.getIntValue());
        assertEquals(29, UniversalTagNumber.CHARACTER_STRING.getIntValue());
        assertEquals(30, UniversalTagNumber.BMPSTRING.getIntValue());
    }

    /** Test of getStringValue method, of class TagNumber. */
    @Test
    public void testGetStringValue() {
        assertEquals("end-of-content", UniversalTagNumber.END_OF_CONTENT.getStringValue());
        assertEquals("boolean", UniversalTagNumber.BOOLEAN.getStringValue());
        assertEquals("integer", UniversalTagNumber.INTEGER.getStringValue());
        assertEquals("bit string", UniversalTagNumber.BIT_STRING.getStringValue());
        assertEquals("octet string", UniversalTagNumber.OCTET_STRING.getStringValue());
        assertEquals("null", UniversalTagNumber.NULL.getStringValue());
        assertEquals("object identifier", UniversalTagNumber.OBJECT_IDENTIFIER.getStringValue());
        assertEquals("object descriptor", UniversalTagNumber.OBJECT_DESCRIPTOR.getStringValue());
        assertEquals("external", UniversalTagNumber.EXTERNAL.getStringValue());
        assertEquals("real", UniversalTagNumber.REAL.getStringValue());
        assertEquals("enumerated", UniversalTagNumber.ENUMERATED.getStringValue());
        assertEquals("embedded pdv", UniversalTagNumber.EMBEDDED_PDV.getStringValue());
        assertEquals("utf8string", UniversalTagNumber.UTF8STRING.getStringValue());
        assertEquals("relative-oid", UniversalTagNumber.RELATIVE_OID.getStringValue());
        assertEquals("sequence", UniversalTagNumber.SEQUENCE.getStringValue());
        assertEquals("set", UniversalTagNumber.SET.getStringValue());
        assertEquals("numericstring", UniversalTagNumber.NUMERICSTRING.getStringValue());
        assertEquals("printablestring", UniversalTagNumber.PRINTABLESTRING.getStringValue());
        assertEquals("t61string", UniversalTagNumber.T61STRING.getStringValue());
        assertEquals("videotexstring", UniversalTagNumber.VIDEOTEXSTRING.getStringValue());
        assertEquals("ia5string", UniversalTagNumber.IA5STRING.getStringValue());
        assertEquals("utctime", UniversalTagNumber.UTCTIME.getStringValue());
        assertEquals("generalizedtime", UniversalTagNumber.GENERALIZEDTIME.getStringValue());
        assertEquals("graphicstring", UniversalTagNumber.GRAPHICSTRING.getStringValue());
        assertEquals("visiblestring", UniversalTagNumber.VISIBLESTRING.getStringValue());
        assertEquals("generalstring", UniversalTagNumber.GENERALSTRING.getStringValue());
        assertEquals("universalstring", UniversalTagNumber.UNIVERSALSTRING.getStringValue());
        assertEquals("character string", UniversalTagNumber.CHARACTER_STRING.getStringValue());
        assertEquals("bmpstring", UniversalTagNumber.BMPSTRING.getStringValue());
    }

    /** Test of fromIntValue method, of class TagNumber. */
    @Test
    public void testFromIntValue() {
        assertEquals(UniversalTagNumber.END_OF_CONTENT, UniversalTagNumber.fromIntValue(0));
        assertEquals(UniversalTagNumber.BOOLEAN, UniversalTagNumber.fromIntValue(1));
        assertEquals(UniversalTagNumber.INTEGER, UniversalTagNumber.fromIntValue(2));
        assertEquals(UniversalTagNumber.BIT_STRING, UniversalTagNumber.fromIntValue(3));
        assertEquals(UniversalTagNumber.OCTET_STRING, UniversalTagNumber.fromIntValue(4));
        assertEquals(UniversalTagNumber.NULL, UniversalTagNumber.fromIntValue(5));
        assertEquals(UniversalTagNumber.OBJECT_IDENTIFIER, UniversalTagNumber.fromIntValue(6));
        assertEquals(UniversalTagNumber.OBJECT_DESCRIPTOR, UniversalTagNumber.fromIntValue(7));
        assertEquals(UniversalTagNumber.EXTERNAL, UniversalTagNumber.fromIntValue(8));
        assertEquals(UniversalTagNumber.REAL, UniversalTagNumber.fromIntValue(9));
        assertEquals(UniversalTagNumber.ENUMERATED, UniversalTagNumber.fromIntValue(10));
        assertEquals(UniversalTagNumber.EMBEDDED_PDV, UniversalTagNumber.fromIntValue(11));
        assertEquals(UniversalTagNumber.UTF8STRING, UniversalTagNumber.fromIntValue(12));
        assertEquals(UniversalTagNumber.RELATIVE_OID, UniversalTagNumber.fromIntValue(13));
        assertEquals(UniversalTagNumber.SEQUENCE, UniversalTagNumber.fromIntValue(16));
        assertEquals(UniversalTagNumber.SET, UniversalTagNumber.fromIntValue(17));
        assertEquals(UniversalTagNumber.NUMERICSTRING, UniversalTagNumber.fromIntValue(18));
        assertEquals(UniversalTagNumber.PRINTABLESTRING, UniversalTagNumber.fromIntValue(19));
        assertEquals(UniversalTagNumber.T61STRING, UniversalTagNumber.fromIntValue(20));
        assertEquals(UniversalTagNumber.VIDEOTEXSTRING, UniversalTagNumber.fromIntValue(21));
        assertEquals(UniversalTagNumber.IA5STRING, UniversalTagNumber.fromIntValue(22));
        assertEquals(UniversalTagNumber.UTCTIME, UniversalTagNumber.fromIntValue(23));
        assertEquals(UniversalTagNumber.GENERALIZEDTIME, UniversalTagNumber.fromIntValue(24));
        assertEquals(UniversalTagNumber.GRAPHICSTRING, UniversalTagNumber.fromIntValue(25));
        assertEquals(UniversalTagNumber.VISIBLESTRING, UniversalTagNumber.fromIntValue(26));
        assertEquals(UniversalTagNumber.GENERALSTRING, UniversalTagNumber.fromIntValue(27));
        assertEquals(UniversalTagNumber.UNIVERSALSTRING, UniversalTagNumber.fromIntValue(28));
        assertEquals(UniversalTagNumber.CHARACTER_STRING, UniversalTagNumber.fromIntValue(29));
        assertEquals(UniversalTagNumber.BMPSTRING, UniversalTagNumber.fromIntValue(30));
        assertEquals(null, UniversalTagNumber.fromIntValue(31));
        assertEquals(null, UniversalTagNumber.fromIntValue(-1));
        assertEquals(null, UniversalTagNumber.fromIntValue(Integer.MAX_VALUE));
        assertEquals(null, UniversalTagNumber.fromIntValue(Integer.MIN_VALUE));
    }

    /** Test of fromStringValue method, of class TagNumber. */
    @Test
    public void testFromStringValue() {
        assertEquals(
                UniversalTagNumber.END_OF_CONTENT,
                UniversalTagNumber.fromStringValue("end-of-content"));
        assertEquals(UniversalTagNumber.BOOLEAN, UniversalTagNumber.fromStringValue("boolean"));
        assertEquals(UniversalTagNumber.INTEGER, UniversalTagNumber.fromStringValue("integer"));
        assertEquals(
                UniversalTagNumber.BIT_STRING, UniversalTagNumber.fromStringValue("bit string"));
        assertEquals(
                UniversalTagNumber.OCTET_STRING,
                UniversalTagNumber.fromStringValue("octet string"));
        assertEquals(UniversalTagNumber.NULL, UniversalTagNumber.fromStringValue("null"));
        assertEquals(
                UniversalTagNumber.OBJECT_IDENTIFIER,
                UniversalTagNumber.fromStringValue("object identifier"));
        assertEquals(
                UniversalTagNumber.OBJECT_DESCRIPTOR,
                UniversalTagNumber.fromStringValue("object descriptor"));
        assertEquals(UniversalTagNumber.EXTERNAL, UniversalTagNumber.fromStringValue("external"));
        assertEquals(UniversalTagNumber.REAL, UniversalTagNumber.fromStringValue("real"));
        assertEquals(
                UniversalTagNumber.ENUMERATED, UniversalTagNumber.fromStringValue("enumerated"));
        assertEquals(
                UniversalTagNumber.EMBEDDED_PDV,
                UniversalTagNumber.fromStringValue("embedded pdv"));
        assertEquals(
                UniversalTagNumber.UTF8STRING, UniversalTagNumber.fromStringValue("utf8string"));
        assertEquals(
                UniversalTagNumber.RELATIVE_OID,
                UniversalTagNumber.fromStringValue("relative-oid"));
        assertEquals(UniversalTagNumber.SEQUENCE, UniversalTagNumber.fromStringValue("sequence"));
        assertEquals(UniversalTagNumber.SET, UniversalTagNumber.fromStringValue("set"));
        assertEquals(
                UniversalTagNumber.NUMERICSTRING,
                UniversalTagNumber.fromStringValue("numericstring"));
        assertEquals(
                UniversalTagNumber.PRINTABLESTRING,
                UniversalTagNumber.fromStringValue("printablestring"));
        assertEquals(UniversalTagNumber.T61STRING, UniversalTagNumber.fromStringValue("t61string"));
        assertEquals(
                UniversalTagNumber.VIDEOTEXSTRING,
                UniversalTagNumber.fromStringValue("videotexstring"));
        assertEquals(UniversalTagNumber.IA5STRING, UniversalTagNumber.fromStringValue("ia5string"));
        assertEquals(UniversalTagNumber.UTCTIME, UniversalTagNumber.fromStringValue("utctime"));
        assertEquals(
                UniversalTagNumber.GENERALIZEDTIME,
                UniversalTagNumber.fromStringValue("generalizedtime"));
        assertEquals(
                UniversalTagNumber.GRAPHICSTRING,
                UniversalTagNumber.fromStringValue("graphicstring"));
        assertEquals(
                UniversalTagNumber.VISIBLESTRING,
                UniversalTagNumber.fromStringValue("visiblestring"));
        assertEquals(
                UniversalTagNumber.GENERALSTRING,
                UniversalTagNumber.fromStringValue("generalstring"));
        assertEquals(
                UniversalTagNumber.UNIVERSALSTRING,
                UniversalTagNumber.fromStringValue("universalstring"));
        assertEquals(
                UniversalTagNumber.CHARACTER_STRING,
                UniversalTagNumber.fromStringValue("character string"));
        assertEquals(UniversalTagNumber.BMPSTRING, UniversalTagNumber.fromStringValue("bmpstring"));
    }

    @Test
    public void testFromIdentifierByte() {
        assertEquals(
                UniversalTagNumber.END_OF_CONTENT,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00000000));
        assertEquals(
                UniversalTagNumber.BOOLEAN,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00000001));
        assertEquals(
                UniversalTagNumber.INTEGER,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00000010));
        assertEquals(
                UniversalTagNumber.BIT_STRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00000011));
        assertEquals(
                UniversalTagNumber.OCTET_STRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00000100));
        assertEquals(
                UniversalTagNumber.NULL, UniversalTagNumber.fromIdentifierByte((byte) 0b00000101));
        assertEquals(
                UniversalTagNumber.OBJECT_IDENTIFIER,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00000110));
        assertEquals(
                UniversalTagNumber.OBJECT_DESCRIPTOR,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00000111));
        assertEquals(
                UniversalTagNumber.EXTERNAL,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00001000));
        assertEquals(
                UniversalTagNumber.REAL, UniversalTagNumber.fromIdentifierByte((byte) 0b00001001));
        assertEquals(
                UniversalTagNumber.ENUMERATED,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00001010));
        assertEquals(
                UniversalTagNumber.EMBEDDED_PDV,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00001011));
        assertEquals(
                UniversalTagNumber.UTF8STRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00001100));
        assertEquals(
                UniversalTagNumber.RELATIVE_OID,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00001101));
        assertEquals(
                UniversalTagNumber.SEQUENCE,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00010000));
        assertEquals(
                UniversalTagNumber.SET, UniversalTagNumber.fromIdentifierByte((byte) 0b00010001));
        assertEquals(
                UniversalTagNumber.NUMERICSTRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00010010));
        assertEquals(
                UniversalTagNumber.PRINTABLESTRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00010011));
        assertEquals(
                UniversalTagNumber.T61STRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00010100));
        assertEquals(
                UniversalTagNumber.VIDEOTEXSTRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00010101));
        assertEquals(
                UniversalTagNumber.IA5STRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00010110));
        assertEquals(
                UniversalTagNumber.UTCTIME,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00010111));
        assertEquals(
                UniversalTagNumber.GENERALIZEDTIME,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00011000));
        assertEquals(
                UniversalTagNumber.GRAPHICSTRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00011001));
        assertEquals(
                UniversalTagNumber.VISIBLESTRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00011010));
        assertEquals(
                UniversalTagNumber.GENERALSTRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00011011));
        assertEquals(
                UniversalTagNumber.UNIVERSALSTRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00011100));
        assertEquals(
                UniversalTagNumber.CHARACTER_STRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00011101));
        assertEquals(
                UniversalTagNumber.BMPSTRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b00011110));

        assertEquals(
                UniversalTagNumber.END_OF_CONTENT,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11100000));
        assertEquals(
                UniversalTagNumber.BOOLEAN,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11100001));
        assertEquals(
                UniversalTagNumber.INTEGER,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11100010));
        assertEquals(
                UniversalTagNumber.BIT_STRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11100011));
        assertEquals(
                UniversalTagNumber.OCTET_STRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11100100));
        assertEquals(
                UniversalTagNumber.NULL, UniversalTagNumber.fromIdentifierByte((byte) 0b11100101));
        assertEquals(
                UniversalTagNumber.OBJECT_IDENTIFIER,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11100110));
        assertEquals(
                UniversalTagNumber.OBJECT_DESCRIPTOR,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11100111));
        assertEquals(
                UniversalTagNumber.EXTERNAL,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11101000));
        assertEquals(
                UniversalTagNumber.REAL, UniversalTagNumber.fromIdentifierByte((byte) 0b11101001));
        assertEquals(
                UniversalTagNumber.ENUMERATED,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11101010));
        assertEquals(
                UniversalTagNumber.EMBEDDED_PDV,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11101011));
        assertEquals(
                UniversalTagNumber.UTF8STRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11101100));
        assertEquals(
                UniversalTagNumber.RELATIVE_OID,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11101101));
        assertEquals(
                UniversalTagNumber.SEQUENCE,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11110000));
        assertEquals(
                UniversalTagNumber.SET, UniversalTagNumber.fromIdentifierByte((byte) 0b11110001));
        assertEquals(
                UniversalTagNumber.NUMERICSTRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11110010));
        assertEquals(
                UniversalTagNumber.PRINTABLESTRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11110011));
        assertEquals(
                UniversalTagNumber.T61STRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11110100));
        assertEquals(
                UniversalTagNumber.VIDEOTEXSTRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11110101));
        assertEquals(
                UniversalTagNumber.IA5STRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11110110));
        assertEquals(
                UniversalTagNumber.UTCTIME,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11110111));
        assertEquals(
                UniversalTagNumber.GENERALIZEDTIME,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11111000));
        assertEquals(
                UniversalTagNumber.GRAPHICSTRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11111001));
        assertEquals(
                UniversalTagNumber.VISIBLESTRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11111010));
        assertEquals(
                UniversalTagNumber.GENERALSTRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11111011));
        assertEquals(
                UniversalTagNumber.UNIVERSALSTRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11111100));
        assertEquals(
                UniversalTagNumber.CHARACTER_STRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11111101));
        assertEquals(
                UniversalTagNumber.BMPSTRING,
                UniversalTagNumber.fromIdentifierByte((byte) 0b11111110));
    }
}

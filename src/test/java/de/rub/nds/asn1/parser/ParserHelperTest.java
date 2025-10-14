/*
 * ASN.1-Attacker - A Library for Arbitrary ASN.1 Structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.UniversalTagNumber;
import de.rub.nds.asn1.model.Asn1BitString;
import de.rub.nds.modifiablevariable.util.DataConverter;
import de.rub.nds.protocol.exception.ParserException;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParserHelperTest {

    @Test
    void testShiftRightUnsigned() {
        assertArrayEquals(
                DataConverter.hexStringToByteArray("0000AA"),
                ParserHelper.shiftRightUnsigned(DataConverter.hexStringToByteArray("AABBCC"), 16));
        assertArrayEquals(
                DataConverter.hexStringToByteArray("7FFFFF"),
                ParserHelper.shiftRightUnsigned(DataConverter.hexStringToByteArray("FFFFFF"), 1));
        assertArrayEquals(
                DataConverter.hexStringToByteArray("0000FF"),
                ParserHelper.shiftRightUnsigned(DataConverter.hexStringToByteArray("00FFFF"), 8));
        assertArrayEquals(
                DataConverter.hexStringToByteArray("00FFFF"),
                ParserHelper.shiftRightUnsigned(DataConverter.hexStringToByteArray("00FFFF"), 0));
        assertArrayEquals(
                DataConverter.hexStringToByteArray("FFFFFF"),
                ParserHelper.shiftRightUnsigned(DataConverter.hexStringToByteArray("FFFFFF"), 0));
        assertArrayEquals(
                DataConverter.hexStringToByteArray(""),
                ParserHelper.shiftRightUnsigned(DataConverter.hexStringToByteArray(""), 256));
        assertArrayEquals(
                DataConverter.hexStringToByteArray("000000"),
                ParserHelper.shiftRightUnsigned(DataConverter.hexStringToByteArray("FFFFFF"), 256));
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    // Indefinite Length
                    ParserHelper.shiftRightUnsigned(
                            DataConverter.hexStringToByteArray("FFFFFFFF"), -1);
                });
    }

    /** Test of parseTagOctets method, of class Asn1Parser. */
    @Test
    void testParseTagOctetsShortTag() throws Exception {
        BufferedInputStream inputStream =
                new BufferedInputStream(
                        new ByteArrayInputStream(DataConverter.hexStringToByteArray("3003020109")));
        byte[] tag = ParserHelper.parseTagOctets(inputStream);
        assertArrayEquals(new byte[] {0x30}, tag);
    }

    @Test
    void testParseTagOctetsLongTag() throws Exception {
        BufferedInputStream inputStream =
                new BufferedInputStream(
                        new ByteArrayInputStream(
                                DataConverter.hexStringToByteArray("5F1D8206493132333435363738")));
        byte[] tag = ParserHelper.parseTagOctets(inputStream);
        assertArrayEquals(new byte[] {0x5F, 0x1D}, tag);
    }

    /** Test of parseTagClass method, of class Asn1Parser. */
    @Test
    void testParseTagClass() {
        assertEquals(TagClass.UNIVERSAL.getIntValue(), ParserHelper.parseTagClass((byte) 0x30));
        assertEquals(TagClass.APPLICATION.getIntValue(), ParserHelper.parseTagClass((byte) 0x41));
        assertEquals(TagClass.PRIVATE.getIntValue(), ParserHelper.parseTagClass((byte) 0xC1));
        assertEquals(
                TagClass.CONTEXT_SPECIFIC.getIntValue(), ParserHelper.parseTagClass((byte) 0xA5));
    }

    /** Test of parseTagConstructed method, of class Asn1Parser. */
    @Test
    void testParseTagConstructed() {
        assertEquals(
                TagConstructed.CONSTRUCTED.getBooleanValue(),
                ParserHelper.parseTagConstructed((byte) 0x30));
        assertEquals(
                TagConstructed.PRIMITIVE.getBooleanValue(),
                ParserHelper.parseTagConstructed((byte) 0x02));
    }

    /** Test of parseTagNumber method, of class Asn1Parser. */
    @Test
    void testParseTagNumber() {
        assertEquals(10, ParserHelper.parseTagNumber(new byte[] {0x0A}));
        assertEquals(31, ParserHelper.parseTagNumber(new byte[] {0x1F}));
        assertEquals(128, ParserHelper.parseTagNumber(new byte[] {(byte) 0x81, (byte) 0x80}));
    }

    /** Test of parseLength method, of class Asn1Parser. */
    @Test
    void testParseLength() {
        assertEquals(
                new BigInteger("1"),
                ParserHelper.parseLength(DataConverter.hexStringToByteArray("01")));
        assertEquals(
                new BigInteger("2"),
                ParserHelper.parseLength(DataConverter.hexStringToByteArray("02")));
        assertEquals(
                new BigInteger("127"),
                ParserHelper.parseLength(DataConverter.hexStringToByteArray("7F")));
        assertEquals(
                new BigInteger("128"),
                ParserHelper.parseLength(DataConverter.hexStringToByteArray("8180")));
        assertEquals(
                new BigInteger("129"),
                ParserHelper.parseLength(DataConverter.hexStringToByteArray("8181")));
        assertEquals(
                new BigInteger("255"),
                ParserHelper.parseLength(DataConverter.hexStringToByteArray("81FF")));
        assertEquals(
                new BigInteger("256"),
                ParserHelper.parseLength(DataConverter.hexStringToByteArray("820100")));
        assertEquals(
                new BigInteger("513"),
                ParserHelper.parseLength(DataConverter.hexStringToByteArray("820201")));
        assertEquals(
                new BigInteger("772"),
                ParserHelper.parseLength(DataConverter.hexStringToByteArray("820304")));
        assertEquals(
                new BigInteger("1029"),
                ParserHelper.parseLength(DataConverter.hexStringToByteArray("820405")));
        assertEquals(
                new BigInteger("21575960328"),
                ParserHelper.parseLength(DataConverter.hexStringToByteArray("850506070708")));
        assertEquals(
                new BigInteger("6627269347851"),
                ParserHelper.parseLength(DataConverter.hexStringToByteArray("86060708090A0B")));
    }

    @Test
    void testParseLengthExceptions() {
        assertThrows(
                ParserException.class,
                () -> {
                    // Indefinite Length
                    ParserHelper.parseLength(DataConverter.hexStringToByteArray("80"));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Reserved Value
                    ParserHelper.parseLength(DataConverter.hexStringToByteArray("FF"));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Too short
                    ParserHelper.parseLength(DataConverter.hexStringToByteArray("81"));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Too short
                    ParserHelper.parseLength(DataConverter.hexStringToByteArray("8201"));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Too long
                    ParserHelper.parseLength(DataConverter.hexStringToByteArray("810101"));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Too long
                    ParserHelper.parseLength(DataConverter.hexStringToByteArray("82010101"));
                });
    }

    /** Test of parseLengthOctets method, of class Asn1Parser. */
    @Test
    void testParseLengthOctets() throws IOException {
        BufferedInputStream inputStream =
                new BufferedInputStream(
                        new ByteArrayInputStream(DataConverter.hexStringToByteArray("01FF")));
        assertArrayEquals(new byte[] {0x01}, ParserHelper.parseLengthOctets(inputStream));

        inputStream =
                new BufferedInputStream(
                        new ByteArrayInputStream(DataConverter.hexStringToByteArray("7FFF")));
        assertArrayEquals(new byte[] {0x7F}, ParserHelper.parseLengthOctets(inputStream));

        inputStream =
                new BufferedInputStream(
                        new ByteArrayInputStream(DataConverter.hexStringToByteArray("8180FF")));
        assertArrayEquals(
                DataConverter.hexStringToByteArray("8180"),
                ParserHelper.parseLengthOctets(inputStream));

        inputStream =
                new BufferedInputStream(
                        new ByteArrayInputStream(DataConverter.hexStringToByteArray("8181FF")));
        assertArrayEquals(
                DataConverter.hexStringToByteArray("8181"),
                ParserHelper.parseLengthOctets(inputStream));

        inputStream =
                new BufferedInputStream(
                        new ByteArrayInputStream(DataConverter.hexStringToByteArray("81FFFF")));
        assertArrayEquals(
                DataConverter.hexStringToByteArray("81FF"),
                ParserHelper.parseLengthOctets(inputStream));

        inputStream =
                new BufferedInputStream(
                        new ByteArrayInputStream(DataConverter.hexStringToByteArray("820100FF")));
        assertArrayEquals(
                DataConverter.hexStringToByteArray("820100"),
                ParserHelper.parseLengthOctets(inputStream));

        inputStream =
                new BufferedInputStream(
                        new ByteArrayInputStream(DataConverter.hexStringToByteArray("820201FF")));
        assertArrayEquals(
                DataConverter.hexStringToByteArray("820201"),
                ParserHelper.parseLengthOctets(inputStream));

        inputStream =
                new BufferedInputStream(
                        new ByteArrayInputStream(DataConverter.hexStringToByteArray("820304FF")));
        assertArrayEquals(
                DataConverter.hexStringToByteArray("820304"),
                ParserHelper.parseLengthOctets(inputStream));

        inputStream =
                new BufferedInputStream(
                        new ByteArrayInputStream(DataConverter.hexStringToByteArray("820405FF")));
        assertArrayEquals(
                DataConverter.hexStringToByteArray("820405"),
                ParserHelper.parseLengthOctets(inputStream));

        inputStream =
                new BufferedInputStream(
                        new ByteArrayInputStream(
                                DataConverter.hexStringToByteArray("850506070708FF")));
        assertArrayEquals(
                DataConverter.hexStringToByteArray("850506070708"),
                ParserHelper.parseLengthOctets(inputStream));

        inputStream =
                new BufferedInputStream(
                        new ByteArrayInputStream(
                                DataConverter.hexStringToByteArray("86060708090A0BFF")));
        assertArrayEquals(
                DataConverter.hexStringToByteArray("86060708090A0B"),
                ParserHelper.parseLengthOctets(inputStream));
    }

    @Test
    void testParseLengthOctetsExceptions() {
        assertThrows(
                ParserException.class,
                () -> {
                    // Indefinite Length
                    ParserHelper.parseLengthOctets(
                            new BufferedInputStream(
                                    new ByteArrayInputStream(
                                            DataConverter.hexStringToByteArray("80"))));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Reserved Value
                    ParserHelper.parseLengthOctets(
                            new BufferedInputStream(
                                    new ByteArrayInputStream(
                                            DataConverter.hexStringToByteArray("FF"))));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Too short
                    ParserHelper.parseLengthOctets(
                            new BufferedInputStream(
                                    new ByteArrayInputStream(
                                            DataConverter.hexStringToByteArray("81"))));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Too short
                    ParserHelper.parseLengthOctets(
                            new BufferedInputStream(
                                    new ByteArrayInputStream(
                                            DataConverter.hexStringToByteArray("8201"))));
                });
    }

    /** Test of parseContentOctets method, of class Asn1Parser. */
    @Test
    void testParseContentOctets() throws IOException {
        BufferedInputStream inputStream =
                new BufferedInputStream(
                        new ByteArrayInputStream(DataConverter.hexStringToByteArray("01FF")));
        byte[] parseContentOctets = ParserHelper.parseContentOctets(BigInteger.ONE, inputStream);
        assertArrayEquals(DataConverter.hexStringToByteArray("01"), parseContentOctets);

        inputStream =
                new BufferedInputStream(
                        new ByteArrayInputStream(
                                DataConverter.hexStringToByteArray("01010101010101010101FF")));
        parseContentOctets = ParserHelper.parseContentOctets(BigInteger.TEN, inputStream);
        assertArrayEquals(
                DataConverter.hexStringToByteArray("01010101010101010101"), parseContentOctets);
    }

    @Test
    void testParseIndividualContentFields() throws Exception {
        Asn1BitString asn1PrimitiveBitString = new Asn1BitString("test");
        BufferedInputStream byteArrayInputStream =
                new BufferedInputStream(
                        new ByteArrayInputStream(
                                DataConverter.hexStringToByteArray("0304066E5DC0")));
        ParserHelper.parseAsn1BitString(asn1PrimitiveBitString, byteArrayInputStream);
        Assertions.assertEquals(
                UniversalTagNumber.BIT_STRING, asn1PrimitiveBitString.getUniversalTagNumberType());
        Assertions.assertEquals(TagClass.UNIVERSAL, asn1PrimitiveBitString.getTagClassType());
        Assertions.assertEquals(
                TagConstructed.PRIMITIVE, asn1PrimitiveBitString.getTagConstructedType());
        Assertions.assertArrayEquals(
                DataConverter.hexStringToByteArray("03"),
                asn1PrimitiveBitString.getTagOctets().getValue());
        Assertions.assertArrayEquals(
                DataConverter.hexStringToByteArray("04"),
                asn1PrimitiveBitString.getLengthOctets().getValue());
        Assertions.assertArrayEquals(
                DataConverter.hexStringToByteArray("066E5DC0"),
                asn1PrimitiveBitString.getContent().getValue());
        Assertions.assertEquals((byte) 0x06, asn1PrimitiveBitString.getUnusedBits().getValue());
        Assertions.assertArrayEquals(
                DataConverter.hexStringToByteArray("01B977"),
                asn1PrimitiveBitString.getUsedBits().getValue());
        Assertions.assertEquals((byte) 0, asn1PrimitiveBitString.getPadding().getValue());
    }
}

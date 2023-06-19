/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
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
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import de.rub.nds.protocol.exception.ParserException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserHelperTest {

    /** Test of parseTagOctets method, of class Asn1Parser. */
    @Test
    public void testParseTagOctetsShortTag() throws Exception {
        InputStream inputStream =
                new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("3003020109"));
        byte[] tag = ParserHelper.parseTagOctets(inputStream);
        assertArrayEquals(new byte[] {0x30}, tag);
    }

    @Test
    public void testParseTagOctetsLongTag() throws Exception {
        InputStream inputStream =
                new ByteArrayInputStream(
                        ArrayConverter.hexStringToByteArray("5F1D8206493132333435363738"));
        byte[] tag = ParserHelper.parseTagOctets(inputStream);
        assertArrayEquals(new byte[] {0x5F, 0x1D}, tag);
    }

    /** Test of parseTagClass method, of class Asn1Parser. */
    @Test
    public void testParseTagClass() {
        assertEquals(TagClass.UNIVERSAL.getIntValue(), ParserHelper.parseTagClass((byte) 0x30));
        assertEquals(TagClass.APPLICATION.getIntValue(), ParserHelper.parseTagClass((byte) 0x41));
        assertEquals(TagClass.PRIVATE.getIntValue(), ParserHelper.parseTagClass((byte) 0xC1));
        assertEquals(
                TagClass.CONTEXT_SPECIFIC.getIntValue(), ParserHelper.parseTagClass((byte) 0xA5));
    }

    /** Test of parseTagConstructed method, of class Asn1Parser. */
    @Test
    public void testParseTagConstructed() {
        assertEquals(
                TagConstructed.CONSTRUCTED.getBooleanValue(),
                ParserHelper.parseTagConstructed((byte) 0x30));
        assertEquals(
                TagConstructed.PRIMITIVE.getBooleanValue(),
                ParserHelper.parseTagConstructed((byte) 0x02));
    }

    /** Test of parseTagNumber method, of class Asn1Parser. */
    @Test
    public void testParseTagNumber() {
        assertEquals(10, ParserHelper.parseTagNumber(new byte[] {0x0A}));
        assertEquals(31, ParserHelper.parseTagNumber(new byte[] {0x1F}));
        assertEquals(128, ParserHelper.parseTagNumber(new byte[] {(byte) 0x81, (byte) 0x80}));
    }

    /** Test of parseLength method, of class Asn1Parser. */
    @Test
    public void testParseLength() {
        assertEquals(
                new BigInteger("1"),
                ParserHelper.parseLength(ArrayConverter.hexStringToByteArray("01")));
        assertEquals(
                new BigInteger("2"),
                ParserHelper.parseLength(ArrayConverter.hexStringToByteArray("02")));
        assertEquals(
                new BigInteger("127"),
                ParserHelper.parseLength(ArrayConverter.hexStringToByteArray("7F")));
        assertEquals(
                new BigInteger("128"),
                ParserHelper.parseLength(ArrayConverter.hexStringToByteArray("8180")));
        assertEquals(
                new BigInteger("129"),
                ParserHelper.parseLength(ArrayConverter.hexStringToByteArray("8181")));
        assertEquals(
                new BigInteger("255"),
                ParserHelper.parseLength(ArrayConverter.hexStringToByteArray("81FF")));
        assertEquals(
                new BigInteger("256"),
                ParserHelper.parseLength(ArrayConverter.hexStringToByteArray("820100")));
        assertEquals(
                new BigInteger("513"),
                ParserHelper.parseLength(ArrayConverter.hexStringToByteArray("820201")));
        assertEquals(
                new BigInteger("772"),
                ParserHelper.parseLength(ArrayConverter.hexStringToByteArray("820304")));
        assertEquals(
                new BigInteger("1029"),
                ParserHelper.parseLength(ArrayConverter.hexStringToByteArray("820405")));
        assertEquals(
                new BigInteger("21575960328"),
                ParserHelper.parseLength(ArrayConverter.hexStringToByteArray("850506070708")));
        assertEquals(
                new BigInteger("6627269347851"),
                ParserHelper.parseLength(ArrayConverter.hexStringToByteArray("86060708090A0B")));
    }

    @Test
    public void testParseLengthExceptions() {
        assertThrows(
                ParserException.class,
                () -> {
                    // Indefinite Length
                    ParserHelper.parseLength(ArrayConverter.hexStringToByteArray("80"));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Reserved Value
                    ParserHelper.parseLength(ArrayConverter.hexStringToByteArray("FF"));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Too short
                    ParserHelper.parseLength(ArrayConverter.hexStringToByteArray("81"));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Too short
                    ParserHelper.parseLength(ArrayConverter.hexStringToByteArray("8201"));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Too long
                    ParserHelper.parseLength(ArrayConverter.hexStringToByteArray("810101"));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Too long
                    ParserHelper.parseLength(ArrayConverter.hexStringToByteArray("82010101"));
                });
    }

    /** Test of parseLengthOctets method, of class Asn1Parser. */
    @Test
    public void testParseLengthOctets() throws IOException {
        InputStream inputStream =
                new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("01FF"));
        assertArrayEquals(new byte[] {0x01}, ParserHelper.parseLengthOctets(inputStream));

        inputStream = new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("7FFF"));
        assertArrayEquals(new byte[] {0x7F}, ParserHelper.parseLengthOctets(inputStream));

        inputStream = new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("8180FF"));
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("8180"),
                ParserHelper.parseLengthOctets(inputStream));

        inputStream = new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("8181FF"));
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("8181"),
                ParserHelper.parseLengthOctets(inputStream));

        inputStream = new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("81FFFF"));
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("81FF"),
                ParserHelper.parseLengthOctets(inputStream));

        inputStream = new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("820100FF"));
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("820100"),
                ParserHelper.parseLengthOctets(inputStream));

        inputStream = new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("820201FF"));
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("820201"),
                ParserHelper.parseLengthOctets(inputStream));

        inputStream = new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("820304FF"));
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("820304"),
                ParserHelper.parseLengthOctets(inputStream));

        inputStream = new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("820405FF"));
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("820405"),
                ParserHelper.parseLengthOctets(inputStream));

        inputStream =
                new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("850506070708FF"));
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("850506070708"),
                ParserHelper.parseLengthOctets(inputStream));

        inputStream =
                new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("86060708090A0BFF"));
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("86060708090A0B"),
                ParserHelper.parseLengthOctets(inputStream));
    }

    @Test
    public void testParseLengthOctetsExceptions() {
        assertThrows(
                ParserException.class,
                () -> {
                    // Indefinite Length
                    ParserHelper.parseLengthOctets(
                            new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("80")));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Reserved Value
                    ParserHelper.parseLengthOctets(
                            new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("FF")));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Too short
                    ParserHelper.parseLengthOctets(
                            new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("81")));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Too short
                    ParserHelper.parseLengthOctets(
                            new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("8201")));
                });
    }

    /** Test of parseContentOctets method, of class Asn1Parser. */
    @Test
    public void testParseContentOctets() throws IOException {
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("01FF"));
        byte[] parseContentOctets = ParserHelper.parseContentOctets(BigInteger.ONE, inputStream);
        assertArrayEquals(ArrayConverter.hexStringToByteArray("01"), parseContentOctets);

        inputStream =
                new ByteArrayInputStream(
                        ArrayConverter.hexStringToByteArray("01010101010101010101FF"));
        parseContentOctets = ParserHelper.parseContentOctets(BigInteger.TEN, inputStream);
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("01010101010101010101"), parseContentOctets);
    }

    @Test
    public void testParseIndividualContentFields() throws Exception {
        Asn1BitString asn1PrimitiveBitString = new Asn1BitString("test");
        InputStream byteArrayInputStream =
                new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("0304066E5DC0"));
        ParserHelper.parseAsn1BitString(asn1PrimitiveBitString, byteArrayInputStream);
        Assertions.assertEquals(
                UniversalTagNumber.BIT_STRING, asn1PrimitiveBitString.getUniversalTagNumberType());
        Assertions.assertEquals(TagClass.UNIVERSAL, asn1PrimitiveBitString.getTagClassType());
        Assertions.assertEquals(
                TagConstructed.PRIMITIVE, asn1PrimitiveBitString.getTagConstructedType());
        Assertions.assertArrayEquals(
                ArrayConverter.hexStringToByteArray("03"),
                asn1PrimitiveBitString.getTagOctets().getValue());
        Assertions.assertArrayEquals(
                ArrayConverter.hexStringToByteArray("04"),
                asn1PrimitiveBitString.getLengthOctets().getValue());
        Assertions.assertArrayEquals(
                ArrayConverter.hexStringToByteArray("066E5DC0"),
                asn1PrimitiveBitString.getContent().getValue());
        Assertions.assertEquals((byte) 0x06, asn1PrimitiveBitString.getUnusedBits().getValue());
        Assertions.assertArrayEquals(
                ArrayConverter.hexStringToByteArray("01B977"),
                asn1PrimitiveBitString.getUsedBits().getValue());
        Assertions.assertEquals((byte) 0, asn1PrimitiveBitString.getPadding().getValue());
    }
}

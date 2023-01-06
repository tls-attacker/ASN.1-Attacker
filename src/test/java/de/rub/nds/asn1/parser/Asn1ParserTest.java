/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import static org.junit.jupiter.api.Assertions.*;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.context.EmptyChooser;
import de.rub.nds.asn1.model.Asn1Encodable;
import de.rub.nds.asn1.model.Asn1Field;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class Asn1ParserTest {

    private Asn1Parser parser;

    private Asn1Field field;

    private AbstractChooser chooser;

    @BeforeEach
    public void setUp() {
        chooser = new EmptyChooser();
        field = Mockito.mock(Asn1Field.class);
        parser = new Asn1ParserImpl(chooser, field);
    }

    /** Test of parseTagOctets method, of class Asn1Parser. */
    @Test
    public void testParseTagOctetsShortTag() throws Exception {
        InputStream inputStream =
                new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("3003020109"));
        byte[] tag = parser.parseTagOctets(inputStream);
        assertArrayEquals(new byte[] {0x30}, tag);
    }

    @Test
    public void testParseTagOctetsLongTag() throws Exception {
        InputStream inputStream =
                new ByteArrayInputStream(
                        ArrayConverter.hexStringToByteArray("5F1D8206493132333435363738"));
        byte[] tag = parser.parseTagOctets(inputStream);
        assertArrayEquals(new byte[] {0x5F, 0x1D}, tag);
    }

    /** Test of parseTagClass method, of class Asn1Parser. */
    @Test
    public void testParseTagClass() {
        assertEquals(TagClass.UNIVERSAL.getIntValue(), parser.parseTagClass((byte) 0x30));
        assertEquals(TagClass.APPLICATION.getIntValue(), parser.parseTagClass((byte) 0x41));
        assertEquals(TagClass.PRIVATE.getIntValue(), parser.parseTagClass((byte) 0xC1));
        assertEquals(TagClass.CONTEXT_SPECIFIC.getIntValue(), parser.parseTagClass((byte) 0xA5));
    }

    /** Test of parseTagConstructed method, of class Asn1Parser. */
    @Test
    public void testParseTagConstructed() {
        assertEquals(
                TagConstructed.CONSTRUCTED.getBooleanValue(),
                parser.parseTagConstructed((byte) 0x30));
        assertEquals(
                TagConstructed.PRIMITIVE.getBooleanValue(),
                parser.parseTagConstructed((byte) 0x02));
    }

    /** Test of parseTagNumber method, of class Asn1Parser. */
    @Test
    public void testParseTagNumber() {
        assertEquals(10, parser.parseTagNumber(new byte[] {0x0A}));
        assertEquals(31, parser.parseTagNumber(new byte[] {0x1F}));
        assertEquals(128, parser.parseTagNumber(new byte[] {(byte) 0x81, (byte) 0x80}));
    }

    /** Test of parseLength method, of class Asn1Parser. */
    @Test
    public void testParseLength() {
        assertEquals(
                new BigInteger("1"), parser.parseLength(ArrayConverter.hexStringToByteArray("01")));
        assertEquals(
                new BigInteger("2"), parser.parseLength(ArrayConverter.hexStringToByteArray("02")));
        assertEquals(
                new BigInteger("127"),
                parser.parseLength(ArrayConverter.hexStringToByteArray("7F")));
        assertEquals(
                new BigInteger("128"),
                parser.parseLength(ArrayConverter.hexStringToByteArray("8180")));
        assertEquals(
                new BigInteger("129"),
                parser.parseLength(ArrayConverter.hexStringToByteArray("8181")));
        assertEquals(
                new BigInteger("255"),
                parser.parseLength(ArrayConverter.hexStringToByteArray("81FF")));
        assertEquals(
                new BigInteger("256"),
                parser.parseLength(ArrayConverter.hexStringToByteArray("820100")));
        assertEquals(
                new BigInteger("513"),
                parser.parseLength(ArrayConverter.hexStringToByteArray("820201")));
        assertEquals(
                new BigInteger("772"),
                parser.parseLength(ArrayConverter.hexStringToByteArray("820304")));
        assertEquals(
                new BigInteger("1029"),
                parser.parseLength(ArrayConverter.hexStringToByteArray("820405")));
        assertEquals(
                new BigInteger("21575960328"),
                parser.parseLength(ArrayConverter.hexStringToByteArray("850506070708")));
        assertEquals(
                new BigInteger("6627269347851"),
                parser.parseLength(ArrayConverter.hexStringToByteArray("86060708090A0B")));
    }

    @Test
    public void testParseLengthExceptions() {
        assertThrows(
                ParserException.class,
                () -> {
                    // Indefinite Length
                    parser.parseLength(ArrayConverter.hexStringToByteArray("80"));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Reserved Value
                    parser.parseLength(ArrayConverter.hexStringToByteArray("FF"));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Too short
                    parser.parseLength(ArrayConverter.hexStringToByteArray("81"));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Too short
                    parser.parseLength(ArrayConverter.hexStringToByteArray("8201"));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Too long
                    parser.parseLength(ArrayConverter.hexStringToByteArray("810101"));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Too long
                    parser.parseLength(ArrayConverter.hexStringToByteArray("82010101"));
                });
    }

    /** Test of parseLengthOctets method, of class Asn1Parser. */
    @Test
    public void testParseLengthOctets() throws IOException {
        InputStream inputStream =
                new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("01FF"));
        assertArrayEquals(new byte[] {0x01}, parser.parseLengthOctets(inputStream));

        inputStream = new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("7FFF"));
        assertArrayEquals(new byte[] {0x7F}, parser.parseLengthOctets(inputStream));

        inputStream = new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("8180FF"));
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("8180"), parser.parseLengthOctets(inputStream));

        inputStream = new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("8181FF"));
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("8181"), parser.parseLengthOctets(inputStream));

        inputStream = new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("81FFFF"));
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("81FF"), parser.parseLengthOctets(inputStream));

        inputStream = new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("820100FF"));
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("820100"),
                parser.parseLengthOctets(inputStream));

        inputStream = new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("820201FF"));
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("820201"),
                parser.parseLengthOctets(inputStream));

        inputStream = new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("820304FF"));
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("820304"),
                parser.parseLengthOctets(inputStream));

        inputStream = new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("820405FF"));
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("820405"),
                parser.parseLengthOctets(inputStream));

        inputStream =
                new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("850506070708FF"));
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("850506070708"),
                parser.parseLengthOctets(inputStream));

        inputStream =
                new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("86060708090A0BFF"));
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("86060708090A0B"),
                parser.parseLengthOctets(inputStream));
    }

    @Test
    public void testParseLengthOctetsExceptions() {
        assertThrows(
                ParserException.class,
                () -> {
                    // Indefinite Length
                    parser.parseLengthOctets(
                            new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("80")));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Reserved Value
                    parser.parseLengthOctets(
                            new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("FF")));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Too short
                    parser.parseLengthOctets(
                            new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("81")));
                });
        assertThrows(
                ParserException.class,
                () -> {
                    // Too short
                    parser.parseLengthOctets(
                            new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("8201")));
                });
    }

    /** Test of parseContentOctets method, of class Asn1Parser. */
    @Test
    public void testParseContentOctets() throws IOException {
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("01FF"));
        byte[] parseContentOctets = parser.parseContentOctets(BigInteger.ONE, inputStream);
        assertArrayEquals(ArrayConverter.hexStringToByteArray("01"), parseContentOctets);

        inputStream =
                new ByteArrayInputStream(
                        ArrayConverter.hexStringToByteArray("01010101010101010101FF"));
        parseContentOctets = parser.parseContentOctets(BigInteger.TEN, inputStream);
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray("01010101010101010101"), parseContentOctets);
    }

    private class Asn1ParserImpl<Chooser extends AbstractChooser>
            extends Asn1Parser<Chooser, Asn1Encodable<Chooser>> {

        public Asn1ParserImpl(Chooser chooser, Asn1Field<Chooser> field) {
            super(chooser, field);
        }

        @Override
        public void parse(InputStream inputStream) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from
            // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void parseWithoutTag(InputStream inputStream, byte[] tagOctets) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from
            // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void parseIndividualContentFields(InputStream inputStream) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from
            // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}

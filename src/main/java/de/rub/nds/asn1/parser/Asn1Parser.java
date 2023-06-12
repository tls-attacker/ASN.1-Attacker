/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1Encodable;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Asn1Parser<Encodable extends Asn1Encodable> {

    private static final Logger LOGGER = LogManager.getLogger();

    protected final Encodable encodable;

    public Asn1Parser(Encodable field) {
        this.encodable = field;
    }

    public byte[] parseTagOctets(InputStream stream) throws IOException {
        if (stream.available() == 0) {
            throw new ParserException("Cannot read from empty stream");
        }
        int read = stream.read();
        if ((read & 0x1F) == 0x1F) {
            // Long tag

            ByteArrayOutputStream tagByteStream = new ByteArrayOutputStream();
            tagByteStream.write(read);
            do {
                if (stream.available() == 0) {
                    throw new ParserException(
                            "Incomplete tag: "
                                    + ArrayConverter.bytesToHexString(tagByteStream.toByteArray()));
                }
                read = stream.read();
                if (read == -1) {
                    throw new ParserException(
                            "Incomplete tag: "
                                    + ArrayConverter.bytesToHexString(tagByteStream.toByteArray()));
                }
                tagByteStream.write(read);
            } while ((read & 0x80) > 0);
            LOGGER.debug("Parsed (long) tag octets: {}", tagByteStream.toByteArray());
            return tagByteStream.toByteArray();
        } else {
            // Short tag
            byte[] tag = new byte[] {(byte) read};
            LOGGER.debug("Parsed short tag octets: {}", tag);
            return tag;
        }
    }

    public int parseTagClass(byte firstTagByte) {
        return (firstTagByte >> 6) & 0x03;
    }

    public boolean parseTagConstructed(byte firstTagByte) {
        return ((firstTagByte >> 5) & 0x01) != 0;
    }

    public int parseTagNumber(byte[] encodedTag) {
        if (encodedTag.length == 1) {
            return encodedTag[0] & 0x1F;
        }
        int tagNumber = 0;
        byte nextByte;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(encodedTag);
        do {
            nextByte = (byte) (inputStream.read() & 0xFF);
            tagNumber = (tagNumber << 7) | (nextByte & 0x7F);
        } while ((nextByte & 0x80) > 0 && inputStream.available() > 0);
        return tagNumber;
    }

    public BigInteger parseLength(byte[] lengthOctets) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(lengthOctets);
        BigInteger length = BigInteger.ZERO;
        byte lengthByte;
        lengthByte = (byte) (inputStream.read() & 0xFF);
        if (lengthByte == (byte) 0x80) {
            throw new ParserException("Indefinite lengths are currently not supported!");
        }
        if (lengthByte == (byte) 0xFF) {
            throw new ParserException("Reserved length value!");
        }
        if ((lengthByte & 0xFF) < 128) {
            length = BigInteger.valueOf(lengthByte & 0xFF);
        } else {
            int numberOfLengthBytes = (lengthByte & 0x7F);
            if (inputStream.available() != numberOfLengthBytes) {
                throw new ParserException("Length octets have incorrect length");
            }
            for (int i = 0; i < numberOfLengthBytes; i++) {
                length = length.shiftLeft(8);
                length = length.or(BigInteger.valueOf(inputStream.read() & 0xFF));
            }
        }
        return length;
    }

    public byte[] parseLengthOctets(InputStream inputStream) throws ParserException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte lengthByte;
        try {
            lengthByte = (byte) (inputStream.read() & 0xFF);
            outputStream.write(lengthByte & 0xFF);
        } catch (IOException ex) {
            throw new ParserException(ex);
        }
        if (lengthByte == (byte) 0x80) {
            throw new ParserException("Indefinite lengths are currently not supported!");
        }
        if (lengthByte == (byte) 0xFF) {
            throw new ParserException("Reserved length value!");
        }
        if ((lengthByte & 0xFF) < 128) {
            LOGGER.debug("Parsed (short) length octets: {}", outputStream.toByteArray());
            return outputStream.toByteArray();
        } else {
            int numberOfLengthBytes = (lengthByte & 0x7F);
            if (numberOfLengthBytes > inputStream.available()) {
                throw new ParserException("Not enough bytes for length octets in stream");
            }
            for (int i = 0; i < numberOfLengthBytes; i++) {
                try {
                    outputStream.write(inputStream.read());
                } catch (IOException ex) {
                    throw new ParserException(ex);
                }
            }
            LOGGER.debug("Parsed (long) length octets: {}", outputStream.toByteArray());
            return outputStream.toByteArray();
        }
    }

    public byte[] parseContentOctets(BigInteger length, InputStream inputStream)
            throws IOException {
        if (inputStream.available() < length.intValue()) {
            throw new ParserException("Not enough bytes in stream");
        }
        byte[] octets = inputStream.readNBytes(length.intValue());
        LOGGER.debug("Parsed content octets: {}", octets);
        return octets;
    }

    public abstract void parse(InputStream inputStream);
}

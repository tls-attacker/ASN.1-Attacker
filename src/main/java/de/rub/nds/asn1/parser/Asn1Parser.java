/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1Encodable;
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
        int read = stream.read();
        if ((read & 0x1F) == 0x1F) {
            // Long tag

            ByteArrayOutputStream tagByteStream = new ByteArrayOutputStream();
            tagByteStream.write(read);
            do {
                read = stream.read();
                if (read == -1) {
                    throw new ParserException("Incomplete tag");
                }
                tagByteStream.write(read);
            } while ((read & 0x80) > 0);
            return tagByteStream.toByteArray();
        } else {
            // Short tag
            return new byte[]{(byte) read};
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
        } while ((nextByte & 0x80) > 0);
        return tagNumber;
    }

    public BigInteger parseLength(byte[] lengthOctets) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(lengthOctets);
        BigInteger length = BigInteger.ZERO;
        byte lengthByte;
        lengthByte = (byte) (inputStream.read() & 0xFF);
        if (lengthByte == 0x80) {
            throw new ParserException("Indefinite lengths are currently not supported!");
        }
        if (lengthByte == 0xFF) {
            throw new ParserException("Reserved length value!");
        }
        if ((lengthByte & 0xFF) < 128) {
            length = BigInteger.valueOf(lengthByte & 0xFF);
        } else {
            int numberOfLengthBytes = (lengthByte & 0x7F);
            for (int i = 0; i < numberOfLengthBytes; i++) {
                length = length.shiftLeft(8);
                length = length.or(BigInteger.valueOf(inputStream.read() & 0xFF));
            }
        }
        return length;
    }

    public byte[] parseLengthOctets(InputStream inputStream) throws ParserException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte lengthByte;
        try {
            lengthByte = (byte) (inputStream.read() & 0xFF);
            outputStream.write(lengthByte & 0xFF);
        } catch (IOException ex) {
            throw new ParserException(ex);
        }
        if (lengthByte == 0x80) {
            throw new ParserException("Indefinite lengths are currently not supported!");
        }
        if (lengthByte == 0xFF) {
            throw new ParserException("Reserved length value!");
        }
        if ((lengthByte & 0xFF) < 128) {
            return outputStream.toByteArray();
        } else {
            int numberOfLengthBytes = (lengthByte & 0x7F);
            for (int i = 0; i < numberOfLengthBytes; i++) {
                try {
                    outputStream.write(inputStream.read());
                } catch (IOException ex) {
                    throw new ParserException(ex);
                }
            }
            return outputStream.toByteArray();
        }
    }

    public byte[] parseContentOctets(BigInteger length, InputStream inputStream) {
        BigInteger toReadLength = length;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while (toReadLength.compareTo(BigInteger.ZERO) > 0) {
            int bytesToRead = 65536;
            if (toReadLength.compareTo(BigInteger.valueOf(bytesToRead)) < 0) {
                bytesToRead = toReadLength.intValue();
            }
            try {
                outputStream.write(inputStream.readNBytes(bytesToRead));
            } catch (IOException ex) {
                throw new ParserException(ex);
            }
            toReadLength = toReadLength.subtract(BigInteger.valueOf(bytesToRead));
        }
        return outputStream.toByteArray();
    }

    public abstract void parse(InputStream inputStream);

    public abstract void parseIndividualContentFields(InputStream inputStream) throws IOException;

}

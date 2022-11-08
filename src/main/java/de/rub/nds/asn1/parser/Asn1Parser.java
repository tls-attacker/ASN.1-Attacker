/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1Field;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Asn1Parser<T extends Asn1Field> {

    private static final Logger LOGGER = LogManager.getLogger();

    private final BufferedInputStream inputStream;

    public Asn1Parser(InputStream inputStream) {
        this.inputStream = new BufferedInputStream(inputStream);
    }

    public abstract T parse();

    protected void genericParse(T field) {
        field.setTag(this.parseTag());
        field.setTagClass(this.parseTagClass());
        field.setTagConstructed(this.parseTagConstructed());
        field.setTagNumber(this.parseTagNumber());
        field.setLength(this.parseLength());
        field.setContent(this.parseContent(field.getLength().getValue()));
    }

    protected final byte[] peek(int length) {
        inputStream.mark(length);
        byte[] peekedBytes;
        try {
            peekedBytes = inputStream.readNBytes(length);
            inputStream.reset();
            return peekedBytes;
        } catch (IOException ex) {
            throw new ParserException(ex);
        }
    }

    protected final int peek() {
        return peek(1)[0];
    }

    protected int parseTag() {
        byte[] tagBytes = peek(2);
        if (tagBytes[0] == 0x1F) {
            return ((tagBytes[0] & 0xFF) << 8) | (tagBytes[1] & 0xFF);
        } else {
            return tagBytes[0] & 0xFF;
        }
    }

    protected int parseTagClass() {
        return ((peek()) >> 6) & 0x03;
    }

    protected boolean parseTagConstructed() {
        return ((peek() >> 5) & 0x01) != 0;
    }

    protected int parseTagNumber() {
        int tagNumber;
        try {
            tagNumber = inputStream.read() & 0x1F;
        } catch (IOException ex) {
            throw new ParserException(ex);
        }
        if (tagNumber == 0x1F) {
            tagNumber = this.parseLongTagNumber();
        }
        return tagNumber;
    }

    protected int parseLongTagNumber() {
        int tagNumber = 0;
        byte nextByte;
        do {
            try {
                nextByte = (byte) (inputStream.read() & 0xFF);
            } catch (IOException ex) {
                throw new ParserException(ex);
            }
            tagNumber = (tagNumber << 7) | (nextByte & 0x7F);
        } while ((nextByte & 0x80) > 0);
        return tagNumber;
    }

    protected BigInteger parseLength() throws ParserException {
        BigInteger length = BigInteger.ZERO;
        byte lengthByte;
        try {
            lengthByte = (byte) (inputStream.read() & 0xFF);
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
            length = BigInteger.valueOf(lengthByte & 0xFF);
        } else {
            int numberOfLengthBytes = (lengthByte & 0x7F);
            for (int i = 0; i < numberOfLengthBytes; i++) {
                try {
                    length = length.shiftLeft(8);
                    length = length.or(BigInteger.valueOf(inputStream.read() & 0xFF));
                } catch (IOException ex) {
                    throw new ParserException(ex);
                }
            }
        }
        return length;
    }

    protected byte[] parseContent(BigInteger length) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while (length.compareTo(BigInteger.ZERO) > 0) {
            int bytesToRead = 65536;
            if (length.compareTo(BigInteger.valueOf(bytesToRead)) < 0) {
                bytesToRead = length.intValue();
            }
            try {
                outputStream.write(inputStream.readNBytes(bytesToRead));
            } catch (IOException ex) {
                throw new ParserException(ex);
            }
            length = length.subtract(BigInteger.valueOf(bytesToRead));
        }
        return outputStream.toByteArray();
    }
}

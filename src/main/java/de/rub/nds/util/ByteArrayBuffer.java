/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.util;

public class ByteArrayBuffer {

    private final byte[] bytes;

    private int readPosition = 0;

    public ByteArrayBuffer(final byte[] bytes) {
        this.bytes = bytes;
    }

    public byte peekByte() throws RuntimeException {
        byte result = 0;
        if (readPosition < this.bytes.length) {
            result = this.bytes[readPosition];
        } else {
            throw new RuntimeException("Cannot peekByte: Next byte not available!");
        }
        return result;
    }

    public byte[] peekBytes(int length) throws RuntimeException {
        byte[] result = null;
        if ((readPosition + length) <= this.bytes.length) {
            result = new byte[length];
            System.arraycopy(this.bytes, this.readPosition, result, 0, length);
        } else {
            throw new RuntimeException("Cannot peekByte: Next " + length + " bytes not available!");
        }
        return result;
    }

    public byte readByte() throws RuntimeException {
        byte result = this.peekByte();
        this.readPosition++;
        return result;
    }

    public byte[] readBytes(int length) throws RuntimeException {
        byte[] result = this.peekBytes(length);
        this.readPosition += length;
        return result;
    }

    public int getNumberOfRemainingBytes() {
        int numRemainingBytes = 0;
        if (this.readPosition < this.bytes.length) {
            numRemainingBytes = this.bytes.length - this.readPosition;
        }
        return numRemainingBytes;
    }

    public byte[] getRemainingBytes() throws RuntimeException {
        byte[] result = null;
        int numRemainingBytes = this.getNumberOfRemainingBytes();
        if (numRemainingBytes > 0) {
            result = this.peekBytes(numRemainingBytes);
        } else {
            throw new RuntimeException("Cannot read remaining bytes since no bytes are left!");
        }
        return result;
    }

    public int getNumberOfReadBytes() {
        return this.readPosition;
    }
}

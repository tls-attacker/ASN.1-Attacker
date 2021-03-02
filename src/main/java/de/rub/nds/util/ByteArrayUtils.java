/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.util;

public class ByteArrayUtils {

    public static byte[] merge(final byte[] byteArray1, final byte[] byteArray2) {
        byte[] result = new byte[byteArray1.length + byteArray2.length];
        System.arraycopy(byteArray1, 0, result, 0, byteArray1.length);
        System.arraycopy(byteArray2, 0, result, byteArray1.length, byteArray2.length);
        return result;
    }

    public static byte[] merge(final byte[] byteArray1, final byte[] byteArray2, final byte[]... byteArrays) {
        byte[] result = merge(byteArray1, byteArray2);
        for (final byte[] byteArrayN : byteArrays) {
            result = merge(result, byteArrayN);
        }
        return result;
    }

    public static byte[] slice(final byte[] byteArray, int start, int length) {
        byte[] result = new byte[length];
        System.arraycopy(byteArray, start, result, 0, length);
        return result;
    }
}

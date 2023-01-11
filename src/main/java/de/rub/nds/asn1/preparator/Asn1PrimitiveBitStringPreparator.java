/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.model.Asn1PrimitiveBitString;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

public class Asn1PrimitiveBitStringPreparator<Chooser extends AbstractChooser>
        extends Asn1FieldPreparator<Chooser, Asn1PrimitiveBitString<Chooser>> {

    private final Asn1PrimitiveBitString asn1PrimitiveBitString;

    public Asn1PrimitiveBitStringPreparator(
            Chooser chooser, final Asn1PrimitiveBitString asn1PrimitiveBitString) {
        super(chooser, asn1PrimitiveBitString);
        this.asn1PrimitiveBitString = asn1PrimitiveBitString;
    }

    @Override
    protected byte[] encodeContent() {
        try {
            this.asn1PrimitiveBitString.setUnusedBits(
                    (byte) countLeadingZeros(this.asn1PrimitiveBitString.getUsedBits().getValue()));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(new byte[] {this.asn1PrimitiveBitString.getUnusedBits().getValue()});
            byte[] encodedContent =
                    Arrays.copyOf(
                            asn1PrimitiveBitString.getUsedBits().getValue(),
                            asn1PrimitiveBitString.getUsedBits().getValue().length);
            encodedContent =
                    shiftLeft(
                            encodedContent, this.asn1PrimitiveBitString.getUnusedBits().getValue());
            encodedContent[encodedContent.length - 1] &=
                    encodeMask(this.asn1PrimitiveBitString.getUnusedBits().getValue());
            encodedContent[encodedContent.length - 1] &=
                    this.asn1PrimitiveBitString.getPadding().getValue();
            outputStream.write(encodedContent);
            return outputStream.toByteArray();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private int countLeadingZeros(byte[] input) {
        int count = 0;
        int length = input.length;
        boolean foundOne = false;
        // Iterate through each byte of the array
        for (int i = 0; i < length; i++) {
            byte b = input[i];
            for (int j = 7; j >= 0; j--) {
                if (!foundOne) {
                    if ((b & (1 << j)) == 0) {
                        count++;
                    } else {
                        foundOne = true;
                    }
                } else {
                    break;
                }
            }
            if (foundOne) {
                break;
            }
        }
        return count;
    }

    private byte encodeMask(byte length) {
        int unsignedLength = length & 0xFF;
        int result = 0;
        for (int i = 0; i < unsignedLength; i++) {
            if (result == 0) {
                result = 1;
            } else {
                result <<= 1;
                result &= 1;
            }
        }
        return (byte) result;
    }

    private byte[] shiftLeft(byte[] input, int n) {
        BigInteger tempBigInt = new BigInteger(input);
        tempBigInt.shiftLeft(n);
        return tempBigInt.toByteArray();
    }
}

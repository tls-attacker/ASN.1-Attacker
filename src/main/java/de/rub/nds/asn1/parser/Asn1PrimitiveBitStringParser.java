/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.model.Asn1PrimitiveBitString;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Asn1PrimitiveBitStringParser<Chooser extends AbstractChooser>
        extends Asn1FieldParser<Chooser, Asn1PrimitiveBitString<Chooser>> {

    private static final Logger LOGGER = LogManager.getLogger();

    public Asn1PrimitiveBitStringParser(
            Chooser chooser, Asn1PrimitiveBitString asn1PrimitiveBitString) {
        super(chooser, asn1PrimitiveBitString);
    }

    @Override
    public void parseIndividualContentFields(InputStream byteArrayInputStream) throws IOException {
        encodable.setUnusedBits((byte) byteArrayInputStream.read());
        byte[] remainingBytes = byteArrayInputStream.readAllBytes();
        LOGGER.debug("Unused bits: " + encodable.getUnusedBits().getValue());
        LOGGER.debug("Remaining bytes: " + ArrayConverter.bytesToHexString(remainingBytes));

        encodable.setUsedBits(shiftRight(remainingBytes, encodable.getUnusedBits().getValue()));
        encodable.setPadding(extractBits(remainingBytes, encodable.getUnusedBits().getValue()));

        LOGGER.debug(
                "Used bits: "
                        + ArrayConverter.bytesToHexString(encodable.getUsedBits().getValue()));
        LOGGER.debug(
                "Padding: "
                        + ArrayConverter.bytesToHexString(
                                new byte[] {encodable.getPadding().getValue()}));
    }

    private byte extractBits(byte[] input, int n) {
        if (input.length == 0) {
            return 0;
        }
        if (n > 8) {
            throw new IllegalArgumentException("n must be between 0 and 8, inclusive");
        }
        int mask = (1 << n) - 1;
        return (byte) (input[input.length - 1] & mask);
    }

    private byte[] shiftRight(byte[] array, int n) {
        if (array.length == 0) {
            return array;
        }
        BigInteger bigInt = new BigInteger(array);
        BigInteger shiftInt = bigInt.shiftRight(n);
        return shiftInt.toByteArray();
    }
}

/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.model.Asn1ObjectIdentifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Asn1ObjectIdentifierPreparator extends Asn1FieldPreparator {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Asn1ObjectIdentifier asn1ObjectIdentifier;

    public Asn1ObjectIdentifierPreparator(Asn1ObjectIdentifier asn1ObjectIdentifier) {
        super(asn1ObjectIdentifier);
        this.asn1ObjectIdentifier = asn1ObjectIdentifier;
    }

    private byte[][] encodeIdentifierStrings(String[] identifierStrings) {
        byte[][] encodedIdentifiers = new byte[identifierStrings.length - 1][];
        encodedIdentifiers[0] = this.encodeFirstTwoIdentifierStrings(identifierStrings);
        for (int i = 1; i < encodedIdentifiers.length; i++) {
            int identifierValue = Integer.parseInt(identifierStrings[i + 1]);
            encodedIdentifiers[i] = this.encodeSingleIdentifier(identifierValue);
        }
        return encodedIdentifiers;
    }

    private byte[] encodeFirstTwoIdentifierStrings(String[] identifierStrings) {
        int identifier1 = Integer.parseInt(identifierStrings[0]);
        int identifier2 = Integer.parseInt(identifierStrings[1]);
        return new byte[] { (byte) (identifier1 * 40 + identifier2) };
    }

    private byte[] encodeSingleIdentifier(int identifierValue) {
        int numberOfIdentifierValueBytes = this.computeNumberOfIdentifierValueBytes(identifierValue);
        byte[] encodedIdentifier = new byte[numberOfIdentifierValueBytes];
        byte moreFlag = 0x00;
        for (int i = numberOfIdentifierValueBytes - 1; i >= 0; i--) {
            encodedIdentifier[i] = (byte) (moreFlag | (identifierValue & 0x7F));
            identifierValue = identifierValue >> 7;
            moreFlag = (byte) 0x80;
        }
        return encodedIdentifier;
    }

    private int computeNumberOfIdentifierValueBytes(int identifierValue) {
        int numberOfIdentifierValueBytes = 1;
        identifierValue = identifierValue >> 7;
        while (identifierValue > 0) {
            numberOfIdentifierValueBytes++;
            identifierValue = identifierValue >> 7;
        }
        return numberOfIdentifierValueBytes;
    }

    @Override
    protected byte[] encodeContent() {

        String fullIdentifierString = this.asn1ObjectIdentifier.getValue().getValue().trim();
        String[] identifierStrings = fullIdentifierString.split("\\.");
        if (identifierStrings.length >= 2) {
            byte[][] encodedIdentifiers = this.encodeIdentifierStrings(identifierStrings);
            int totalLength = 0;
            int contentPos = 0;
            for (byte[] encodedIdentifier : encodedIdentifiers) {
                totalLength += encodedIdentifier.length;
            }
            byte[] content = new byte[totalLength];
            for (byte[] encodedIdentifier : encodedIdentifiers) {
                for (int j = 0; j < encodedIdentifier.length; j++) {
                    content[contentPos] = encodedIdentifier[j];
                    contentPos++;
                }
            }
            return content;
        } else {

            return new byte[0];
        }
    }
}

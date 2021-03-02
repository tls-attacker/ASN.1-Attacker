/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.serializer;

import de.rub.nds.asn1.model.Asn1Field;
import de.rub.nds.util.ByteArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;

public class Asn1FieldSerializer extends Asn1RawFieldSerializer {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Asn1Field field;

    public Asn1FieldSerializer(final Asn1Field field) {
        super(field);
        this.field = field;
    }

    @Override
    public void updateLayers() {
        this.encodeField();
        super.updateLayers();
    }

    private void encodeField() {
        byte[] identifierOctets = this.encodeIdentifier();
        byte[] contentOctets = this.encodeContent();
        byte[] lengthOctets = this.encodeLength(BigInteger.valueOf(contentOctets.length));
        this.field.setIdentifierOctets(identifierOctets);
        this.field.setLengthOctets(lengthOctets);
        this.field.setContentOctets(contentOctets);
    }

    private final byte[] encodeIdentifier() {
        byte firstIdentifierByte = 0;
        firstIdentifierByte = this.encodeTagClass(firstIdentifierByte, this.field.getTagClass().getValue());
        firstIdentifierByte = this.encodeIsConstructed(firstIdentifierByte, this.field.getTagConstructed().getValue());
        return this.encodeTagNumber(firstIdentifierByte, this.field.getTagNumber().getValue());
    }

    private byte encodeTagClass(byte firstIdentifierByte, int tagClass) {
        return (byte) (firstIdentifierByte | ((tagClass & 0x3) << 6));
    }

    private byte encodeIsConstructed(byte firstIdentifierByte, boolean isConstructed) {
        return ((isConstructed == true) ? (byte) (firstIdentifierByte | 0x20) : firstIdentifierByte);
    }

    private byte[] encodeTagNumber(byte firstIdentifierByte, int tagNumber) {
        byte[] result = null;
        if (tagNumber < 0) {
            LOGGER.warn("Tag number is smaller than zero. Defaulting to zero!");
            tagNumber = 0;
        }
        if (this.field.getLongTagNumberBytes().getValue() == 0 && tagNumber <= 0x1F) {
            result = new byte[] { firstIdentifierByte };
            result[0] |= (byte) (tagNumber & 0x1F);
        } else {
            int longTagNumberBytes = this.field.getLongTagNumberBytes().getValue();
            // Quick Fix
            if (longTagNumberBytes > 65535) {
                LOGGER.warn("Fix von longTagNumberBytes: critical value: " + longTagNumberBytes);
                longTagNumberBytes = 65535;
            }
            byte[] longEncoding = this.encodeLongTagNumber(firstIdentifierByte, tagNumber);
            if (longEncoding.length < longTagNumberBytes) {
                longEncoding = ByteArrayUtils.merge(new byte[longTagNumberBytes - longEncoding.length], longEncoding);
            }
            firstIdentifierByte = (byte) (firstIdentifierByte | 0x1F);
            result = ByteArrayUtils.merge(new byte[] { firstIdentifierByte }, longEncoding);
        }
        return result;
    }

    private byte[] encodeLongTagNumber(byte firstIdentifierByte, int tagNumber) {
        int tagNumberByteCount = this.getTagNumberByteCount(tagNumber);
        byte[] result = new byte[tagNumberByteCount];
        byte moreFlag = 0x00;
        for (int i = tagNumberByteCount - 1; i >= 0; i--) {
            result[i] = (byte) (moreFlag | (tagNumber & 0x7F));
            tagNumber = tagNumber >> 7;
            moreFlag = (byte) 0x80;
        }
        return result;
    }

    private int getTagNumberByteCount(int tagNumber) {
        int result = 0;
        while (tagNumber > 0) {
            result++;
            tagNumber = tagNumber >> 7;
        }
        return result;
    }

    private final byte[] encodeLength(BigInteger contentLength) {
        byte[] result = null;
        this.field.setLength(contentLength);
        BigInteger length = this.field.getLength().getValue();
        if (length.compareTo(BigInteger.ZERO) == -1) {
            LOGGER.warn("Field length is smaller than zero. Defaulting to zero!");
            length = BigInteger.ZERO;
        }
        if (this.field.getLongLengthBytes().getValue() == 0 && length.compareTo(BigInteger.valueOf(127)) <= 0) {
            result = new byte[] { (byte) length.byteValue() };
        } else {
            result = encodeLongLength(length);
        }
        return result;
    }

    private byte[] encodeLongLength(BigInteger length) {
        byte[] result = null;
        byte[] longLength = length.toByteArray();
        int longLengthBytes = this.field.getLongLengthBytes().getValue();
        // Quick Fix
        if (longLengthBytes > 65535) {
            LOGGER.warn("Fix von longLengthBytes: critical value: " + longLengthBytes);
            longLengthBytes = 65535;
        }
        if (longLength[0] == 0x00) {
            if (longLength.length < (longLengthBytes + 1)) {
                longLength = ByteArrayUtils.merge(new byte[longLengthBytes + 1 - longLength.length], longLength);
            }
            longLength[0] = (byte) (0x80 | longLength.length - 1);
            result = longLength;
        } else {
            byte[] prefix = new byte[] { (byte) (0x80 | longLength.length) };
            if (longLength.length < (longLengthBytes)) {
                longLength = ByteArrayUtils.merge(new byte[longLengthBytes - longLength.length], longLength);
            }
            result = ByteArrayUtils.merge(prefix, longLength);
        }
        return result;
    }

    private final byte[] encodeContent() {
        return field.getContent().getValue();
    }
}

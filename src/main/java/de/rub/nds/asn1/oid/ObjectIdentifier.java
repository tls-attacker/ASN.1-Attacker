/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.oid;

import de.rub.nds.asn1.parser.ParserException;
import de.rub.nds.modifiablevariable.util.UnformattedByteArrayAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ObjectIdentifier {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LogManager.getLogger();

    @XmlJavaTypeAdapter(UnformattedByteArrayAdapter.class)
    private final byte[] encoded;

    @SuppressWarnings("unused")
    private ObjectIdentifier() {
        encoded = null;
    }

    public ObjectIdentifier(byte[] bytes) {
        encoded = bytes;
    }

    public ObjectIdentifier(String id) {
        String[] splittedId = id.split("\\.");
        long[] idValues = new long[splittedId.length];
        int i = 0;
        try {
            for (String subId : splittedId) {
                idValues[i] = Long.parseLong(subId);
                i++;
            }
        } catch (RuntimeException E) {
            throw new ParserException("Could not parse OID \'" + id + "\'", E);
        }
        this.encoded = computeEncodedValue(idValues);
    }

    public ObjectIdentifier(long[] idValues) {
        this.encoded = computeEncodedValue(idValues);
    }

    private List<Long> decodeValue(final byte[] content) {
        List<Long> resultList = new LinkedList<>();

        if (content.length > 0) {
            resultList.add((long) (content[0] / 40));
            resultList.add((long) (content[0] % 40));
            int contentPos = 1;
            while (contentPos < content.length) {
                long oidPart = 0;
                boolean partFinished;
                do {
                    oidPart = (oidPart << 7) | (content[contentPos] & 0x7F);
                    partFinished = (content[contentPos] & 0x80) == 0;
                    contentPos++;
                } while (contentPos < content.length && !partFinished);
                resultList.add(oidPart);
            }
        }
        return resultList;
    }

    private byte[] computeEncodedValue(long[] idValues) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        //
        if (idValues.length == 1) {
            stream.write((int) (idValues[0] * 40));
        } else if (idValues.length >= 2) {
            stream.write((int) (idValues[0] * 40 + idValues[1]));
            for (int i = 2; i < idValues.length; i++) {
                try {
                    stream.write(encodeSingleIdValue(idValues[i]));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        return stream.toByteArray();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        List<Long> idValues = decodeValue(encoded);
        for (long idValue : idValues) {
            if (!first) {
                builder.append(".");
            } else {
                first = false;
            }
            builder.append(idValue);
        }
        return builder.toString();
    }

    public byte[] getEncoded() {
        return Arrays.copyOf(encoded, encoded.length);
    }

    private byte[] encodeSingleIdValue(long idValue) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        byte moreFlag = 0x00;
        do {
            try {
                stream.write(new byte[] {(byte) (moreFlag | (idValue & 0x7F))});
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            idValue >>= 7;
            moreFlag = (byte) 0x80;
        } while (idValue > 0);
        // we encoded the byte array in reverse order - we have to flip it again
        return reverse(stream.toByteArray());
    }

    private byte[] reverse(byte[] array) {
        byte[] newArray = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[array.length - (i + 1)];
        }
        return newArray;
    }
}

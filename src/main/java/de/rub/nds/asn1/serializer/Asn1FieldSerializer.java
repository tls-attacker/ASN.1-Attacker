/*
 * ASN.1-Attacker - A Library for Arbitrary ASN.1 Structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.serializer;

import de.rub.nds.asn1.model.Asn1Field;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Asn1FieldSerializer {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Asn1Field field;

    public Asn1FieldSerializer(final Asn1Field field) {
        this.field = field;
    }

    public final byte[] serialize() {
        try {
            LOGGER.trace("Serializing: {} ", field.getIdentifier());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stream.writeBytes(field.getTagOctets().getValue());
            stream.writeBytes(field.getLengthOctets().getValue());
            stream.write(field.getContent().getValue());
            return stream.toByteArray();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

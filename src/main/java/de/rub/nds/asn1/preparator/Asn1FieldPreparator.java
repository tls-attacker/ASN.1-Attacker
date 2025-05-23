/*
 * ASN.1-Attacker - A Library for Arbitrary ASN.1 Structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.model.Asn1Encodable;
import de.rub.nds.asn1.model.Asn1Field;
import de.rub.nds.protocol.util.SilentByteArrayOutputStream;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Asn1FieldPreparator<Field extends Asn1Field> {

    private static final Logger LOGGER = LogManager.getLogger();

    protected final Field field;

    public Asn1FieldPreparator(final Field field) {
        this.field = field;
    }

    public final void prepare() {
        LOGGER.trace("Preparing: {}", field.getIdentifier());
        prepareContent(field);
        Asn1PreparatorHelper.prepareAfterContent(field);
    }

    public void prepareContent(Asn1Field field) {
        field.setContent(encodeContent());
    }

    protected abstract byte[] encodeContent();

    protected byte[] encodeChildren(Asn1Encodable... children) {
        SilentByteArrayOutputStream outputStream = new SilentByteArrayOutputStream();
        for (Asn1Encodable child : children) {
            LOGGER.debug("Encoding {}", child.getIdentifier());
            outputStream.write(encode(child));
        }
        return outputStream.toByteArray();
    }

    protected byte[] encodeChildren(List<Asn1Encodable> children) {
        SilentByteArrayOutputStream outputStream = new SilentByteArrayOutputStream();
        for (Asn1Encodable child : children) {
            LOGGER.debug("Encoding {}", child.getIdentifier());
            outputStream.write(encode(child));
        }
        return outputStream.toByteArray();
    }

    private byte[] encode(Asn1Encodable encodable) {
        SilentByteArrayOutputStream outputStream = new SilentByteArrayOutputStream();
        outputStream.write(encodable.getTagOctets().getValue());
        outputStream.write(encodable.getLengthOctets().getValue());
        outputStream.write(encodable.getContent().getValue());
        return outputStream.toByteArray();
    }
}

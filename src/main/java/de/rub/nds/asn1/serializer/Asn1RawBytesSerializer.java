/*
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2020 Ruhr University Bochum, Paderborn University,
 * and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.rub.nds.asn1.serializer;

import de.rub.nds.asn1.model.Asn1RawBytes;

public class Asn1RawBytesSerializer extends Asn1Serializer {

    private final Asn1RawBytes asn1RawBytes;

    public Asn1RawBytesSerializer(Asn1RawBytes asn1RawBytes) {
        this.asn1RawBytes = asn1RawBytes;
    }

    @Override
    public void updateLayers() {
        this.encodePrimitiveOctetString();
    }

    private void encodePrimitiveOctetString() {
        byte[] content = this.asn1RawBytes.getValue();
        this.asn1RawBytes.setContent(content);
    }

    @Override
    public final byte[] serialize() {
        this.updateLayers();
        return this.asn1RawBytes.getContent().getValue();

    }
}

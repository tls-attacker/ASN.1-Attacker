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

import de.rub.nds.asn1.model.Asn1Enumerated;

public class Asn1EnumeratedSerializer extends Asn1FieldSerializer {

    private final Asn1Enumerated asn1Enumerated;

    public Asn1EnumeratedSerializer(final Asn1Enumerated asn1Enumerated) {
        super(asn1Enumerated);
        this.asn1Enumerated = asn1Enumerated;
    }

    @Override
    public void updateLayers() {
        this.encodeEnumerated();
        super.updateLayers();
    }

    private void encodeEnumerated() {
        byte[] content = this.asn1Enumerated.getValue().toByteArray();
        this.asn1Enumerated.setContent(content);
    }
}

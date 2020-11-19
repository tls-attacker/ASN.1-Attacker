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

import de.rub.nds.asn1.model.Asn1Container;

public class DefaultAsn1ContainerSerializer extends Asn1FieldSerializer {

    private final Asn1Container asn1Container;

    public DefaultAsn1ContainerSerializer(final Asn1Container asn1Container) {
        super(asn1Container);
        this.asn1Container = asn1Container;
    }

    @Override
    public void updateLayers() {
        this.encodeContainer();
        super.updateLayers();
    }

    private void encodeContainer() {
        byte[] content = this.asn1Container.getEncodedChildren();
        this.asn1Container.setContent(content);
    }
}

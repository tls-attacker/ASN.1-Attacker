/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
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

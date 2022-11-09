/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.model.Asn1Container;
import de.rub.nds.asn1.model.Asn1Field;

public class GenericAsn1ContainerPreparator extends Asn1FieldPreparator {

    private final Asn1Container asn1Container;

    public GenericAsn1ContainerPreparator(final Asn1Container asn1Container) {
        super(asn1Container);
        this.asn1Container = asn1Container;
    }

    @Override
    protected byte[] encodeContent() {
        for (Asn1Field field : asn1Container.getChildren()) {
            // encode
        }
        return this.asn1Container.getEncodedChildren().getValue();
    }
}

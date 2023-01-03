/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.model.Asn1Container;
import de.rub.nds.asn1.model.Asn1Encodable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenericAsn1ContainerPreparator extends Asn1FieldPreparator {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Asn1Container asn1Container;

    public GenericAsn1ContainerPreparator(final Asn1Container asn1Container) {
        super(asn1Container);
        this.asn1Container = asn1Container;
    }

    @Override
    protected byte[] encodeContent() {
        LOGGER.debug("Encoding content for {}", asn1Container.getIdentifier());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (Asn1Encodable asn1Field : asn1Container.getChildren()) {
            LOGGER.info("Preparing:" + asn1Field.getIdentifier());
            asn1Field.getGenericPreparator().prepare();
            try {
                outputStream.write(asn1Field.getGenericSerializer().serialize());
            } catch (IOException ex) {
                LOGGER.error("Could not encoded child", ex);
            }
        }
        this.asn1Container.setEncodedChildren(outputStream.toByteArray());
        return this.asn1Container.getEncodedChildren().getValue();
    }
}

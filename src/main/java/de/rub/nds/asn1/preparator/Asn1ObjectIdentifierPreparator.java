/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.model.Asn1ObjectIdentifier;
import de.rub.nds.asn1.oid.ObjectIdentifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Asn1ObjectIdentifierPreparator extends Asn1FieldPreparator<Asn1ObjectIdentifier> {

    private static final Logger LOGGER = LogManager.getLogger();

    public Asn1ObjectIdentifierPreparator(Asn1ObjectIdentifier asn1ObjectIdentifier) {
        super(asn1ObjectIdentifier);
    }

    @Override
    protected byte[] encodeContent() {
        ObjectIdentifier objectIdentifier = new ObjectIdentifier(field.getValue().getValue());
        return objectIdentifier.getEncoded();
    }
}

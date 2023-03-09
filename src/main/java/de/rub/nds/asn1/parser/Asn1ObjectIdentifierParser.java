/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.model.Asn1ObjectIdentifier;
import de.rub.nds.asn1.oid.ObjectIdentifier;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Asn1ObjectIdentifierParser<Chooser extends AbstractChooser>
        extends Asn1FieldParser<Chooser, Asn1ObjectIdentifier<Chooser>> {

    private static final Logger LOGGER = LogManager.getLogger();

    public Asn1ObjectIdentifierParser(
            Chooser chooser, Asn1ObjectIdentifier<Chooser> asn1ObjectIdentifier) {
        super(chooser, asn1ObjectIdentifier);
    }

    @Override
    public void parseIndividualContentFields(InputStream byteArrayInputStream) throws IOException {
        byte[] contentBytes = byteArrayInputStream.readAllBytes();
        ObjectIdentifier oid = new ObjectIdentifier(contentBytes);
        encodable.setValue(oid.toString());
    }
}

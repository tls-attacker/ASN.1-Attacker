/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1ObjectIdentifier;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Asn1ObjectIdentifierParser extends Asn1Parser<Asn1ObjectIdentifier> {

    private static final Logger LOGGER = LogManager.getLogger();

    private final String identifier;

    public Asn1ObjectIdentifierParser(String identifier, InputStream inputStream) {
        super(inputStream);
        this.identifier = identifier;
    }

    @Override
    public Asn1ObjectIdentifier parse() {
        Asn1ObjectIdentifier asn1ObjectIdentifier = new Asn1ObjectIdentifier(identifier);
        genericParse(asn1ObjectIdentifier);
        return asn1ObjectIdentifier;
    }
}

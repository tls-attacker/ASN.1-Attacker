/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1Encodable;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Asn1Parser<Encodable extends Asn1Encodable> {

    private static final Logger LOGGER = LogManager.getLogger();

    protected final Encodable encodable;

    public Asn1Parser(Encodable field) {
        this.encodable = field;
    }

    public abstract void parse(InputStream inputStream);
}

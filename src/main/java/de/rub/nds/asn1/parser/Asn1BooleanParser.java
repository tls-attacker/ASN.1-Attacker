/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1Boolean;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Asn1BooleanParser extends Asn1Parser<Asn1Boolean> {

    private static final Logger LOGGER = LogManager.getLogger();

    public Asn1BooleanParser(InputStream stream) {
        super(stream);
    }

    @Override
    public Asn1Boolean parse() {
        Asn1Boolean asn1Boolean = new Asn1Boolean();
        genericParse(asn1Boolean);
        if (asn1Boolean.getContent().getValue().length == 0) {
            LOGGER.warn("asn1Boolean content is encoded without any content. Assuming \"false\".");
            asn1Boolean.setValue(false);
        } else if (asn1Boolean.getContent().getValue().length > 1) {
            LOGGER.warn("asn1Boolean content is encoded with more than one byte");
        }
        if (asn1Boolean.getContent().getValue()[0] == (byte) 0xFF) {
            asn1Boolean.setValue(true);
        } else if (asn1Boolean.getContent().getValue()[0] == (byte) 0x00) {
            asn1Boolean.setValue(false);
        } else {
            LOGGER.warn("asn1Boolean is not DER encoded. Assuming \"false\".");
            asn1Boolean.setValue(false);
        }
        return asn1Boolean;
    }
}

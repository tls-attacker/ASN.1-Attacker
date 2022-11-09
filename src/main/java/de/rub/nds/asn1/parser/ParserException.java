/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.parser;

public class ParserException extends RuntimeException {

    public ParserException(Throwable e) {
        super(e);
    }

    public ParserException(String message) {
        super(message);
    }
}

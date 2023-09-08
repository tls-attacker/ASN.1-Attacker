/*
 * ASN.1-Attacker - A library for arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.exceptions;

public class NotInitializedException extends RuntimeException {

    public NotInitializedException() {}

    public NotInitializedException(String message) {
        super(message);
    }

    public NotInitializedException(Throwable cause) {
        super(cause);
    }

    public NotInitializedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotInitializedException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

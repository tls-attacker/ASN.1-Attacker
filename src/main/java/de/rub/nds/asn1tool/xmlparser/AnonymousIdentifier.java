/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1tool.xmlparser;

public class AnonymousIdentifier {

    private static int anonymousIdentifierNumber = 1;

    public static String createAnonymousIdentifier() {
        String result = "_anonymous" + anonymousIdentifierNumber;
        anonymousIdentifierNumber++;
        return result;
    }
}

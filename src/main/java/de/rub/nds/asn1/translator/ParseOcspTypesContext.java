/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.translator;

import de.rub.nds.asn1.translator.contextcomponents.ParseOcspTypeContextComponent;

public class ParseOcspTypesContext extends Context {

    public static String NAME = "ParseOcspTypesContext";

    private static final ContextComponent[] contextComponents =
        new ContextComponent[] { new ParseOcspTypeContextComponent() };

    public ParseOcspTypesContext() {
        super(contextComponents);
    }

    @Override
    public String getName() {
        return NAME;
    }
}

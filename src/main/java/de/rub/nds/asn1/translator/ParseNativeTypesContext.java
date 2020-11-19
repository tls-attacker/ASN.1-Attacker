/*
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2020 Ruhr University Bochum, Paderborn University,
 * and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.rub.nds.asn1.translator;

import de.rub.nds.asn1.translator.contextcomponents.ParseNativeTypeContextComponent;

public class ParseNativeTypesContext extends Context {

    public static String NAME = "ParseNativeTypesContext";

    private static final ContextComponent[] contextComponents =
        new ContextComponent[] { new ParseNativeTypeContextComponent() };

    public ParseNativeTypesContext() {
        super(contextComponents);
    }

    @Override
    public String getName() {
        return NAME;
    }
}

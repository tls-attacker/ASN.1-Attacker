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

import java.util.HashMap;
import java.util.Map;

public class ContextRegister {

    private static ContextRegister instance = null;

    private final Map<String, Class<? extends Context>> contextClasses = new HashMap<>();

    private ContextRegister() {

    }

    public static ContextRegister getInstance() {
        if (instance == null) {
            instance = new ContextRegister();
        }
        return instance;
    }

    public void registerContext(final String contextName, final Class<? extends Context> contextClass) {
        String maskedInput = this.maskName(contextName);
        if (this.contextClasses.containsKey(maskedInput) == false) {
            this.contextClasses.put(maskedInput, contextClass);
        } else {
            throw new RuntimeException("Cannot register context " + contextName + " more than once!");
        }
    }

    public Context createContext(final String contextName) {
        try {
            String maskedInput = this.maskName(contextName);
            Class<? extends Context> contextClass = this.contextClasses.get(maskedInput);
            return contextClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private String maskName(final String input) {
        return input.trim().toLowerCase();
    }
}

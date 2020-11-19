/*
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2020 Ruhr University Bochum, Paderborn University,
 * and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.rub.nds.asn1tool.xmlparser;

import java.util.LinkedList;
import java.util.List;

public class JaxbClassList {

    private static JaxbClassList instance = null;

    private final List<Class> classList = new LinkedList<>();

    private JaxbClassList() {

    }

    public static JaxbClassList getInstance() {
        if (instance == null) {
            instance = new JaxbClassList();
        }
        return instance;
    }

    public void addClass(Class newClass) {
        if (newClass != null && this.classList.contains(newClass) == false) {
            this.classList.add(newClass);
        }
    }

    public void addClasses(Class... classes) {
        if (classes != null) {
            for (Class tmpClass : classes) {
                this.addClass(tmpClass);
            }
        }
    }

    public Class[] getClasses() {
        Class[] classes = new Class[this.classList.size()];
        for (int i = 0; i < this.classList.size(); i++) {
            classes[i] = this.classList.get(i);
        }
        return classes;
    }
}

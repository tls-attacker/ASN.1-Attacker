/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.handler;

import de.rub.nds.asn1.context.AbstractChooser;

public abstract class Handler<Chooser extends AbstractChooser> {

    protected final Chooser chooser;

    public Handler(Chooser chooser) {
        this.chooser = chooser;
    }

    public abstract void adjustContext();
}

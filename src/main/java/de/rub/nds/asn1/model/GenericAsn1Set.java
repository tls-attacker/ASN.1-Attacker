/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.handler.EmptyHandler;
import de.rub.nds.asn1.handler.Handler;
import de.rub.nds.asn1.parser.Asn1Parser;

public class GenericAsn1Set<Chooser extends AbstractChooser> extends Asn1Set<Chooser> {

    public GenericAsn1Set(String identifier) {
        super(identifier);
    }

    @Override
    public Asn1Parser getParser(Chooser chooser) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Handler getHandler(Chooser chooser) {
        return new EmptyHandler(chooser);
    }
}

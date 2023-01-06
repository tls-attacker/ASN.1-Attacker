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
import de.rub.nds.asn1.parser.Asn1UnknownParser;
import de.rub.nds.asn1.preparator.Asn1UnknownPreparator;

public class Asn1UnknownField<Chooser extends AbstractChooser> extends Asn1Field<Chooser> {

    private byte[] contentConfig;

    /** Private no-arg constructor to please JAXB */
    private Asn1UnknownField() {
        super(null);
    }

    public Asn1UnknownField(String identifier) {
        super(identifier);
    }

    public byte[] getContentConfig() {
        return contentConfig;
    }

    public void setContentConfig(byte[] contentConfig) {
        this.contentConfig = contentConfig;
    }

    @Override
    public Asn1UnknownPreparator getPreparator(Chooser chooser) {
        return new Asn1UnknownPreparator(chooser, this);
    }

    @Override
    public Asn1UnknownParser getParser(Chooser chooser) {
        return new Asn1UnknownParser(chooser, this);
    }
}

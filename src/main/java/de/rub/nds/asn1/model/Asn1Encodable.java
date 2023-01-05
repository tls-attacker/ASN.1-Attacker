/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.parser.Asn1Parser;
import de.rub.nds.asn1.preparator.Preparator;
import de.rub.nds.asn1.serializer.Asn1FieldSerializer;

public interface Asn1Encodable {

    public String getIdentifier();

    public void setIdentifier(final String identifier);

    public Asn1FieldSerializer getSerializer();

    public Preparator getPreparator();

    public Asn1Parser<?> getParser();

    public abstract boolean isOptional();

    public abstract boolean isCompatible(Integer tagNumber, Boolean constructed, Integer classType);
}

/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

public interface Asn1Encodable {

    public String getIdentifier();

    public void setIdentifier(final String identifier);

    public abstract boolean isOptional();

    public abstract boolean matchesHeader(
            Integer tagNumber, Boolean constructed, Integer classType);
}

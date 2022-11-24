/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
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

    public Asn1FieldSerializer getGenericSerializer();

    public Preparator getGenericPreparator();
    
    public Asn1Parser<?> getParser();

}

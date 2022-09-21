/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1;

import de.rub.nds.asn1.serializer.Asn1Serializer;
import java.io.IOException;
import jakarta.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

public interface Asn1Encodable {
    String getIdentifier();

    void setIdentifier(final String identifier);

    String getType();

    void setType(final String type);

    boolean hasAttribute(final String attributeName);

    String getAttribute(final String attributeName);

    void setAttribute(final String attributeName, final String attributeValue);

    Asn1Serializer getSerializer();

    Asn1Encodable getCopy() throws JAXBException, IOException, XMLStreamException;
}

/*
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2020 Ruhr University Bochum, Paderborn University,
 * and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.rub.nds.asn1;

import de.rub.nds.asn1.serializer.Asn1Serializer;

public interface Asn1Encodable {
    String getIdentifier();

    void setIdentifier(final String identifier);

    String getType();

    void setType(final String type);

    boolean hasAttribute(final String attributeName);

    String getAttribute(final String attributeName);

    void setAttribute(final String attributeName, final String attributeValue);

    Asn1Serializer getSerializer();
}

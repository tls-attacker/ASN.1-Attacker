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

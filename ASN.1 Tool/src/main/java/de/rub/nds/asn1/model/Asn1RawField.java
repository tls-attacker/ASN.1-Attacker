package de.rub.nds.asn1.model;

import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1.serializer.Asn1Serializer;
import de.rub.nds.asn1.serializer.Asn1RawFieldSerializer;
import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;

import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Asn1RawField implements Asn1Encodable {

    @XmlAttribute(name = "identifier")
    private String identifier = "";

    @XmlAttribute(name = "type")
    private String type = "";

    @XmlAnyAttribute
    private Map<QName, String> attributes = new HashMap<>();

    @XmlElement(name = "identifierOctets")
    private ModifiableByteArray identifierOctets = new ModifiableByteArray();

    @XmlElement(name = "lengthOctets")
    private ModifiableByteArray lengthOctets = new ModifiableByteArray();

    @XmlElement(name = "contentOctets")
    private ModifiableByteArray contentOctets = new ModifiableByteArray();

    public Asn1RawField() {
        this.identifierOctets.setOriginalValue(new byte[0]);
        this.lengthOctets.setOriginalValue(new byte[0]);
        this.contentOctets.setOriginalValue(new byte[0]);
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<QName, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(final Map<QName, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean hasAttribute(final String attributeName) {
        return this.attributes.containsKey(new QName(attributeName));
    }

    @Override
    public String getAttribute(final String attributeName) {
        String attribute = null;
        QName attributeQName = new QName(attributeName);
        if (this.attributes.containsKey(attributeQName)) {
            attribute = this.attributes.get(attributeQName);
        }
        return attribute;
    }

    @Override
    public void setAttribute(final String attributeName, final String attributeValue) {
        this.attributes.put(new QName(attributeName), attributeValue);
    }

    public ModifiableByteArray getIdentifierOctets() {
        return identifierOctets;
    }

    public void setIdentifierOctets(final ModifiableByteArray identifierOctets) {
        this.identifierOctets = identifierOctets;
    }

    public void setIdentifierOctets(final byte[] identifierOctets) {
        this.identifierOctets = ModifiableVariableFactory.safelySetValue(this.identifierOctets, identifierOctets);
    }

    public ModifiableByteArray getLengthOctets() {
        return lengthOctets;
    }

    public void setLengthOctets(final ModifiableByteArray lengthOctets) {
        this.lengthOctets = lengthOctets;
    }

    public void setLengthOctets(final byte[] lengthOctets) {
        this.lengthOctets = ModifiableVariableFactory.safelySetValue(this.lengthOctets, lengthOctets);
    }

    public ModifiableByteArray getContentOctets() {
        return contentOctets;
    }

    public void setContentOctets(final ModifiableByteArray contentOctets) {
        this.contentOctets = contentOctets;
    }

    public void setContentOctets(final byte[] contentOctets) {
        this.contentOctets = ModifiableVariableFactory.safelySetValue(this.contentOctets, contentOctets);
    }

    @Override
    public Asn1Serializer getSerializer() {
        return new Asn1RawFieldSerializer(this);
    }
}

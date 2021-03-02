package de.rub.nds.asn1.model;

import de.rub.nds.asn1.serializer.DefaultAsn1ContainerSerializer;
import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1.serializer.Asn1Serializer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Asn1Container extends Asn1Field {

    @XmlTransient
    private byte[] encodedChildren = new byte[0];

    public Asn1Container() {
        this(0, false, 0);
    }

    public Asn1Container(final int tagClass, final boolean tagConstructed, final int tagNumber) {
        super(tagClass, tagConstructed, tagNumber);
    }

    public byte[] getEncodedChildren() {
        return encodedChildren;
    }

    public void setEncodedChildren(byte[] encodedChildren) {
        this.encodedChildren = encodedChildren;
    }

    public abstract void addChild(final Asn1Encodable child);

    public abstract void setChildren(final List<Asn1Encodable> children);

    public abstract List<Asn1Encodable> getChildren();

    public abstract void clearChildren();

    @Override
    public Asn1Serializer getSerializer() {
        return new DefaultAsn1ContainerSerializer(this);
    }
}

package de.rub.nds.asn1.model;

import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.modifiablevariable.HoldsModifiableVariable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Asn1ConstructedOctetString extends Asn1Container {

    public static final int TAG_CLASS = TagClass.UNIVERSAL.getIntValue();

    public static final boolean IS_CONSTRUCTED = true;

    public static final int TAG_NUMBER = TagNumber.OCTET_STRING.getIntValue();

    @XmlAnyElement(lax = true)
    @HoldsModifiableVariable  
    private List<Asn1Encodable> children = new LinkedList<>();

    public Asn1ConstructedOctetString() {
        super(TAG_CLASS, IS_CONSTRUCTED, TAG_NUMBER);
    }

    @Override
    public void addChild(final Asn1Encodable child) {
        this.children.add(child);
    }

    @Override
    public List<Asn1Encodable> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<Asn1Encodable> children) {
        this.children = children;
    }

    @Override
    public void clearChildren() {
        this.children.clear();
    }
}

package de.rub.nds.asn1.model;

import de.rub.nds.asn1.serializer.Asn1PrimitivePrintableStringSerializer;
import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.serializer.Asn1Serializer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Asn1PrimitivePrintableString extends Asn1Field {

    public static final int TAG_CLASS = TagClass.UNIVERSAL.getIntValue();

    public static final boolean IS_CONSTRUCTED = false;

    public static final int TAG_NUMBER = TagNumber.PRINTABLESTRING.getIntValue();

    @XmlElement(name = "value")
    private String value = "";

    public Asn1PrimitivePrintableString() {
        super(TAG_CLASS, IS_CONSTRUCTED, TAG_NUMBER);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public Asn1Serializer getSerializer() {
        return new Asn1PrimitivePrintableStringSerializer(this);
    }
}

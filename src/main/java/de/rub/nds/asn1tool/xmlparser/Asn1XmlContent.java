package de.rub.nds.asn1tool.xmlparser;

import de.rub.nds.asn1.Asn1Encodable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Asn1XmlContent {

    @XmlAnyElement(lax = true)
    private List<Asn1Encodable> asn1Encodables = new LinkedList<>();

    public Asn1XmlContent() {

    }

    public List<Asn1Encodable> getAsn1Encodables() {
        return asn1Encodables;
    }

    public void setAsn1Encodables(List<Asn1Encodable> asn1Encodables) {
        this.asn1Encodables = asn1Encodables;
    }
}

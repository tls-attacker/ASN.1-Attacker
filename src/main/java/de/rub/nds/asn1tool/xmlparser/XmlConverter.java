package de.rub.nds.asn1tool.xmlparser;

import de.rub.nds.asn1.adapters.BigIntegerAdapter;
import de.rub.nds.modifiablevariable.util.ByteArrayAdapter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XmlConverter {

    private JAXBContext jaxbContext = null;

    private String xmlString = null;

    public XmlConverter(final Asn1XmlContent asn1XmlContent, final File file) {
        this.jaxbContext = this.createJaxbContext();
        this.convertToXml(asn1XmlContent, file);
    }

    private JAXBContext createJaxbContext() {
        Class[] classes = JaxbClassList.getInstance().getClasses();
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(classes);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return jaxbContext;
    }

    private void convertToXml(final Asn1XmlContent asn1XmlContent, final File file) {
        try {
            Marshaller marshaller = this.jaxbContext.createMarshaller();
            marshaller.setAdapter(new ByteArrayAdapter());
            marshaller.setAdapter(new BigIntegerAdapter());
            marshaller.marshal(asn1XmlContent, file);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }  catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}

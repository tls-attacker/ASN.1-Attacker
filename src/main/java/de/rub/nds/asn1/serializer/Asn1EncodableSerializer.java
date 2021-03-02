
package de.rub.nds.asn1.serializer;

import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1tool.xmlparser.JaxbClassList;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A helper class to serialize and deserialize Asn1Encodable objects.
 *
 */
public class Asn1EncodableSerializer {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * context initialization is expensive, we need to do that only once
     */
    private static JAXBContext context;

    /**
     * Returns an initialized JaxbContext
     *
     * @return
     * @throws JAXBException
     * @throws IOException
     */
    private static synchronized JAXBContext getJAXBContext() throws JAXBException, IOException {
        if (context == null) {
            Class[] classes = JaxbClassList.getInstance().getClasses();
            context = JAXBContext.newInstance(classes);
        }
        return context;
    }

    

    /**
     * Writes an Asn1Encodable to an Outputstream
     *
     * @param outputStream Outputstream to write to
     * @param asn1Encdoable TestVector to serializ
     * @throws JAXBException If something goes wrong
     * @throws IOException If something goes wrong
     */
    public static void write(OutputStream outputStream, Asn1Encodable asn1Encdoable) throws JAXBException, IOException {
        context = getJAXBContext();
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(asn1Encdoable, outputStream);
        outputStream.close();
    }

    /**
     * Reads a Asn1Encodable from an InputStream
     *
     * @param inputStream Inputstream to read from
     * @return Read TestVector
     * @throws JAXBException If something goes wrong
     * @throws IOException If something goes wrong
     * @throws XMLStreamException If something goes wrong
     */
    public static Asn1Encodable read(InputStream inputStream) throws JAXBException, IOException, XMLStreamException {
        context = getJAXBContext();
        Unmarshaller m = context.createUnmarshaller();
        XMLInputFactory xif = XMLInputFactory.newFactory();
        xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
        xif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        XMLStreamReader xsr = xif.createXMLStreamReader(inputStream);
        Asn1Encodable asn1 = (Asn1Encodable) m.unmarshal(xsr);
        inputStream.close();
        return asn1;
    }


    /**
     * Returns a deep copy of the Asn1Encodable. 
     *
     * @param asn1Encodable Asn1Encodable to copy
     * @return
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.IOException
     * @throws javax.xml.stream.XMLStreamException
     */
    public static Asn1Encodable copyAsn1Encodable(Asn1Encodable asn1Encodable) throws JAXBException, IOException, XMLStreamException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Asn1EncodableSerializer.write(stream, asn1Encodable);
        stream.flush();
        Asn1Encodable copiedAsn1Encodable = Asn1EncodableSerializer.read(new ByteArrayInputStream(stream.toByteArray()));
        return copiedAsn1Encodable;
    }

    private Asn1EncodableSerializer() {
    }

}


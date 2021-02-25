/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1tool.xmlparser;

import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1.adapters.BigIntegerAdapter;
import de.rub.nds.asn1.model.Asn1Container;
import de.rub.nds.modifiablevariable.util.ByteArrayAdapter;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XmlParser {

    private JAXBContext jaxbContext = null;

    private Asn1XmlContent asn1XmlContent = null;

    private Map<String, Asn1Encodable> identifierMap = new HashMap<>();

    public XmlParser(final String xml) {
        this.jaxbContext = this.createJaxbContext();
        this.parseXml(xml);
        this.crawlAsn1EncodedContent();
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

    private void parseXml(final String xml) {
        try {
            StringReader stringReader = new StringReader(xml);
            Unmarshaller unmarshaller = this.jaxbContext.createUnmarshaller();
            unmarshaller.setAdapter(new ByteArrayAdapter());
            unmarshaller.setAdapter(new BigIntegerAdapter());
            this.asn1XmlContent = (Asn1XmlContent) unmarshaller.unmarshal(stringReader);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (ClassCastException e) {
            throw new RuntimeException("Is the root element of type Asn1XmlContent?", e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    private void crawlAsn1EncodedContent() {
        List<Asn1Encodable> asn1Encodables = this.asn1XmlContent.getAsn1Encodables();
        this.crawlAsn1EncodedContentRecursive("", asn1Encodables);
    }

    private void crawlAsn1EncodedContentRecursive(final String basePath, final List<Asn1Encodable> asn1Encodables) {
        if (asn1Encodables != null) {
            for (Asn1Encodable asn1Encodable : asn1Encodables) {
                String identifier = this.indexAsn1Encodable(basePath, asn1Encodable);
                if (asn1Encodable instanceof Asn1Container) {
                    this.crawlAsn1EncodedContentRecursive(identifier, ((Asn1Container) asn1Encodable).getChildren());
                }
            }
        }
    }

    private String indexAsn1Encodable(final String basePath, final Asn1Encodable asn1Encodable) {
        if (asn1Encodable.getIdentifier() == null || asn1Encodable.getIdentifier().isEmpty()) {
            asn1Encodable.setIdentifier(AnonymousIdentifier.createAnonymousIdentifier());
        }
        String fullPathIdentifier = basePath + "/" + asn1Encodable.getIdentifier();
        if (this.identifierMap.containsKey(fullPathIdentifier) == false) {
            this.identifierMap.put(fullPathIdentifier, asn1Encodable);
        } else {
            throw new RuntimeException("Identifier " + fullPathIdentifier + " is used more than once!");
        }
        return fullPathIdentifier;
    }

    public Asn1XmlContent getAsn1XmlContent() {
        return asn1XmlContent;
    }

    public Map<String, Asn1Encodable> getIdentifierMap() {
        return identifierMap;
    }
}

/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1Any;
import de.rub.nds.asn1.model.Asn1Factory;
import de.rub.nds.asn1.model.Asn1Field;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public class Asn1AnyParser extends Asn1Parser<Asn1Any> {

    public Asn1AnyParser(Asn1Any field) {
        super(field);
    }

    @Override
    public void parse(InputStream inputStream) {
        try {
            byte[] tagOctets = parseTagOctets(inputStream);
            int tagClass = parseTagClass(tagOctets[0]);
            boolean constructed = parseTagConstructed(tagOctets[0]);
            int tagNumber = parseTagNumber(tagOctets);
            Asn1Field asn1Field = Asn1Factory.createAsn1Element(tagClass, constructed, tagNumber);
            asn1Field.setTagConstructed(constructed);
            asn1Field.setTagClass(tagClass);
            asn1Field.setTagNumber(tagNumber);
            Asn1Parser<?> parser = asn1Field.getParser();
            byte[] lengthOctets = parser.parseLengthOctets(inputStream);
            BigInteger length = parser.parseLength(lengthOctets);
            asn1Field.setLength(length);
            asn1Field.setLengthOctets(lengthOctets);
            byte[] content = parser.parseContentOctets(length, inputStream);
            asn1Field.setContent(content);
            parser.parseIndividualContentFields(new ByteArrayInputStream(content));
            encodable.setInstantiation(asn1Field);
        } catch (IOException ex) {
            throw new ParserException(ex);
        }
    }

    @Override
    public void parseWithoutTag(InputStream inputStream, byte[] tagOctets) {
        if (encodable.getInstantiation() == null) {
            throw new ParserException("Cannot parse AnyField without Tag");
        } else {
            encodable.getParser().parseWithoutTag(inputStream, tagOctets);
        }
    }

    @Override
    public void parseIndividualContentFields(InputStream inputStream) throws IOException {
        if (encodable.getInstantiation() == null) {
            throw new ParserException("Cannot parse AnyField without Tag");
        } else {
            encodable.getParser().parseIndividualContentFields(inputStream);
        }
    }
}

/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.model.Asn1Field;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Asn1FieldParser<Field extends Asn1Field> extends Asn1Parser<Field> {

    private static final Logger LOGGER = LogManager.getLogger();

    public Asn1FieldParser(Field field) {
        super(field);
    }

    @Override
    public void parse(InputStream inputStream) {
        try {
            encodable.setTagOctets(this.parseTagOctets(inputStream));
            encodable.setTagClass(this.parseTagClass(encodable.getTagOctets().getValue()[0]));
            encodable.setTagConstructed(this.parseTagConstructed(encodable.getTagOctets().getValue()[0]));
            encodable.setTagNumber(this.parseTagNumber(encodable.getTagOctets().getValue()));
            encodable.setLengthOctets(this.parseLengthOctets(inputStream));
            encodable.setLength(this.parseLength(encodable.getLengthOctets().getValue()));
            encodable.setContent(this.parseContentOctets(encodable.getLength().getValue(), inputStream));
            parseIndividualContentFields(new ByteArrayInputStream(encodable.getContent().getValue()));
        } catch (IOException ex) {
            throw new ParserException(ex);
        }
    }

    public abstract void parseIndividualContentFields(InputStream inputStream) throws IOException;

}

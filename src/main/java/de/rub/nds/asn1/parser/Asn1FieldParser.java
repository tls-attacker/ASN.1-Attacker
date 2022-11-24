/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.TagNumber;
import de.rub.nds.asn1.model.Asn1Field;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Asn1FieldParser<Field extends Asn1Field> extends Asn1Parser<Field> {

    private static final Logger LOGGER = LogManager.getLogger();

    public Asn1FieldParser(Field field) {
        super(field);
    }

    @Override
    public void parse(InputStream inputStream) {
        LOGGER.debug("Parsing: {}", encodable.getIdentifier());
        try {
            byte[] tagOctets = this.parseTagOctets(inputStream);
            parseWithoutTag(inputStream, tagOctets);
        } catch (IOException ex) {
            throw new ParserException(ex);
        }
    }

    @Override
    public void parseWithoutTag(InputStream inputStream, byte[] tagOctets) {
        LOGGER.debug("Parsing: {} of type {}", encodable.getIdentifier(), encodable.getClass().getSimpleName());
        try {
            encodable.setTagOctets(tagOctets);
            encodable.setTagClass(this.parseTagClass(encodable.getTagOctets().getValue()[0]));
            encodable.setTagConstructed(this.parseTagConstructed(encodable.getTagOctets().getValue()[0]));
            encodable.setTagNumber(this.parseTagNumber(encodable.getTagOctets().getValue()));
            encodable.setLengthOctets(this.parseLengthOctets(inputStream));
            encodable.setLength(this.parseLength(encodable.getLengthOctets().getValue()));
            encodable.setContent(this.parseContentOctets(encodable.getLength().getValue(), inputStream));
            parseIndividualContentFields(new ByteArrayInputStream(encodable.getContent().getValue()));
            setConstants();
            if (!areConstantsValid()) {
                throw new ParserException("Expectation missmatch");
            }
        } catch (IOException ex) {
            throw new ParserException(ex);
        }
        LOGGER.trace("Finished parsing {}", encodable.getIdentifier());
    }

    private void setConstants() {
        if (encodable.getTagClassType() == null) {
            encodable.setTagClassType(TagClass.fromIntValue(encodable.getTagClass().getValue()));
        }
        if (encodable.getTagConstructedType() != null) {
            encodable.setTagConstructedType(TagConstructed.fromBooleanValue(encodable.getTagConstructed().getValue()));
        }
        if (encodable.getTagNumberType() == null) {
            encodable.setTagNumberType(TagNumber.fromIntValue(encodable.getTagNumber().getValue()));
        }
    }

    private boolean areConstantsValid() {
        if (encodable.getTagClassType() != null
            && encodable.getTagClassType().getIntValue() != encodable.getTagClass().getValue()) {
            TagClass foundTagClass = TagClass.fromIntValue(encodable.getTagClass().getValue());
            LOGGER.warn("TagClassType did not match expectations expected " + encodable.getTagClassType().name() + "("
                + encodable.getTagClassType().getIntValue() + ") but found "
                + (foundTagClass == null ? "???" : foundTagClass.name()) + " (" + encodable.getTagClass().getValue()
                + ")");
            return false;
        }
        if (encodable.getTagConstructedType() != null
            && encodable.getTagConstructedType().getBooleanValue() != encodable.getTagConstructed().getValue()) {
            TagConstructed foundTagConstructed =
                TagConstructed.fromBooleanValue(encodable.getTagConstructed().getValue());
            LOGGER.warn("TagConstructedType did not match expectations expected "
                + encodable.getTagConstructedType().name() + "(" + encodable.getTagConstructedType().getBooleanValue()
                + ") but found " + (foundTagConstructed == null ? "???" : foundTagConstructed.name()) + " ("
                + encodable.getTagConstructed().getValue() + ")");
            return false;
        }
        if (encodable.getTagNumberType() != null
            && !Objects.equals(encodable.getTagNumberType().getIntValue(), encodable.getTagNumber().getValue())) {
            TagNumber foundTagNumber = TagNumber.fromIntValue(encodable.getTagNumber().getValue());
            LOGGER.warn("TagNumber did not match expectations expected " + encodable.getTagNumberType().name() + "("
                + encodable.getTagNumberType().getIntValue() + ") but found "
                + (foundTagNumber == null ? "???" : foundTagNumber.name()) + " (" + encodable.getTagNumber().getValue()
                + ")");
            return false;
        }
        return true;
    }

}

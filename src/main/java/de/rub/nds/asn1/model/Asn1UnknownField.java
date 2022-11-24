package de.rub.nds.asn1.model;

import de.rub.nds.asn1.parser.Asn1Parser;
import de.rub.nds.asn1.parser.Asn1UnknownParser;
import de.rub.nds.asn1.preparator.Asn1UnknownPreparator;
import de.rub.nds.asn1.preparator.Preparator;

public class Asn1UnknownField extends Asn1Field {

    private byte[] contentConfig;

    public Asn1UnknownField(String identifier) {
        super(identifier);
    }

    public byte[] getContentConfig() {
        return contentConfig;
    }

    public void setContentConfig(byte[] contentConfig) {
        this.contentConfig = contentConfig;
    }

    @Override
    public Preparator getGenericPreparator() {
        return new Asn1UnknownPreparator(this);
    }

    @Override
    public Asn1Parser<?> getParser() {
        return new Asn1UnknownParser(this);
    }

}

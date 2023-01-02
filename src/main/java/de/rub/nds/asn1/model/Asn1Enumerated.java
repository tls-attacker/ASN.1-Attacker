package de.rub.nds.asn1.model;

import de.rub.nds.asn1.parser.Asn1Parser;
import de.rub.nds.asn1.preparator.Preparator;

public class Asn1Enumerated extends Asn1Field {

    public Asn1Enumerated(String identifier) {
        super(identifier);
    }

    @Override
    public Preparator getGenericPreparator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Asn1Parser<?> getParser() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

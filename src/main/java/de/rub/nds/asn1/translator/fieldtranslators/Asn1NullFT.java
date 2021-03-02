package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1Null;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1NullFT extends Asn1FieldFT<Asn1Null> {

    public Asn1NullFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1Null());
    }

    protected Asn1NullFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1Null asn1Null) {
        super(intermediateAsn1Field, asn1Null);
    }
}

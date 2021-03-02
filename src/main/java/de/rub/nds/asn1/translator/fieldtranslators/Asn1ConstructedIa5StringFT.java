package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1ConstructedIa5String;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1ConstructedIa5StringFT extends Asn1FieldFT<Asn1ConstructedIa5String> {

    public Asn1ConstructedIa5StringFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1ConstructedIa5String());
    }

    protected Asn1ConstructedIa5StringFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1ConstructedIa5String asn1ConstructedIa5String) {
        super(intermediateAsn1Field, asn1ConstructedIa5String);
    }
}

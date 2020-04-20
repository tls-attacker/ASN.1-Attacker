package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1ConstructedBitString;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1ConstructedBitStringFT extends Asn1FieldFT<Asn1ConstructedBitString> {

    public Asn1ConstructedBitStringFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1ConstructedBitString());
    }

    protected Asn1ConstructedBitStringFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1ConstructedBitString asn1ConstructedBitString) {
        super(intermediateAsn1Field, asn1ConstructedBitString);
    }
}

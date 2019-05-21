package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1EncapsulatingBitString;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1EncapsulatingBitStringFT extends Asn1FieldFT<Asn1EncapsulatingBitString> {

    public Asn1EncapsulatingBitStringFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1EncapsulatingBitString());
    }

    protected Asn1EncapsulatingBitStringFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1EncapsulatingBitString asn1EncapsulatingBitString) {
        super(intermediateAsn1Field, asn1EncapsulatingBitString);
    }
}

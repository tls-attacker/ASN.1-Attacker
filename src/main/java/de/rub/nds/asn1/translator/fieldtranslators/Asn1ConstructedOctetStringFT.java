package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1ConstructedOctetString;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1ConstructedOctetStringFT extends Asn1FieldFT<Asn1ConstructedOctetString> {

    public Asn1ConstructedOctetStringFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1ConstructedOctetString());
    }

    protected Asn1ConstructedOctetStringFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1ConstructedOctetString asn1ConstructedOctetString) {
        super(intermediateAsn1Field, asn1ConstructedOctetString);
    }
}

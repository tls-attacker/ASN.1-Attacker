package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1EncapsulatingOctetString;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1EncapsulatingOctetStringFT extends Asn1FieldFT<Asn1EncapsulatingOctetString> {

    public Asn1EncapsulatingOctetStringFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1EncapsulatingOctetString());
    }

    protected Asn1EncapsulatingOctetStringFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1EncapsulatingOctetString asn1EncapsulatingOctetString) {
        super(intermediateAsn1Field, asn1EncapsulatingOctetString);
    }
}

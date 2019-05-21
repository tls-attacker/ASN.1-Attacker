package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1PrimitiveOctetString;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1PrimitiveOctetStringFT extends Asn1FieldFT<Asn1PrimitiveOctetString> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1PrimitiveOctetString asn1PrimitiveOctetString;

    public Asn1PrimitiveOctetStringFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1PrimitiveOctetString());
    }

    protected Asn1PrimitiveOctetStringFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1PrimitiveOctetString asn1PrimitiveOctetString) {
        super(intermediateAsn1Field, asn1PrimitiveOctetString);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1PrimitiveOctetString = asn1PrimitiveOctetString;
    }

    @Override
    public Asn1PrimitiveOctetString translate(final String identifier, final String type) {
        this.asn1PrimitiveOctetString.setValue(this.intermediateAsn1Field.getContent());
        return super.translate(identifier, type);
    }
}

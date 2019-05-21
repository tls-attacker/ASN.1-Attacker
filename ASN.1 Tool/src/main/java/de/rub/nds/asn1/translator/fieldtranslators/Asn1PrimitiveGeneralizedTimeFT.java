package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1PrimitiveGeneralizedTime;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

import java.io.UnsupportedEncodingException;

public final class Asn1PrimitiveGeneralizedTimeFT extends Asn1FieldFT<Asn1PrimitiveGeneralizedTime> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1PrimitiveGeneralizedTime asn1PrimitiveGeneralizedTime;

    public Asn1PrimitiveGeneralizedTimeFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1PrimitiveGeneralizedTime());
    }

    protected Asn1PrimitiveGeneralizedTimeFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1PrimitiveGeneralizedTime asn1PrimitiveGeneralizedTime) {
        super(intermediateAsn1Field, asn1PrimitiveGeneralizedTime);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1PrimitiveGeneralizedTime = asn1PrimitiveGeneralizedTime;
    }

    @Override
    public Asn1PrimitiveGeneralizedTime translate(final String identifier, final String type) {
        try {
            String value = new String(this.intermediateAsn1Field.getContent(), "UTF-8");
            this.asn1PrimitiveGeneralizedTime.setValue(value);
            return super.translate(identifier, type);
        } catch(UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}

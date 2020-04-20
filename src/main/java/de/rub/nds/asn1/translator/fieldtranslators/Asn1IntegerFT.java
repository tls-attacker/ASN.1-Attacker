package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1Integer;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

import java.math.BigInteger;

public final class Asn1IntegerFT extends Asn1FieldFT<Asn1Integer> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1Integer asn1Integer;

    public Asn1IntegerFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1Integer());
    }

    protected Asn1IntegerFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1Integer asn1Integer) {
        super(intermediateAsn1Field, asn1Integer);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1Integer = asn1Integer;
    }

    @Override
    public Asn1Integer translate(final String identifier, final String type) {
        BigInteger value = new BigInteger(this.intermediateAsn1Field.getContent());
        this.asn1Integer.setValue(value);
        return super.translate(identifier, type);
    }
}

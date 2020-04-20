package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1ConstructedUtcTime;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1ConstructedUtcTimeFT extends Asn1FieldFT<Asn1ConstructedUtcTime> {

    public Asn1ConstructedUtcTimeFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1ConstructedUtcTime());
    }

    protected Asn1ConstructedUtcTimeFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1ConstructedUtcTime asn1ConstructedUtcTime) {
        super(intermediateAsn1Field, asn1ConstructedUtcTime);
    }
}

package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1Sequence;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;
import java.math.BigInteger;

public final class Asn1SequenceFT extends Asn1FieldFT<Asn1Sequence> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1Sequence asn1Sequence;

    public Asn1SequenceFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1Sequence());
    }

    protected Asn1SequenceFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1Sequence asn1Sequence) {
        super(intermediateAsn1Field, asn1Sequence);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1Sequence = asn1Sequence;
    }

    @Override
    public Asn1Sequence translate(final String identifier, final String type) {
        this.asn1Sequence.setContent(BigInteger.valueOf(this.intermediateAsn1Field.getTag()).toByteArray());
        return super.translate(identifier, type);
    }
}

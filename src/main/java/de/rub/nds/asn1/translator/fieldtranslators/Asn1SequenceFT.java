package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1Sequence;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1SequenceFT extends Asn1FieldFT<Asn1Sequence> {

    public Asn1SequenceFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1Sequence());
    }

    protected Asn1SequenceFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1Sequence asn1Sequence) {
        super(intermediateAsn1Field, asn1Sequence);
    }
}
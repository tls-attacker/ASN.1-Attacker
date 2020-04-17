package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1Sequence;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

import java.util.Arrays;
import java.math.BigInteger;

public final class Asn1OcspContextSequenceFT extends Asn1FieldFT<Asn1Sequence> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1Sequence asn1Sequence;

    public Asn1OcspContextSequenceFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1Sequence());
    }

    protected Asn1OcspContextSequenceFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1Sequence asn1Sequence) {
        super(intermediateAsn1Field, asn1Sequence);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1Sequence = asn1Sequence;
    }

    @Override
    public Asn1Sequence translate(final String identifier, final String type) {
        byte[] originalTag = BigInteger.valueOf(this.intermediateAsn1Field.getTag()).toByteArray();
        if (originalTag[0] == 0x00 && originalTag.length == 2) {
            originalTag = Arrays.copyOfRange(originalTag, 1, originalTag.length);
        }
        this.asn1Sequence.setIdentifierOctets(originalTag);

        return super.translate(identifier, type);
    }
}

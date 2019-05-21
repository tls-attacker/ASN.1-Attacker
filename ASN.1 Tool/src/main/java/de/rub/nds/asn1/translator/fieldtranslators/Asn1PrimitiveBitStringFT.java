package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1PrimitiveBitString;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;
import de.rub.nds.util.ByteArrayUtils;

public final class Asn1PrimitiveBitStringFT extends Asn1FieldFT<Asn1PrimitiveBitString> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1PrimitiveBitString asn1PrimitiveBitString;

    public Asn1PrimitiveBitStringFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1PrimitiveBitString());
    }

    protected Asn1PrimitiveBitStringFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1PrimitiveBitString asn1PrimitiveBitString) {
        super(intermediateAsn1Field, asn1PrimitiveBitString);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1PrimitiveBitString = asn1PrimitiveBitString;
    }

    @Override
    public Asn1PrimitiveBitString translate(final String identifier, final String type) {
        byte[] content = this.intermediateAsn1Field.getContent();
        int unusedBits = content[0] & 0xFF;
        byte[] value = ByteArrayUtils.slice(content, 1, content.length - 1);
        this.asn1PrimitiveBitString.setUnusedBits(unusedBits);
        this.asn1PrimitiveBitString.setValue(value);
        return super.translate(identifier, type);
    }
}

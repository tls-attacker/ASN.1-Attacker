package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1EndOfContent;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1EndOfContentFT extends Asn1FieldFT<Asn1EndOfContent> {

    public Asn1EndOfContentFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1EndOfContent());
    }

    protected Asn1EndOfContentFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1EndOfContent asn1EndOfContent) {
        super(intermediateAsn1Field, asn1EndOfContent);
    }
}

package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1ConstructedPrintableString;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1ConstructedPrintableStringFT extends Asn1FieldFT<Asn1ConstructedPrintableString> {

    public Asn1ConstructedPrintableStringFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1ConstructedPrintableString());
    }

    protected Asn1ConstructedPrintableStringFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1ConstructedPrintableString asn1ConstructedPrintableString) {
        super(intermediateAsn1Field, asn1ConstructedPrintableString);
    }
}

package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1Boolean;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1BooleanFT extends Asn1FieldFT<Asn1Boolean> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1Boolean asn1Boolean;

    public Asn1BooleanFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1Boolean());
    }

    protected Asn1BooleanFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1Boolean asn1Boolean) {
        super(intermediateAsn1Field, asn1Boolean);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1Boolean = asn1Boolean;
    }

    @Override
    public Asn1Boolean translate(final String identifier, final String type) {
        byte[] content = intermediateAsn1Field.getContent();
        this.asn1Boolean.setValue((content != null && content.length > 0 && content[0] != 0) ? true : false);
        return super.translate(identifier, type);
    }
}

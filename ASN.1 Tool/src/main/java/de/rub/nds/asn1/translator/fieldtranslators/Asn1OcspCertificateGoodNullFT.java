package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1Null;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;
import java.math.BigInteger;

public final class Asn1OcspCertificateGoodNullFT extends Asn1FieldFT<Asn1Null> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1Null asn1Null;

    public Asn1OcspCertificateGoodNullFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1Null());
    }

    protected Asn1OcspCertificateGoodNullFT(final IntermediateAsn1Field intermediateAsn1Field, final Asn1Null asn1Null) {
        super(intermediateAsn1Field, asn1Null);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1Null = asn1Null;
    }

    @Override
    public Asn1Null translate(final String identifier, final String type) {
        this.asn1Null.setIdentifierOctets(BigInteger.valueOf(this.intermediateAsn1Field.getTag()).toByteArray());
        return super.translate(identifier, type);
    }
}

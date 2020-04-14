package de.rub.nds.asn1.translator.ocspcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.model.Asn1Null;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1OcspCertificateGoodNullFT;

public class Asn1OcspCertificateGoodNullCCO extends ContextComponentOption<Asn1Null> {

    public Asn1OcspCertificateGoodNullCCO() {
        super(
                -128, // supposed to be 0x80, but Java uses signed integers
                TagClass.CONTEXT_SPECIFIC.getIntValue(),
                TagConstructed.CONSTRUCTED.getBooleanValue(),
                0,
                false,
                Asn1OcspCertificateGoodNullFT.class,
                null
        );
    }
}
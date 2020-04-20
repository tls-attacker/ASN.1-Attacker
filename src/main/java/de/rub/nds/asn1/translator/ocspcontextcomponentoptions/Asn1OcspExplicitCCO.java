package de.rub.nds.asn1.translator.ocspcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.model.Asn1Explicit;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1OcspExplicitFT;

public class Asn1OcspExplicitCCO extends ContextComponentOption<Asn1Explicit> {

    public Asn1OcspExplicitCCO(final String subContextName) {
        super(
                TagClass.CONTEXT_SPECIFIC.getIntValue(),
                TagConstructed.CONSTRUCTED.getBooleanValue(),
                0,
                true,
                Asn1OcspExplicitFT.class,
                subContextName
        );
    }
}
package de.rub.nds.asn1.translator.ocspcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.model.Asn1Explicit;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1OcspExplicitFT;

public class Asn1OcspA0ExplicitCCO extends ContextComponentOption<Asn1Explicit> {

    public Asn1OcspA0ExplicitCCO(final String subContextName) {
        super(
                0xA0,
                TagClass.CONTEXT_SPECIFIC.getIntValue(),
                TagConstructed.CONSTRUCTED.getBooleanValue(),
                0,
                true,
                Asn1OcspExplicitFT.class,
                subContextName
        );
    }
}
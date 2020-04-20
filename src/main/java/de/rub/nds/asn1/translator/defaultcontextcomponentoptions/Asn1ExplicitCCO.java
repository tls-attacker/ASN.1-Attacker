package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.model.Asn1Explicit;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1ExplicitFT;

public class Asn1ExplicitCCO extends ContextComponentOption<Asn1Explicit> {

    public Asn1ExplicitCCO(final String subContextName) {
        super(
                TagClass.CONTEXT_SPECIFIC.getIntValue(),
                TagConstructed.CONSTRUCTED.getBooleanValue(),
                0,
                true,
                Asn1ExplicitFT.class,
                subContextName
        );
    }
}
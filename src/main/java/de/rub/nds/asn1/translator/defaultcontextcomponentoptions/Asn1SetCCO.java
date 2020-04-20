package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1Set;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1SetFT;

public class Asn1SetCCO extends ContextComponentOption<Asn1Set> {

    public Asn1SetCCO(final String subContextName) {
        super(
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.CONSTRUCTED.getBooleanValue(),
                TagNumber.SET.getIntValue(),
                true,
                Asn1SetFT.class,
                subContextName
        );
    }
}
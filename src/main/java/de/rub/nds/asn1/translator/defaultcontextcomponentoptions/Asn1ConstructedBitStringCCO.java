package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1ConstructedBitString;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1ConstructedBitStringFT;

public class Asn1ConstructedBitStringCCO extends ContextComponentOption<Asn1ConstructedBitString> {

    public Asn1ConstructedBitStringCCO(final String subContextName) {
        super(
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.CONSTRUCTED.getBooleanValue(),
                TagNumber.BIT_STRING.getIntValue(),
                true,
                Asn1ConstructedBitStringFT.class,
                subContextName
        );
    }
}

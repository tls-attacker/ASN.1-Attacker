package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1EncapsulatingBitString;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1EncapsulatingBitStringFT;

public class Asn1EncapsulatingBitStringCCO extends ContextComponentOption<Asn1EncapsulatingBitString> {

    public Asn1EncapsulatingBitStringCCO(final String subContextName) {
        super(
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.PRIMITIVE.getBooleanValue(),
                TagNumber.BIT_STRING.getIntValue(),
                true,
                Asn1EncapsulatingBitStringFT.class,
                subContextName
        );
    }
}
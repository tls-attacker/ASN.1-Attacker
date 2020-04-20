package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1PrimitiveBitString;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1PrimitiveBitStringFT;

public class Asn1PrimitiveBitStringCCO extends ContextComponentOption<Asn1PrimitiveBitString> {

    public Asn1PrimitiveBitStringCCO() {
        super(
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.PRIMITIVE.getBooleanValue(),
                TagNumber.BIT_STRING.getIntValue(),
                false,
                Asn1PrimitiveBitStringFT.class,
                null
        );
    }
}
package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1PrimitiveUtf8String;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1PrimitiveUtf8StringFT;

public class Asn1PrimitiveUtf8StringCCO extends ContextComponentOption<Asn1PrimitiveUtf8String> {

    public Asn1PrimitiveUtf8StringCCO() {
        super(
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.PRIMITIVE.getBooleanValue(),
                TagNumber.UTF8STRING.getIntValue(),
                false,
                Asn1PrimitiveUtf8StringFT.class,
                null
        );
    }
}
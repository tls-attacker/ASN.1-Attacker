package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1PrimitiveT61String;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1PrimitiveT61StringFT;

public class Asn1PrimitiveT61StringCCO extends ContextComponentOption<Asn1PrimitiveT61String> {

    public Asn1PrimitiveT61StringCCO() {
        super(
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.PRIMITIVE.getBooleanValue(),
                TagNumber.T61STRING.getIntValue(),
                false,
                Asn1PrimitiveT61StringFT.class,
                null
        );
    }
}
package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1PrimitiveIa5String;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1PrimitiveIa5StringFT;

public class Asn1PrimitiveIa5StringCCO extends ContextComponentOption<Asn1PrimitiveIa5String> {

    public Asn1PrimitiveIa5StringCCO() {
        super(
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.PRIMITIVE.getBooleanValue(),
                TagNumber.IA5STRING.getIntValue(),
                false,
                Asn1PrimitiveIa5StringFT.class,
                null
        );
    }
}
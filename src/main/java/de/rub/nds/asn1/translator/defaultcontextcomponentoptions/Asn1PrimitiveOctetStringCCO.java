package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1PrimitiveOctetString;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1PrimitiveOctetStringFT;

public class Asn1PrimitiveOctetStringCCO extends ContextComponentOption<Asn1PrimitiveOctetString> {

    public Asn1PrimitiveOctetStringCCO() {
        super(
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.PRIMITIVE.getBooleanValue(),
                TagNumber.OCTET_STRING.getIntValue(),
                false,
                Asn1PrimitiveOctetStringFT.class,
                null
        );
    }
}
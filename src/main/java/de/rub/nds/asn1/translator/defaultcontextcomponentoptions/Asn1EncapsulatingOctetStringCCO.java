package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1EncapsulatingOctetString;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1EncapsulatingOctetStringFT;

public class Asn1EncapsulatingOctetStringCCO extends ContextComponentOption<Asn1EncapsulatingOctetString> {

    public Asn1EncapsulatingOctetStringCCO(final String subContextName) {
        super(
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.PRIMITIVE.getBooleanValue(),
                TagNumber.OCTET_STRING.getIntValue(),
                true,
                Asn1EncapsulatingOctetStringFT.class,
                subContextName
        );
    }
}
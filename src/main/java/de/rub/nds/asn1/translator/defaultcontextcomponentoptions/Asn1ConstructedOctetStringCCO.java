package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1ConstructedOctetString;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1ConstructedOctetStringFT;

public class Asn1ConstructedOctetStringCCO extends ContextComponentOption<Asn1ConstructedOctetString> {

    public Asn1ConstructedOctetStringCCO(final String subContextName) {
        super(
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.CONSTRUCTED.getBooleanValue(),
                TagNumber.OCTET_STRING.getIntValue(),
                true,
                Asn1ConstructedOctetStringFT.class,
                subContextName
        );
    }
}
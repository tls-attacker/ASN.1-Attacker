package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1ConstructedUtf8String;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1ConstructedUtf8StringFT;

public class Asn1ConstructedUtf8StringCCO extends ContextComponentOption<Asn1ConstructedUtf8String> {

    public Asn1ConstructedUtf8StringCCO(final String subContextName) {
        super(
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.CONSTRUCTED.getBooleanValue(),
                TagNumber.UTF8STRING.getIntValue(),
                true,
                Asn1ConstructedUtf8StringFT.class,
                subContextName
        );
    }
}
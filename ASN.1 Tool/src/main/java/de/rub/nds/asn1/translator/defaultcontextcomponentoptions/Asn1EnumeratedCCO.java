package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1Enumerated;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1EnumeratedFT;

public class Asn1EnumeratedCCO extends ContextComponentOption<Asn1Enumerated> {

    public Asn1EnumeratedCCO() {
        super(
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.PRIMITIVE.getBooleanValue(),
                TagNumber.ENUMERATED.getIntValue(),
                false,
                Asn1EnumeratedFT.class,
                null
        );
    }
}
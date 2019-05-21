package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1Integer;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1IntegerFT;

public class Asn1IntegerCCO extends ContextComponentOption<Asn1Integer> {

    public Asn1IntegerCCO() {
        super(
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.PRIMITIVE.getBooleanValue(),
                TagNumber.INTEGER.getIntValue(),
                false,
                Asn1IntegerFT.class,
                null
        );
    }
}
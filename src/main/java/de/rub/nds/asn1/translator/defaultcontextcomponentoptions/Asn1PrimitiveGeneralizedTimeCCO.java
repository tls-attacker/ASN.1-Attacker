package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1PrimitiveGeneralizedTime;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1PrimitiveGeneralizedTimeFT;

public class Asn1PrimitiveGeneralizedTimeCCO extends ContextComponentOption<Asn1PrimitiveGeneralizedTime> {

    public Asn1PrimitiveGeneralizedTimeCCO() {
        super(
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.PRIMITIVE.getBooleanValue(),
                TagNumber.GENERALIZEDTIME.getIntValue(),
                false,
                Asn1PrimitiveGeneralizedTimeFT.class,
                null
        );
    }
}

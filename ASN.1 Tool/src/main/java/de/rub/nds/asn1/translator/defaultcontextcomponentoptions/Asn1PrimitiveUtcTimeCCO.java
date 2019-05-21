package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1PrimitiveUtcTime;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1PrimitiveUtcTimeFT;

public class Asn1PrimitiveUtcTimeCCO extends ContextComponentOption<Asn1PrimitiveUtcTime> {

    public Asn1PrimitiveUtcTimeCCO() {
        super(
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.PRIMITIVE.getBooleanValue(),
                TagNumber.UTCTIME.getIntValue(),
                false,
                Asn1PrimitiveUtcTimeFT.class,
                null
        );
    }
}
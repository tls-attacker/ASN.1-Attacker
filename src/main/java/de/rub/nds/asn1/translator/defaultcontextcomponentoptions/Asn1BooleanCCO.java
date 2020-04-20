package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1Boolean;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1BooleanFT;

public class Asn1BooleanCCO extends ContextComponentOption<Asn1Boolean> {

    public Asn1BooleanCCO() {
        super(
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.PRIMITIVE.getBooleanValue(),
                TagNumber.BOOLEAN.getIntValue(),
                false,
                Asn1BooleanFT.class,
                null
        );
    }
}

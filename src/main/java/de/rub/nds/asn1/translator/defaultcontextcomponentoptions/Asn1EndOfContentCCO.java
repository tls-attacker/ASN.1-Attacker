package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1EndOfContent;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1EndOfContentFT;

public class Asn1EndOfContentCCO extends ContextComponentOption<Asn1EndOfContent> {

    public Asn1EndOfContentCCO() {
        super(
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.PRIMITIVE.getBooleanValue(),
                TagNumber.END_OF_CONTENT.getIntValue(),
                false,
                Asn1EndOfContentFT.class,
                null
        );
    }
}
package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1Sequence;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1SequenceFT;

public class Asn1SequenceCCO extends ContextComponentOption<Asn1Sequence> {

    public Asn1SequenceCCO(final String subContextName) {
        super(
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.CONSTRUCTED.getBooleanValue(),
                TagNumber.SEQUENCE.getIntValue(),
                true,
                Asn1SequenceFT.class,
                subContextName
        );
    }
}
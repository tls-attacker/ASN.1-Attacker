package de.rub.nds.asn1.translator.ocspcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1Sequence;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1OcspContextSequenceFT;

public class Asn1OcspA3ContextSequenceCCO extends ContextComponentOption<Asn1Sequence> {

    public Asn1OcspA3ContextSequenceCCO(final String subContextName) {
        super(
                0xA3,
                TagClass.CONTEXT_SPECIFIC.getIntValue(),
                TagConstructed.CONSTRUCTED.getBooleanValue(),
                0,
                true,
                Asn1OcspContextSequenceFT.class,
                subContextName
        );
    }
}
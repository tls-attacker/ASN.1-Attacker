package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1ObjectIdentifier;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1ObjectIdentifierFT;

public class Asn1ObjectIdentifierCCO extends ContextComponentOption<Asn1ObjectIdentifier> {

    public Asn1ObjectIdentifierCCO() {
        super(
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.PRIMITIVE.getBooleanValue(),
                TagNumber.OBJECT_IDENTIFIER.getIntValue(),
                false,
                Asn1ObjectIdentifierFT.class,
                null
        );
    }
}
package de.rub.nds.asn1.translator.ocspcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1ObjectIdentifier;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1ObjectIdentifierFT;

public class Asn1OcspObjectIdentifierCCO extends ContextComponentOption<Asn1ObjectIdentifier> {

    public Asn1OcspObjectIdentifierCCO() {
        super(
                0x06,
                TagClass.UNIVERSAL.getIntValue(),
                TagConstructed.PRIMITIVE.getBooleanValue(),
                TagNumber.OBJECT_IDENTIFIER.getIntValue(),
                false,
                Asn1ObjectIdentifierFT.class,
                null
        );
    }
}
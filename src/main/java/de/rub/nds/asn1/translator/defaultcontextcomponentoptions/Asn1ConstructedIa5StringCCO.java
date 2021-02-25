/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1ConstructedIa5String;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1ConstructedIa5StringFT;

public class Asn1ConstructedIa5StringCCO extends ContextComponentOption<Asn1ConstructedIa5String> {

    public Asn1ConstructedIa5StringCCO(final String subContextName) {
        super(TagClass.UNIVERSAL.getIntValue(), TagConstructed.CONSTRUCTED.getBooleanValue(),
            TagNumber.IA5STRING.getIntValue(), true, Asn1ConstructedIa5StringFT.class, subContextName);
    }
}
/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1ConstructedT61String;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1ConstructedT61StringFT;

public class Asn1ConstructedT61StringCCO extends ContextComponentOption<Asn1ConstructedT61String> {

    public Asn1ConstructedT61StringCCO(final String subContextName) {
        super(TagClass.UNIVERSAL.getIntValue(), TagConstructed.CONSTRUCTED.getBooleanValue(),
            TagNumber.T61STRING.getIntValue(), true, Asn1ConstructedT61StringFT.class, subContextName);
    }
}
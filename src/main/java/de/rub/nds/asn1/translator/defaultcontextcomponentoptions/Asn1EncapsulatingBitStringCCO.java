/*
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2020 Ruhr University Bochum, Paderborn University,
 * and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.rub.nds.asn1.translator.defaultcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.asn1.model.Asn1EncapsulatingBitString;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1EncapsulatingBitStringFT;

public class Asn1EncapsulatingBitStringCCO extends ContextComponentOption<Asn1EncapsulatingBitString> {

    public Asn1EncapsulatingBitStringCCO(final String subContextName) {
        super(TagClass.UNIVERSAL.getIntValue(), TagConstructed.PRIMITIVE.getBooleanValue(), TagNumber.BIT_STRING
            .getIntValue(), true, Asn1EncapsulatingBitStringFT.class, subContextName);
    }
}
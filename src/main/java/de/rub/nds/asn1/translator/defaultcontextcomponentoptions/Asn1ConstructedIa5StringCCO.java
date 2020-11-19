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
import de.rub.nds.asn1.model.Asn1ConstructedIa5String;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1ConstructedIa5StringFT;

public class Asn1ConstructedIa5StringCCO extends ContextComponentOption<Asn1ConstructedIa5String> {

    public Asn1ConstructedIa5StringCCO(final String subContextName) {
        super(TagClass.UNIVERSAL.getIntValue(), TagConstructed.CONSTRUCTED.getBooleanValue(), TagNumber.IA5STRING
            .getIntValue(), true, Asn1ConstructedIa5StringFT.class, subContextName);
    }
}
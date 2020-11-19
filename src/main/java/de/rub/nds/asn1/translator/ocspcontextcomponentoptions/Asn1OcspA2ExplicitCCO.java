/*
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2020 Ruhr University Bochum, Paderborn University,
 * and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.rub.nds.asn1.translator.ocspcontextcomponentoptions;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.model.Asn1Explicit;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1OcspExplicitFT;

public class Asn1OcspA2ExplicitCCO extends ContextComponentOption<Asn1Explicit> {

    public Asn1OcspA2ExplicitCCO(final String subContextName) {
        super(0xA2, TagClass.CONTEXT_SPECIFIC.getIntValue(), TagConstructed.CONSTRUCTED.getBooleanValue(), 2, true,
            Asn1OcspExplicitFT.class, subContextName);
    }
}
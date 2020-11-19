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
import de.rub.nds.asn1.model.Asn1PrimitiveIa5String;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1PrimitiveIa5StringFT;

public class Asn1OcspRevocationUrlContextIA5StringCCO extends ContextComponentOption<Asn1PrimitiveIa5String> {

    public Asn1OcspRevocationUrlContextIA5StringCCO() {
        super(
            134, // 0x86 unsigned
            TagClass.CONTEXT_SPECIFIC.getIntValue(), TagConstructed.CONSTRUCTED.getBooleanValue(), 0, false,
            Asn1PrimitiveIa5StringFT.class, null);
    }
}
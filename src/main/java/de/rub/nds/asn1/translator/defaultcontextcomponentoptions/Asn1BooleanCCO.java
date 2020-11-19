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
import de.rub.nds.asn1.model.Asn1Boolean;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1BooleanFT;

public class Asn1BooleanCCO extends ContextComponentOption<Asn1Boolean> {

    public Asn1BooleanCCO() {
        super(TagClass.UNIVERSAL.getIntValue(), TagConstructed.PRIMITIVE.getBooleanValue(), TagNumber.BOOLEAN
            .getIntValue(), false, Asn1BooleanFT.class, null);
    }
}

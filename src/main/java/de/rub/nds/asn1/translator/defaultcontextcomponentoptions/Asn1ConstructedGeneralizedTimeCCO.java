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
import de.rub.nds.asn1.model.Asn1ConstructedGeneralizedTime;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.fieldtranslators.Asn1ConstructedGeneralizedTimeFT;

public class Asn1ConstructedGeneralizedTimeCCO extends ContextComponentOption<Asn1ConstructedGeneralizedTime> {

    public Asn1ConstructedGeneralizedTimeCCO(final String subContextName) {
        super(TagClass.UNIVERSAL.getIntValue(), TagConstructed.CONSTRUCTED.getBooleanValue(), TagNumber.GENERALIZEDTIME
            .getIntValue(), true, Asn1ConstructedGeneralizedTimeFT.class, subContextName);
    }
}
/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.time;

import de.rub.nds.asn1.model.Asn1Encodable;
import org.joda.time.DateTime;

public interface TimeField extends Asn1Encodable {
    public abstract DateTime getTimeValue();

    public abstract void setValue(String timeValue);
}

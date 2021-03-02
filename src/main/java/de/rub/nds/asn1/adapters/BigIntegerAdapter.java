/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.adapters;

import java.math.BigInteger;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BigIntegerAdapter extends XmlAdapter<String, BigInteger> {

    @Override
    public BigInteger unmarshal(String value) {

        value = value.replaceAll("\\s", "");
        return new BigInteger(value, 10);
    }

    @Override
    public String marshal(BigInteger value) {
        return value.toString();
    }
}

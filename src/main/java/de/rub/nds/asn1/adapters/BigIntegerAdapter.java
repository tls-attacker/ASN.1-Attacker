/*
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2020 Ruhr University Bochum, Paderborn University,
 * and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.rub.nds.asn1.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigInteger;

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

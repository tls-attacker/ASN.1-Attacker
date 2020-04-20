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

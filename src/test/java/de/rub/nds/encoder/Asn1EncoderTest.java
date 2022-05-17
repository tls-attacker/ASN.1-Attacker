/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.encoder;

import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1.encoder.Asn1Encoder;
import de.rub.nds.asn1.model.Asn1Container;
import de.rub.nds.asn1.model.Asn1RawBytes;
import de.rub.nds.asn1.serializer.Asn1EncodableSerializer;
import de.rub.nds.asn1.serializer.Asn1RawBytesSerializer;
import de.rub.nds.asn1.serializer.Asn1Serializer;
import de.rub.nds.util.ByteArrayUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class Asn1EncoderTest {

    private Asn1Encoder cut;
    private static final String TEST_STRING_VALUE = "ASN1_TEST";
    private static final String TEST_EXPECTED_TWO_ENTRIES = TEST_STRING_VALUE + 0 + TEST_STRING_VALUE + 1;

    @Nested
    class callEncode {

        @Test
        public void andListContainsOneEntry() {
            // given
            cut = new Asn1Encoder(createAsn1Encodables(1));
            byte[] expected = (TEST_STRING_VALUE + 0).getBytes();

            // when
            byte[] actual = cut.encode();

            // then
            Assertions.assertArrayEquals(expected, actual, "Arrays are not properly merged!");
        }

        @Test
        public void andListContainsTwoEntries() {
            // given
            cut = new Asn1Encoder(createAsn1Encodables(2));
            byte[] expected = TEST_EXPECTED_TWO_ENTRIES.getBytes();

            // when
            byte[] actual = cut.encode();

            // then
            Assertions.assertArrayEquals(expected, actual, "Arrays are not properly merged!");
        }

        @Test
        public void andAndAsn1EncodableIsOfTypeAsn1Container() {
            // given
            byte[] expected;

            // The octet identifier and encoded length of each children have to be considered in the total expected
            // length
            byte[] encodedIdentifierAndLength = new byte[] { 0, 20 };
            byte[] entriesAsBytes = TEST_EXPECTED_TWO_ENTRIES.getBytes();
            List<Asn1Container> asn1Containers = createAsn1Containers(2);
            cut = new Asn1Encoder(asn1Containers.get(0), new Asn1Container[] { asn1Containers.get(1) });

            expected = ByteArrayUtils.merge(encodedIdentifierAndLength, entriesAsBytes);

            // Since there are two entries it has to be merged twice for testing
            expected = ByteArrayUtils.merge(expected, expected);

            // when
            byte[] actual = cut.encode();

            // then
            Assertions.assertArrayEquals(expected, actual, "Arrays are not properly merged!");
        }

    }

    private List<Asn1Encodable> createAsn1Encodables(int amountOfEntries) {
        List<Asn1Encodable> asn1Encodables = new ArrayList<>();

        for (int i = 0; i < amountOfEntries; i++) {

            int ctr = i;
            Asn1Encodable asn1Encodable = new Asn1Encodable() {
                @Override
                public String getIdentifier() {
                    return "ASN1_IDENTIFIER";
                }

                @Override
                public void setIdentifier(String identifier) {

                }

                @Override
                public String getType() {
                    return "ASN1";
                }

                @Override
                public void setType(String type) {

                }

                @Override
                public boolean hasAttribute(String attributeName) {
                    return true;
                }

                @Override
                public String getAttribute(String attributeName) {
                    return "ATTR1";
                }

                @Override
                public void setAttribute(String attributeName, String attributeValue) {

                }

                @Override
                public Asn1Encodable getCopy() throws JAXBException, IOException, XMLStreamException {
                    return Asn1EncodableSerializer.copyAsn1Encodable(this);
                }

                @Override
                public Asn1Serializer getSerializer() {
                    Asn1RawBytes rawBytes = new Asn1RawBytes();
                    rawBytes.setValue((TEST_STRING_VALUE + ctr).getBytes());

                    return new Asn1RawBytesSerializer(rawBytes);
                }
            };

            asn1Encodables.add(asn1Encodable);

        }

        return asn1Encodables;
    }

    private List<Asn1Container> createAsn1Containers(int amountOfEntries) {
        List<Asn1Container> asn1Containers = new ArrayList<>();

        for (int i = 0; i < amountOfEntries; i++) {

            Asn1Container asn1Container = new Asn1Container() {
                @Override
                public void addChild(Asn1Encodable child) {

                }

                @Override
                public void setChildren(List<Asn1Encodable> children) {

                }

                @Override
                public List<Asn1Encodable> getChildren() {
                    return createAsn1Encodables(2);
                }

                @Override
                public void clearChildren() {

                }
            };

            asn1Containers.add(asn1Container);
        }

        return asn1Containers;
    }
}

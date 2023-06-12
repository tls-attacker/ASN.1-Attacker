/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import de.rub.nds.asn1.model.Asn1Integer;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Asn1FieldPreparatorTest {

    public Asn1FieldPreparatorTest() {}

    @BeforeEach
    public void setUp() {}

    @AfterEach
    public void tearDown() {}

    @Test
    public void testPrepare() {
        testSize(1, "020100");
        testSize(2, "02020000");
        testSize(
                127,
                "027F00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        testSize(
                128,
                "0281800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        testSize(
                255,
                "0281FF000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        testSize(
                256,
                "0282010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
    }

    public void testSize(int size, String expectedResult) {
        Asn1FieldPreparatorImpl instance = new Asn1FieldPreparatorImpl(size);
        instance.prepare();
        assertArrayEquals(
                ArrayConverter.hexStringToByteArray(expectedResult),
                ArrayConverter.concatenate(
                        instance.field.getTagOctets().getValue(),
                        instance.field.getLengthOctets().getValue(),
                        instance.field.getContent().getValue()),
                "Expected: "
                        + expectedResult
                        + " Found: "
                        + ArrayConverter.bytesToHexString(
                                ArrayConverter.concatenate(
                                        instance.field.getTagOctets().getValue(),
                                        instance.field.getLengthOctets().getValue(),
                                        instance.field.getContent().getValue())));
    }

    public class Asn1FieldPreparatorImpl extends Asn1FieldPreparator<Asn1Integer> {

        private final int size;

        public Asn1FieldPreparatorImpl(int size) {
            super(new Asn1Integer("testInteger"));
            this.size = size;
        }

        @Override
        public byte[] encodeContent() {
            return new byte[size];
        }
    }
}

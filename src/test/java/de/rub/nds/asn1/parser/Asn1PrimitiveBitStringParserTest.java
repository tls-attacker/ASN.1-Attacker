/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.TagNumber;
import de.rub.nds.asn1.context.EmptyChooser;
import de.rub.nds.asn1.model.Asn1PrimitiveBitString;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Asn1PrimitiveBitStringParserTest {

    /** Test of parseIndividualContentFields method, of class Asn1PrimitiveBitStringParser. */
    @Test
    public void testParseIndividualContentFields() throws Exception {
        Asn1PrimitiveBitString asn1PrimitiveBitString = new Asn1PrimitiveBitString("test");
        InputStream byteArrayInputStream =
                new ByteArrayInputStream(ArrayConverter.hexStringToByteArray("0304066E5DC0"));
        Asn1PrimitiveBitStringParser instance =
                new Asn1PrimitiveBitStringParser(new EmptyChooser(), asn1PrimitiveBitString);
        instance.parse(byteArrayInputStream);
        Assertions.assertEquals(TagNumber.BIT_STRING, asn1PrimitiveBitString.getTagNumberType());
        Assertions.assertEquals(TagClass.UNIVERSAL, asn1PrimitiveBitString.getTagClassType());
        Assertions.assertEquals(
                TagConstructed.PRIMITIVE, asn1PrimitiveBitString.getTagConstructedType());
        Assertions.assertArrayEquals(
                ArrayConverter.hexStringToByteArray("03"),
                asn1PrimitiveBitString.getTagOctets().getValue());
        Assertions.assertArrayEquals(
                ArrayConverter.hexStringToByteArray("04"),
                asn1PrimitiveBitString.getLengthOctets().getValue());
        Assertions.assertArrayEquals(
                ArrayConverter.hexStringToByteArray("066E5DC0"),
                asn1PrimitiveBitString.getContent().getValue());
        Assertions.assertEquals((byte) 0x06, asn1PrimitiveBitString.getUnusedBits().getValue());
        System.out.println(
                ArrayConverter.bytesToHexString(asn1PrimitiveBitString.getUsedBits().getValue()));
        Assertions.assertArrayEquals(
                ArrayConverter.hexStringToByteArray("01B977"),
                asn1PrimitiveBitString.getUsedBits().getValue());
        Assertions.assertEquals((byte) 0, asn1PrimitiveBitString.getPadding().getValue());
    }
}

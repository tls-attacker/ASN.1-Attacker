/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.TagNumber;
import de.rub.nds.asn1.context.EmptyChooser;
import de.rub.nds.asn1.model.Asn1PrimitiveBitString;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Asn1PrimitiveBitStringPreparatorTest {

    @Test
    public void testEncodeContent() {
        Asn1PrimitiveBitString<EmptyChooser> asn1PrimitiveBitString =
                new Asn1PrimitiveBitString<>("test");
        Preparator preparator = asn1PrimitiveBitString.getPreparator(null);
        asn1PrimitiveBitString.setUsedBits(ArrayConverter.hexStringToByteArray("01B977"));
        asn1PrimitiveBitString.setUnusedBits((byte) 6);
        preparator.prepare();

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
        Assertions.assertEquals((byte) 0, asn1PrimitiveBitString.getPadding().getValue());
    }
}

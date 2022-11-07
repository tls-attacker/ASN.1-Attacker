/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1tool;

import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1.encoder.Asn1Encoder;
import de.rub.nds.asn1.model.Asn1Boolean;
import de.rub.nds.asn1.model.Asn1ConstructedBitString;
import de.rub.nds.asn1.model.Asn1ConstructedIa5String;
import de.rub.nds.asn1.model.Asn1ConstructedOctetString;
import de.rub.nds.asn1.model.Asn1ConstructedPrintableString;
import de.rub.nds.asn1.model.Asn1ConstructedT61String;
import de.rub.nds.asn1.model.Asn1ConstructedUtcTime;
import de.rub.nds.asn1.model.Asn1ConstructedUtf8String;
import de.rub.nds.asn1.model.Asn1Container;
import de.rub.nds.asn1.model.Asn1EncapsulatingBitString;
import de.rub.nds.asn1.model.Asn1EncapsulatingOctetString;
import de.rub.nds.asn1.model.Asn1EndOfContent;
import de.rub.nds.asn1.model.Asn1Enumerated;
import de.rub.nds.asn1.model.Asn1Explicit;
import de.rub.nds.asn1.model.Asn1Field;
import de.rub.nds.asn1.model.Asn1Implicit;
import de.rub.nds.asn1.model.Asn1Integer;
import de.rub.nds.asn1.model.Asn1Null;
import de.rub.nds.asn1.model.Asn1ObjectIdentifier;
import de.rub.nds.asn1.model.Asn1PrimitiveBitString;
import de.rub.nds.asn1.model.Asn1PrimitiveIa5String;
import de.rub.nds.asn1.model.Asn1PrimitiveOctetString;
import de.rub.nds.asn1.model.Asn1PrimitivePrintableString;
import de.rub.nds.asn1.model.Asn1PrimitiveT61String;
import de.rub.nds.asn1.model.Asn1PrimitiveUtcTime;
import de.rub.nds.asn1.model.Asn1PrimitiveUtf8String;
import de.rub.nds.asn1.model.Asn1RawBytes;
import de.rub.nds.asn1.model.Asn1RawField;
import de.rub.nds.asn1.model.Asn1Sequence;
import de.rub.nds.asn1.model.Asn1Set;
import de.rub.nds.asn1tool.xmlparser.Asn1XmlContent;
import de.rub.nds.asn1tool.xmlparser.JaxbClassList;
import de.rub.nds.asn1tool.xmlparser.XmlParser;
import de.rub.nds.modifiablevariable.biginteger.BigIntegerAddModification;
import de.rub.nds.modifiablevariable.biginteger.BigIntegerExplicitValueModification;
import de.rub.nds.modifiablevariable.biginteger.BigIntegerInteractiveModification;
import de.rub.nds.modifiablevariable.biginteger.BigIntegerShiftLeftModification;
import de.rub.nds.modifiablevariable.biginteger.BigIntegerShiftRightModification;
import de.rub.nds.modifiablevariable.biginteger.BigIntegerSubtractModification;
import de.rub.nds.modifiablevariable.biginteger.BigIntegerXorModification;
import de.rub.nds.modifiablevariable.biginteger.ModifiableBigInteger;
import de.rub.nds.modifiablevariable.bool.BooleanExplicitValueModification;
import de.rub.nds.modifiablevariable.bool.ModifiableBoolean;
import de.rub.nds.modifiablevariable.bytearray.ByteArrayDeleteModification;
import de.rub.nds.modifiablevariable.bytearray.ByteArrayDuplicateModification;
import de.rub.nds.modifiablevariable.bytearray.ByteArrayExplicitValueModification;
import de.rub.nds.modifiablevariable.bytearray.ByteArrayInsertModification;
import de.rub.nds.modifiablevariable.bytearray.ByteArrayPayloadModification;
import de.rub.nds.modifiablevariable.bytearray.ByteArrayShuffleModification;
import de.rub.nds.modifiablevariable.bytearray.ByteArrayXorModification;
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;
import de.rub.nds.modifiablevariable.integer.IntegerAddModification;
import de.rub.nds.modifiablevariable.integer.IntegerExplicitValueModification;
import de.rub.nds.modifiablevariable.integer.IntegerShiftLeftModification;
import de.rub.nds.modifiablevariable.integer.IntegerShiftRightModification;
import de.rub.nds.modifiablevariable.integer.IntegerSubtractModification;
import de.rub.nds.modifiablevariable.integer.IntegerXorModification;
import de.rub.nds.modifiablevariable.integer.ModifiableInteger;
import de.rub.nds.modifiablevariable.mlong.LongAddModification;
import de.rub.nds.modifiablevariable.mlong.LongExplicitValueModification;
import de.rub.nds.modifiablevariable.mlong.LongSubtractModification;
import de.rub.nds.modifiablevariable.mlong.LongXorModification;
import de.rub.nds.modifiablevariable.mlong.ModifiableLong;
import de.rub.nds.modifiablevariable.singlebyte.ByteAddModification;
import de.rub.nds.modifiablevariable.singlebyte.ByteExplicitValueModification;
import de.rub.nds.modifiablevariable.singlebyte.ByteSubtractModification;
import de.rub.nds.modifiablevariable.singlebyte.ByteXorModification;
import de.rub.nds.modifiablevariable.singlebyte.ModifiableByte;
import de.rub.nds.modifiablevariable.string.ModifiableString;
import de.rub.nds.modifiablevariable.string.StringExplicitValueModification;
import java.io.IOException;
import java.util.List;

public class Asn1Tool {

    public static Class[] getAsn1ToolJaxbClasses() {
        return new Class[] {
            // ModifiableVariables
            ModifiableBigInteger.class, BigIntegerAddModification.class, BigIntegerExplicitValueModification.class,
            BigIntegerInteractiveModification.class, BigIntegerShiftLeftModification.class,
            BigIntegerShiftRightModification.class, BigIntegerSubtractModification.class,
            BigIntegerXorModification.class, ModifiableBoolean.class, BooleanExplicitValueModification.class,
            ModifiableByteArray.class, ByteArrayDeleteModification.class, ByteArrayDuplicateModification.class,
            ByteArrayExplicitValueModification.class, ByteArrayInsertModification.class,
            ByteArrayPayloadModification.class, ByteArrayShuffleModification.class, ByteArrayXorModification.class,
            ModifiableInteger.class, IntegerAddModification.class, IntegerExplicitValueModification.class,
            IntegerShiftLeftModification.class, IntegerShiftRightModification.class, IntegerSubtractModification.class,
            IntegerXorModification.class, ModifiableLong.class, LongAddModification.class,
            LongExplicitValueModification.class, LongSubtractModification.class, LongXorModification.class,
            ModifiableByte.class, ByteAddModification.class, ByteExplicitValueModification.class,
            ByteSubtractModification.class, ByteXorModification.class, ModifiableString.class,
            StringExplicitValueModification.class,
            // ASN.1 classes
            Asn1XmlContent.class, Asn1Boolean.class, Asn1ConstructedBitString.class, Asn1ConstructedIa5String.class,
            Asn1ConstructedOctetString.class, Asn1ConstructedPrintableString.class, Asn1ConstructedT61String.class,
            Asn1ConstructedUtcTime.class, Asn1ConstructedUtf8String.class, Asn1Container.class,
            Asn1EncapsulatingBitString.class, Asn1EncapsulatingOctetString.class, Asn1EndOfContent.class,
            Asn1Enumerated.class, Asn1Explicit.class, Asn1Field.class, Asn1Integer.class, Asn1Null.class,
            Asn1ObjectIdentifier.class, Asn1PrimitiveBitString.class, Asn1PrimitiveIa5String.class,
            Asn1PrimitiveOctetString.class, Asn1PrimitivePrintableString.class, Asn1PrimitiveT61String.class,
            Asn1PrimitiveUtcTime.class, Asn1PrimitiveUtf8String.class, Asn1RawField.class, Asn1Sequence.class,
            Asn1Set.class, Asn1RawBytes.class, Asn1Implicit.class

            // Todo: Support all native ASN.1 types. So far, only the types relevant for X.509 are supported.
        };
    }
}

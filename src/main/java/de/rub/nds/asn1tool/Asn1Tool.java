package de.rub.nds.asn1tool;

import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1.encoder.Asn1Encoder;
import de.rub.nds.asn1.model.*;
import de.rub.nds.asn1tool.filesystem.HexFileWriter;
import de.rub.nds.asn1tool.filesystem.TextFileReader;
import de.rub.nds.asn1tool.xmlparser.Asn1XmlContent;
import de.rub.nds.asn1tool.xmlparser.JaxbClassList;
import de.rub.nds.asn1tool.xmlparser.XmlParser;
import de.rub.nds.modifiablevariable.biginteger.*;
import de.rub.nds.modifiablevariable.bool.BooleanExplicitValueModification;
import de.rub.nds.modifiablevariable.bool.BooleanToogleModification;
import de.rub.nds.modifiablevariable.bool.ModifiableBoolean;
import de.rub.nds.modifiablevariable.bytearray.*;
import de.rub.nds.modifiablevariable.integer.*;
import de.rub.nds.modifiablevariable.mlong.*;
import de.rub.nds.modifiablevariable.singlebyte.*;
import de.rub.nds.modifiablevariable.string.ModifiableString;
import de.rub.nds.modifiablevariable.string.StringExplicitValueModification;

import java.io.IOException;
import java.util.List;

public class Asn1Tool {

    public static void main(final String[] args) {
        try {
            // Define classes for use in a JAXB context
            JaxbClassList.getInstance().addClasses(getAsn1ToolJaxbClasses());

            // Read file specified in args[0]
            if (args.length < 1) {
                throw new RuntimeException("Expected argument 0: Input file name");
            }
            TextFileReader textFileReader = new TextFileReader(args[0]);
            String xmlString = textFileReader.read();

            // Parse XML
            XmlParser xmlParser = new XmlParser(xmlString);
            Asn1XmlContent asn1XmlContent = xmlParser.getAsn1XmlContent();

            // Encode XML
            List<Asn1Encodable> asn1Encodables = asn1XmlContent.getAsn1Encodables();
            Asn1Encoder asn1Encoder = new Asn1Encoder(asn1Encodables);
            byte[] encodedAsn1 = asn1Encoder.encode();

            // Write result to file in args[1]
            if(args.length < 3) {
                throw new RuntimeException("Expected argument 1: Output file directory\nExpected argument 2: Output file name");
            }
            HexFileWriter hexFileWriter = new HexFileWriter(args[1], args[2]);
            hexFileWriter.write(encodedAsn1);
        } catch(IOException e) {
            throw new RuntimeException(e);
        } catch(ClassCastException e) {
            throw new RuntimeException("An unknown object is found within the parsed ASN.1 structure! Did you use an unspecified element?", e);
        }
    }

    public static Class[] getAsn1ToolJaxbClasses() {
        return new Class[] {
                // ModifiableVariables
                ModifiableBigInteger.class,
                BigIntegerAddModification.class,
                BigIntegerExplicitValueModification.class,
                BigIntegerInteractiveModification.class,
                BigIntegerShiftLeftModification.class,
                BigIntegerShiftRightModification.class,
                BigIntegerSubtractModification.class,
                BigIntegerXorModification.class,

                ModifiableBoolean.class,
                BooleanExplicitValueModification.class,

                ModifiableByteArray.class,
                ByteArrayDeleteModification.class,
                ByteArrayDuplicateModification.class,
                ByteArrayExplicitValueModification.class,
                ByteArrayInsertModification.class,
                ByteArrayPayloadModification.class,
                ByteArrayShuffleModification.class,
                ByteArrayXorModification.class,

                ModifiableInteger.class,
                IntegerAddModification.class,
                IntegerExplicitValueModification.class,
                IntegerShiftLeftModification.class,
                IntegerShiftRightModification.class,
                IntegerSubtractModification.class,
                IntegerXorModification.class,

                ModifiableLong.class,
                LongAddModification.class,
                LongExplicitValueModification.class,
                LongSubtractModification.class,
                LongXorModification.class,

                ModifiableByte.class,
                ByteAddModification.class,
                ByteExplicitValueModification.class,
                ByteSubtractModification.class,
                ByteXorModification.class,

                ModifiableString.class,
                StringExplicitValueModification.class,

                // ASN.1 classes
                Asn1XmlContent.class,

                Asn1Boolean.class,
                Asn1ConstructedBitString.class,
                Asn1ConstructedGeneralizedTime.class,
                Asn1ConstructedIa5String.class,
                Asn1ConstructedOctetString.class,
                Asn1ConstructedPrintableString.class,
                Asn1ConstructedT61String.class,
                Asn1ConstructedUtcTime.class,
                Asn1ConstructedUtf8String.class,
                Asn1Container.class,
                Asn1EncapsulatingBitString.class,
                Asn1EncapsulatingOctetString.class,
                Asn1EndOfContent.class,
                Asn1Explicit.class,
                Asn1Field.class,
                Asn1Implicit.class,
                Asn1Integer.class,
                Asn1Null.class,
                Asn1ObjectIdentifier.class,
                Asn1PrimitiveBitString.class,
                Asn1PrimitiveGeneralizedTime.class,
                Asn1PrimitiveIa5String.class,
                Asn1PrimitiveOctetString.class,
                Asn1PrimitivePrintableString.class,
                Asn1PrimitiveT61String.class,
                Asn1PrimitiveUtcTime.class,
                Asn1PrimitiveUtf8String.class,
                Asn1RawField.class,
                Asn1Sequence.class,
                Asn1Set.class,
                Asn1RawBytes.class,
                Asn1Implicit.class

                // Todo: Support all native ASN.1 types. So far, only the types relevant for X.509 are supported.
        };
    }
}

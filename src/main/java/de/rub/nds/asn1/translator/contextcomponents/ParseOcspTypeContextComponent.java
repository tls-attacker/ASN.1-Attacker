package de.rub.nds.asn1.translator.contextcomponents;

import de.rub.nds.asn1.translator.ContextComponent;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.ParseOcspTypesContext;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.*;
import de.rub.nds.asn1.translator.ocspcontextcomponentoptions.*;

public class ParseOcspTypeContextComponent extends ContextComponent {

    private static ContextComponentOption<?>[] contextComponentOptions = new ContextComponentOption<?>[] {
            new Asn1BooleanCCO(),
            new Asn1ConstructedBitStringCCO(ParseOcspTypesContext.NAME),
            new Asn1ConstructedGeneralizedTimeCCO(ParseOcspTypesContext.NAME),
            new Asn1ConstructedIa5StringCCO(ParseOcspTypesContext.NAME),
            new Asn1ConstructedOctetStringCCO(ParseOcspTypesContext.NAME),
            new Asn1ConstructedPrintableStringCCO(ParseOcspTypesContext.NAME),
            new Asn1ConstructedT61StringCCO(ParseOcspTypesContext.NAME),
            new Asn1ConstructedUtcTimeCCO(ParseOcspTypesContext.NAME),
            new Asn1ConstructedUtf8StringCCO(ParseOcspTypesContext.NAME),
            new Asn1EncapsulatingBitStringCCO(ParseOcspTypesContext.NAME),
            new Asn1EncapsulatingOctetStringCCO(ParseOcspTypesContext.NAME),
            new Asn1EndOfContentCCO(),
            new Asn1EnumeratedCCO(),
            new Asn1IntegerCCO(),
            new Asn1NullCCO(),
            new Asn1OcspObjectIdentifierCCO(),
            new Asn1PrimitiveBitStringCCO(),
            new Asn1PrimitiveGeneralizedTimeCCO(),
            new Asn1PrimitiveIa5StringCCO(),
            new Asn1PrimitiveOctetStringCCO(),
            new Asn1PrimitivePrintableStringCCO(),
            new Asn1PrimitiveT61StringCCO(),
            new Asn1PrimitiveUtcTimeCCO(),
            new Asn1PrimitiveUtf8StringCCO(),
            new Asn1SequenceCCO(ParseOcspTypesContext.NAME),
            new Asn1OcspA0ExplicitCCO(ParseOcspTypesContext.NAME),
            new Asn1OcspA1ExplicitCCO(ParseOcspTypesContext.NAME),
            new Asn1OcspA2ExplicitCCO(ParseOcspTypesContext.NAME),
            new Asn1OcspA3ExplicitCCO(ParseOcspTypesContext.NAME),
            new Asn1OcspCertificateGoodNullCCO(),
            new Asn1OcspRevocationUrlContextIA5StringCCO(),
            new Asn1SetCCO(ParseOcspTypesContext.NAME)
    };

    public ParseOcspTypeContextComponent() {
        super("", "", contextComponentOptions, false, true);
    }
}

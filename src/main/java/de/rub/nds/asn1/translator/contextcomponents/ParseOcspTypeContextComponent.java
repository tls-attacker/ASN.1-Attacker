/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.translator.contextcomponents;

import de.rub.nds.asn1.translator.ContextComponent;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.ParseOcspTypesContext;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1BooleanCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1ConstructedBitStringCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1ConstructedGeneralizedTimeCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1ConstructedIa5StringCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1ConstructedOctetStringCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1ConstructedPrintableStringCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1ConstructedT61StringCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1ConstructedUtcTimeCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1ConstructedUtf8StringCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1EncapsulatingBitStringCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1EncapsulatingOctetStringCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1EndOfContentCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1EnumeratedCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1IntegerCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1NullCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1PrimitiveBitStringCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1PrimitiveGeneralizedTimeCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1PrimitiveIa5StringCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1PrimitiveOctetStringCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1PrimitivePrintableStringCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1PrimitiveT61StringCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1PrimitiveUtcTimeCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1PrimitiveUtf8StringCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1SequenceCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1SetCCO;
import de.rub.nds.asn1.translator.ocspcontextcomponentoptions.Asn1OcspA0ExplicitCCO;
import de.rub.nds.asn1.translator.ocspcontextcomponentoptions.Asn1OcspA1ExplicitCCO;
import de.rub.nds.asn1.translator.ocspcontextcomponentoptions.Asn1OcspA2ExplicitCCO;
import de.rub.nds.asn1.translator.ocspcontextcomponentoptions.Asn1OcspA3ExplicitCCO;
import de.rub.nds.asn1.translator.ocspcontextcomponentoptions.Asn1OcspCertificateGoodNullCCO;
import de.rub.nds.asn1.translator.ocspcontextcomponentoptions.Asn1OcspObjectIdentifierCCO;
import de.rub.nds.asn1.translator.ocspcontextcomponentoptions.Asn1OcspRevocationUrlContextIA5StringCCO;

public class ParseOcspTypeContextComponent extends ContextComponent {

    private static ContextComponentOption<?>[] contextComponentOptions = new ContextComponentOption<?>[] {
        new Asn1BooleanCCO(), new Asn1ConstructedBitStringCCO(ParseOcspTypesContext.NAME),
        new Asn1ConstructedGeneralizedTimeCCO(ParseOcspTypesContext.NAME),
        new Asn1ConstructedIa5StringCCO(ParseOcspTypesContext.NAME),
        new Asn1ConstructedOctetStringCCO(ParseOcspTypesContext.NAME),
        new Asn1ConstructedPrintableStringCCO(ParseOcspTypesContext.NAME),
        new Asn1ConstructedT61StringCCO(ParseOcspTypesContext.NAME),
        new Asn1ConstructedUtcTimeCCO(ParseOcspTypesContext.NAME),
        new Asn1ConstructedUtf8StringCCO(ParseOcspTypesContext.NAME),
        new Asn1EncapsulatingBitStringCCO(ParseOcspTypesContext.NAME),
        new Asn1EncapsulatingOctetStringCCO(ParseOcspTypesContext.NAME), new Asn1EndOfContentCCO(),
        new Asn1EnumeratedCCO(), new Asn1IntegerCCO(), new Asn1NullCCO(), new Asn1OcspObjectIdentifierCCO(),
        new Asn1PrimitiveBitStringCCO(), new Asn1PrimitiveGeneralizedTimeCCO(), new Asn1PrimitiveIa5StringCCO(),
        new Asn1PrimitiveOctetStringCCO(), new Asn1PrimitivePrintableStringCCO(), new Asn1PrimitiveT61StringCCO(),
        new Asn1PrimitiveUtcTimeCCO(), new Asn1PrimitiveUtf8StringCCO(),
        new Asn1SequenceCCO(ParseOcspTypesContext.NAME), new Asn1OcspA0ExplicitCCO(ParseOcspTypesContext.NAME),
        new Asn1OcspA1ExplicitCCO(ParseOcspTypesContext.NAME), new Asn1OcspA2ExplicitCCO(ParseOcspTypesContext.NAME),
        new Asn1OcspA3ExplicitCCO(ParseOcspTypesContext.NAME), new Asn1OcspCertificateGoodNullCCO(),
        new Asn1OcspRevocationUrlContextIA5StringCCO(), new Asn1SetCCO(ParseOcspTypesContext.NAME) };

    public ParseOcspTypeContextComponent() {
        super("", "", contextComponentOptions, false, true);
    }
}

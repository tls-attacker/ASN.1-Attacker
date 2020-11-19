/*
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2020 Ruhr University Bochum, Paderborn University,
 * and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.rub.nds.asn1.translator.contextcomponents;

import de.rub.nds.asn1.translator.ContextComponent;
import de.rub.nds.asn1.translator.ContextComponentOption;
import de.rub.nds.asn1.translator.ParseNativeTypesContext;
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
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1ExplicitCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1IntegerCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1NullCCO;
import de.rub.nds.asn1.translator.defaultcontextcomponentoptions.Asn1ObjectIdentifierCCO;
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

public class ParseNativeTypeContextComponent extends ContextComponent {

    private static ContextComponentOption<?>[] contextComponentOptions = new ContextComponentOption<?>[] {
        new Asn1BooleanCCO(), new Asn1ConstructedBitStringCCO(ParseNativeTypesContext.NAME),
        new Asn1ConstructedGeneralizedTimeCCO(ParseNativeTypesContext.NAME),
        new Asn1ConstructedIa5StringCCO(ParseNativeTypesContext.NAME),
        new Asn1ConstructedOctetStringCCO(ParseNativeTypesContext.NAME),
        new Asn1ConstructedPrintableStringCCO(ParseNativeTypesContext.NAME),
        new Asn1ConstructedT61StringCCO(ParseNativeTypesContext.NAME),
        new Asn1ConstructedUtcTimeCCO(ParseNativeTypesContext.NAME),
        new Asn1ConstructedUtf8StringCCO(ParseNativeTypesContext.NAME),
        new Asn1EncapsulatingBitStringCCO(ParseNativeTypesContext.NAME),
        new Asn1EncapsulatingOctetStringCCO(ParseNativeTypesContext.NAME), new Asn1EndOfContentCCO(),
        new Asn1EnumeratedCCO(), new Asn1ExplicitCCO(ParseNativeTypesContext.NAME), new Asn1IntegerCCO(),
        new Asn1NullCCO(), new Asn1ObjectIdentifierCCO(), new Asn1PrimitiveBitStringCCO(),
        new Asn1PrimitiveGeneralizedTimeCCO(), new Asn1PrimitiveIa5StringCCO(), new Asn1PrimitiveOctetStringCCO(),
        new Asn1PrimitivePrintableStringCCO(), new Asn1PrimitiveT61StringCCO(), new Asn1PrimitiveUtcTimeCCO(),
        new Asn1PrimitiveUtf8StringCCO(), new Asn1SequenceCCO(ParseNativeTypesContext.NAME),
        new Asn1SetCCO(ParseNativeTypesContext.NAME) };

    public ParseNativeTypeContextComponent() {
        super("", "", contextComponentOptions, false, true);
    }
}

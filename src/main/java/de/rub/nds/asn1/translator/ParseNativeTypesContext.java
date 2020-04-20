package de.rub.nds.asn1.translator;

import de.rub.nds.asn1.translator.contextcomponents.ParseNativeTypeContextComponent;

public class ParseNativeTypesContext extends Context {

    public static String NAME = "ParseNativeTypesContext";

    private static final ContextComponent[] contextComponents = new ContextComponent[] {
        new ParseNativeTypeContextComponent()
    };

    public ParseNativeTypesContext() {
        super(contextComponents);
    }

    @Override
    public String getName() {
        return NAME;
    }
}

package de.rub.nds.asn1.translator;

import de.rub.nds.asn1.translator.contextcomponents.ParseOcspTypeContextComponent;

public class ParseOcspTypesContext extends Context {

    public static String NAME = "ParseOcspTypesContext";

    private static final ContextComponent[] contextComponents = new ContextComponent[] {
        new ParseOcspTypeContextComponent()
    };

    public ParseOcspTypesContext() {
        super(contextComponents);
    }

    @Override
    public String getName() {
        return NAME;
    }
}

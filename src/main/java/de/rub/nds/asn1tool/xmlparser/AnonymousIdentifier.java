package de.rub.nds.asn1tool.xmlparser;

public class AnonymousIdentifier {

    private static int anonymousIdentifierNumber = 1;

    public static String createAnonymousIdentifier() {
        String result = "_anonymous" + anonymousIdentifierNumber;
        anonymousIdentifierNumber++;
        return result;
    }
}

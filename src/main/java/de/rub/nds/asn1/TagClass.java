package de.rub.nds.asn1;

public enum TagClass {
    UNIVERSAL(0, "universal"),
    APPLICATiON(1, "application"),
    CONTEXT_SPECIFIC(2, "context-specific"),
    PRIVATE(3, "private");

    private final int intValue;

    private final String stringValue;

    TagClass(int intValue, String stringValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
    }

    public int getIntValue() {
        return this.intValue;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public static TagClass fromIntValue(int intValue) {
        TagClass result = null;
        for(TagClass currentTagClass : TagClass.values()) {
            if(currentTagClass.getIntValue() == intValue) {
                result = currentTagClass;
                break;
            }
        }
        return result;
    }

    public static TagClass fromStringValue(String stringValue) {
        TagClass result = null;
        for(TagClass currentTagClass : TagClass.values()) {
            if(currentTagClass.getStringValue().equalsIgnoreCase(stringValue)) {
                result = currentTagClass;
                break;
            }
        }
        return result;
    }

    public static TagClass fromIdentifierByte(byte identifierByte) {
        return fromIntValue((identifierByte >> 6) & 0x03);
    }
}

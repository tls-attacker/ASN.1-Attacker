package de.rub.nds.asn1.parser;

public class ParserException extends Exception {

    public ParserException(Throwable e) {
        super(e);
    }

    public ParserException(String message) {
        super(message);
    }
}

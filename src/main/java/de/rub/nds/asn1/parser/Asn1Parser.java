package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1.parser.contentunpackers.ContentUnpacker;
import de.rub.nds.asn1.parser.contentunpackers.ContentUnpackerRegister;
import de.rub.nds.asn1.translator.Asn1Translator;
import de.rub.nds.util.ByteArrayBuffer;
import de.rub.nds.util.ByteArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class Asn1Parser {

    private static final Logger LOGGER = LogManager.getLogger();

    private final ByteArrayBuffer byteArrayBuffer;

    private final boolean isStrictMode;

    public Asn1Parser(final byte[] bytes, final boolean strictMode) {
        this.byteArrayBuffer = new ByteArrayBuffer(bytes);
        this.isStrictMode = strictMode;
    }

    public List<Asn1Encodable> parse(final String contextName) throws ParserException {
        List<IntermediateAsn1Field> intermediateAsn1Fields = this.parseIntermediateFields();
        return this.translateIntermediateFields(contextName, intermediateAsn1Fields);
    }

    public List<IntermediateAsn1Field> parseIntermediateFields() throws ParserException {
        List<IntermediateAsn1Field> intermediateAsn1Fields = new LinkedList<>();
        while(this.byteArrayBuffer.getNumberOfRemainingBytes() > 0) {
            IntermediateAsn1Field intermediateAsn1Field = this.parseAsn1Field();
            this.parseChildren(intermediateAsn1Field);
            intermediateAsn1Fields.add(intermediateAsn1Field);
        }
        return intermediateAsn1Fields;
    }

    private IntermediateAsn1Field parseAsn1Field() throws ParserException {
        try {
            int tagClass = this.parseTagClass();
            boolean tagConstructed = this.parseTagConstructed();
            int tagNumber = this.parseTagNumber();
            BigInteger length = this.parseLength();
            byte[] content = this.parseContent(length);
            return new IntermediateAsn1Field(tagClass, tagConstructed, tagNumber, length, content);
        } catch(RuntimeException e) {
            throw new ParserException(e);
        }
    }

    private int parseTagClass() {
        return ((this.byteArrayBuffer.peekByte() >> 6) & 0x03);
    }

    private boolean parseTagConstructed() {
        return ((this.byteArrayBuffer.peekByte() >> 5) & 0x01) != 0;
    }

    private int parseTagNumber() {
        int tagNumber = this.byteArrayBuffer.readByte() & 0x1F;
        if(tagNumber == 0x1F) {
            tagNumber = this.parseLongTagNumber();
        }
        return tagNumber;
    }

    private int parseLongTagNumber() {
        int tagNumber = 0;
        byte nextByte;
        do {
            nextByte = this.byteArrayBuffer.readByte();
            tagNumber = (tagNumber << 7) | (nextByte & 0x7F);
        } while((nextByte & 0x80) > 0);
        return tagNumber;
    }

    private BigInteger parseLength() throws ParserException {
        BigInteger length = BigInteger.ZERO;
        byte lengthByte = this.byteArrayBuffer.readByte();
        if(lengthByte == 0x80) {
            throw new ParserException("Indefinite lengths are currently not supported!");
        }
        if(lengthByte == 0xFF) {
            throw new ParserException("Reserved length value!");
        }
        if((lengthByte & 0xFF) < 128) {
            length = BigInteger.valueOf(lengthByte & 0xFF);
        }
        else {
            int numberOfLengthBytes = (lengthByte & 0x7F);
            for(int i = 0; i < numberOfLengthBytes; i++) {
                length = length.shiftLeft(8);
                length = length.or(BigInteger.valueOf(this.byteArrayBuffer.readByte() & 0xFF));
            }
        }
        return length;
    }

    private byte[] parseContent(BigInteger length) {
        byte[] content = new byte[0];
        while(length.compareTo(BigInteger.ZERO) > 0) {
            int bytesToRead = 65536;
            if(length.compareTo(BigInteger.valueOf(bytesToRead)) < 0) {
                bytesToRead = length.intValue();
            }
            content = ByteArrayUtils.merge(content, this.byteArrayBuffer.readBytes(bytesToRead));
            length = length.subtract(BigInteger.valueOf(bytesToRead));
        }
        return content;
    }

    private List<IntermediateAsn1Field> parseChildren(final IntermediateAsn1Field intermediateAsn1Field) throws ParserException {
        List<IntermediateAsn1Field> children = new LinkedList<>();
        List<ContentUnpacker> contentUnpackers = ContentUnpackerRegister.getInstance().getContentUnpackers();
        for (ContentUnpacker contentUnpacker : contentUnpackers) {
            try {
                byte[] unpacked = contentUnpacker.unpack(intermediateAsn1Field.getContent());
                Asn1Parser childParser = new Asn1Parser(unpacked, this.isStrictMode);
                children = childParser.parseIntermediateFields();
                intermediateAsn1Field.setChildren(children);
                break; // No break is executed if an exception is thrown, e.g. because unpacking is not successful
            } catch (Throwable e) {
                LOGGER.warn(e);
            }
        }
        return children;
    }

    private List<Asn1Encodable> translateIntermediateFields(final String contextName, List<IntermediateAsn1Field> intermediateAsn1Fields) {
        Asn1Translator asn1Translator = new Asn1Translator(contextName, intermediateAsn1Fields, this.isStrictMode);
        return asn1Translator.translate();
    }
}

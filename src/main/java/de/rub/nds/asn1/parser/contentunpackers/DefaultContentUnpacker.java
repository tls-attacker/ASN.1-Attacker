package de.rub.nds.asn1.parser.contentunpackers;

public class DefaultContentUnpacker extends ContentUnpacker {

    static {
        ContentUnpackerRegister.getInstance().registerContentUnpacker(new DefaultContentUnpacker());
    }

    @Override
    public byte[] unpack(final byte[] content) {
        return content;
    }
}

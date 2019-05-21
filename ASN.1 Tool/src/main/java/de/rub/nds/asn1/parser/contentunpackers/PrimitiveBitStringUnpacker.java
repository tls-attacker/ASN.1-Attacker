package de.rub.nds.asn1.parser.contentunpackers;

public class PrimitiveBitStringUnpacker extends ContentUnpacker {

    static {
        ContentUnpackerRegister.getInstance().registerContentUnpacker(new DefaultContentUnpacker());
    }

    @Override
    public byte[] unpack(final byte[] content) {
        byte[] unpacked = new byte[0];
        if(content.length > 1) {
            unpacked = new byte[content.length - 1];
            System.arraycopy(content, 1, unpacked, 0, unpacked.length);
        }
        return unpacked;
    }
}

package de.rub.nds.asn1.parser.contentunpackers;

public abstract class ContentUnpacker {

    public abstract byte[] unpack(final byte[] content);
}

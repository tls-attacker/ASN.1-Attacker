package de.rub.nds.asn1.encoder.typeprocessors;

import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1.encoder.encodingoptions.Asn1EncodingOptions;

public class DefaultAsn1TypeProcessor extends Asn1TypeProcessor {

    private final Asn1Encodable asn1Encodable;

    public DefaultAsn1TypeProcessor(final Asn1EncodingOptions asn1EncodingOptions, final Asn1Encodable asn1Encodable) {
        super(asn1EncodingOptions, asn1Encodable);
        this.asn1Encodable = asn1Encodable;
    }

    @Override
    public byte[] encode() {
        return this.asn1Encodable.getSerializer().serialize();
    }
}

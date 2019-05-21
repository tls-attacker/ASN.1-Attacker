package de.rub.nds.asn1.encoder.typeprocessors;

import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1.encoder.encodingoptions.Asn1EncodingOptions;

public abstract class Asn1TypeProcessor {

    private final Asn1EncodingOptions asn1EncodingOptions;

    protected Asn1TypeProcessor(final Asn1EncodingOptions asn1EncodingOptions, final Asn1Encodable asn1Encodable) {
        this.asn1EncodingOptions = asn1EncodingOptions;
    }

    public Asn1EncodingOptions getAsn1EncodingOptions() {
        return asn1EncodingOptions;
    }

    public void onBeforeChildEncode() {

    }

    public abstract byte[] encode();
}

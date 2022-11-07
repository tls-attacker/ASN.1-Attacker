package de.rub.nds.asn1.encoder;

import de.rub.nds.asn1.encoder.typeprocessors.Asn1TypeProcessor;

public class TypeRegistration {

    public final String type;

    public final Class<? extends Asn1TypeProcessor> typeEncoderClass;

    // Future todo: Support specifying a context
    public TypeRegistration(final String type, final Class<? extends Asn1TypeProcessor> typeEncoderClass) {
        this.type = type;
        this.typeEncoderClass = typeEncoderClass;
    }
}

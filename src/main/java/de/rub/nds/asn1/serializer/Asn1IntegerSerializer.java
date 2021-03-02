package de.rub.nds.asn1.serializer;

import de.rub.nds.asn1.model.Asn1Integer;

public class Asn1IntegerSerializer extends Asn1FieldSerializer {

    private final Asn1Integer asn1Integer;

    public Asn1IntegerSerializer(final Asn1Integer asn1Integer) {
        super(asn1Integer);
        this.asn1Integer = asn1Integer;
    }

    @Override
    public void updateLayers() {
        this.encodeInteger();
        super.updateLayers();
    }

    private void encodeInteger() {
        byte[] content = this.asn1Integer.getValue().toByteArray();
        this.asn1Integer.setContent(content);
    }
}

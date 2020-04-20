package de.rub.nds.asn1.serializer;

import de.rub.nds.asn1.model.Asn1PrimitiveOctetString;

public class Asn1PrimitiveOctetStringSerializer extends Asn1FieldSerializer {

    private final Asn1PrimitiveOctetString asn1PrimitiveOctetString;

    public Asn1PrimitiveOctetStringSerializer(Asn1PrimitiveOctetString asn1PrimitiveOctetString) {
        super(asn1PrimitiveOctetString);
        this.asn1PrimitiveOctetString = asn1PrimitiveOctetString;
    }

    @Override
    public void updateLayers() {
        this.encodePrimitiveOctetString();
        super.updateLayers();
    }

    private void encodePrimitiveOctetString() {
        byte[] content = this.asn1PrimitiveOctetString.getValue();
        this.asn1PrimitiveOctetString.setContent(content);
    }
}

package de.rub.nds.asn1.serializer;


import de.rub.nds.asn1.model.Asn1PrimitiveUtcTime;

public class Asn1PrimitiveUtcTimeSerializer extends Asn1FieldSerializer {

    private final Asn1PrimitiveUtcTime asn1PrimitiveUtcTime;

    public Asn1PrimitiveUtcTimeSerializer(Asn1PrimitiveUtcTime asn1PrimitiveUtcTime) {
        super(asn1PrimitiveUtcTime);
        this.asn1PrimitiveUtcTime = asn1PrimitiveUtcTime;
    }

    @Override
    public void updateLayers() {
        this.encodePrimitiveUtcTime();
        super.updateLayers();
    }

    private void encodePrimitiveUtcTime() {
        byte[] content = this.asn1PrimitiveUtcTime.getValue().getBytes();
        this.asn1PrimitiveUtcTime.setContent(content);
    }
}

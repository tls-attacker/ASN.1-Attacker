package de.rub.nds.asn1.serializer;

import de.rub.nds.asn1.model.Asn1PrimitiveIa5String;

public class Asn1PrimitiveIa5StringSerializer extends Asn1FieldSerializer {

    private final Asn1PrimitiveIa5String asn1PrimitiveIa5String;

    public Asn1PrimitiveIa5StringSerializer(Asn1PrimitiveIa5String asn1PrimitiveIa5String) {
        super(asn1PrimitiveIa5String);
        this.asn1PrimitiveIa5String = asn1PrimitiveIa5String;
    }

    @Override
    public void updateLayers() {
        this.encodePrimitiveIa5String();
        super.updateLayers();
    }

    private void encodePrimitiveIa5String() {
        byte[] content = this.asn1PrimitiveIa5String.getValue().getBytes();
        this.asn1PrimitiveIa5String.setContent(content);
    }
}

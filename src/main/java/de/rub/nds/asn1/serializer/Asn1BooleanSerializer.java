package de.rub.nds.asn1.serializer;

import de.rub.nds.asn1.model.Asn1Boolean;

public class Asn1BooleanSerializer extends Asn1FieldSerializer {

    private final Asn1Boolean asn1Boolean;

    public Asn1BooleanSerializer(final Asn1Boolean asn1Boolean) {
        super(asn1Boolean);
        this.asn1Boolean = asn1Boolean;
    }

    @Override
    public void updateLayers() {
        this.encodeBoolean();
        super.updateLayers();
    }

    private void encodeBoolean() {
        byte[] content = new byte[] { 0 };
        if(this.asn1Boolean.getValue()) {
            content[0] = (byte) 0xFF;
        }
        this.asn1Boolean.setContent(content);
    }
}

package de.rub.nds.asn1.serializer;

import de.rub.nds.asn1.model.Asn1RawField;
import de.rub.nds.util.ByteArrayUtils;

public class Asn1RawFieldSerializer extends Asn1Serializer {

    private final Asn1RawField rawField;

    public Asn1RawFieldSerializer(final Asn1RawField rawField) {
        this.rawField = rawField;
    }

    @Override
    public void updateLayers() {
        // Asn1RawField is bottom layer, hence there are no lower layers to write into.
    }

    @Override
    public final byte[] serialize() {
        this.updateLayers();
        return ByteArrayUtils.merge(
                this.rawField.getIdentifierOctets().getValue(),
                this.rawField.getLengthOctets().getValue(),
                this.rawField.getContentOctets().getValue()
        );
    }
}

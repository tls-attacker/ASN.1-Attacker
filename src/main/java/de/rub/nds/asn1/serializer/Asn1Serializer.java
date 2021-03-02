package de.rub.nds.asn1.serializer;

public abstract class Asn1Serializer {

    public abstract void updateLayers();

    public abstract byte[] serialize();
}

package de.rub.nds.asn1.model;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.TagNumber;
import de.rub.nds.asn1.preparator.GenericAsn1ContainerPreparator;
import de.rub.nds.asn1.preparator.Preparator;

public class Asn1Explicit extends Asn1Container {

    private Asn1Encodable child;

    public Asn1Explicit(String identifier, Asn1Encodable child) {
        super(identifier, TagClass.CONTEXT_SPECIFIC, TagConstructed.CONSTRUCTED, TagNumber.EXPLICIT);
        this.child = child;
        addChild(child);
    }

    @Override
    public Preparator getGenericPreparator() {
        return new GenericAsn1ContainerPreparator(this);
    }

    public Asn1Encodable getChild() {
        return child;
    }

}

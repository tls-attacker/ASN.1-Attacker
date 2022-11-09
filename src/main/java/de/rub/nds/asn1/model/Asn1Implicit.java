/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.serializer.Asn1ImplicitSerializer;
import de.rub.nds.modifiablevariable.integer.ModifiableInteger;
import jakarta.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Asn1Implicit extends Asn1Container {

    public static final int TAG_CLASS = TagClass.CONTEXT_SPECIFIC.getIntValue();

    public static final boolean IS_CONSTRUCTED = true;

    @XmlElement(name = "offset")
    private ModifiableInteger offset;

    public Asn1Implicit() {
    }

    public ModifiableInteger getOffset() {
        return offset;
    }

    public void setOffset(ModifiableInteger offset) {
        this.offset = offset;
    }

    @Override
    public Asn1ImplicitSerializer getSerializer() {
        return new Asn1ImplicitSerializer(this);
    }
}

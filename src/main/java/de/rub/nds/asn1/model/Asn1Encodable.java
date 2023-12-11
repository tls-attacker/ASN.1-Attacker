/*
 * ASN.1-Attacker - A Library for Arbitrary ASN.1 Structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;

public interface Asn1Encodable {

    String getIdentifier();

    void setIdentifier(final String identifier);

    boolean isOptional();

    boolean matchesHeader(TagClass classType, Boolean constructed, Integer tagNumber);

    ModifiableByteArray getTagOctets();

    ModifiableByteArray getLengthOctets();

    ModifiableByteArray getContent();
}

/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.serializer.*;
import de.rub.nds.asn1.model.Asn1Implicit;
import de.rub.nds.modifiablevariable.bytearray.ByteArrayExplicitValueModification;
import java.util.Arrays;

public class Asn1ImplicitParser extends Asn1FieldParser {

    private final Asn1Implicit asn1Implicit;

    public Asn1ImplicitParser(final Asn1Implicit asn1Implicit) {
        super(asn1Implicit);
        this.asn1Implicit = asn1Implicit;
    }

    @Override
    public void updateLayers() {
        this.encodeImplicit();
        super.updateLayers();
    }

    private void encodeImplicit() {
        int offset = this.asn1Implicit.getOffset();
        this.asn1Implicit.getTagNumber().setOriginalValue(offset);
        byte[] content = this.asn1Implicit.getEncodedChildren();

        // Inherit the constructed flag from the child
        boolean constructed = ((content[0] & 0x20) >> 5) == 1;
        this.asn1Implicit.setTagConstructedModification(constructed);

        int tagNumber = ((content[0]) & 0x1f);
        int i = 1;
        if (tagNumber >= 31) {
            // Figure out how many bytes the original tag number was comprised of
            while ((content[i] & 0x80) == 1) {
                i = i + 1;
            }
        }

        // Figure out how many octets the length is comprised of
        int j = i;
        while ((content[j] & 0x80) == 1) {
            j = j + 1;
        }
        // Extract the length
        byte[] length = Arrays.copyOfRange(content, i, j - i + 1);

        // Set the content to the encoded child minus the tag bytes
        this.asn1Implicit.getLengthOctets().setModification(new ByteArrayExplicitValueModification(length));
        this.asn1Implicit.setContent(Arrays.copyOfRange(content, j, content.length));
    }
}

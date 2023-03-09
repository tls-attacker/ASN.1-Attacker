/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.model.Asn1Encodable;
import de.rub.nds.asn1.model.Asn1Sequence;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;

public abstract class Asn1SequencePreparator<Chooser extends AbstractChooser>
        extends Asn1FieldPreparator<Chooser, Asn1Sequence<Chooser>> {

    public Asn1SequencePreparator(Chooser chooser, Asn1Sequence<Chooser> field) {
        super(chooser, field);
    }

    @Override
    protected byte[] encodeContent() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    protected byte[] encodedChildren(Collection<Asn1Encodable<Chooser>> childrenCollection) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        for (Asn1Encodable<Chooser> child : childrenCollection) {
            byte[] serialize = child.getSerializer().serialize();
            try {
                stream.write(serialize);
            } catch (IOException ex) {
                throw new RuntimeException("Could not write children content");
            }
        }
        return stream.toByteArray();
    }
}

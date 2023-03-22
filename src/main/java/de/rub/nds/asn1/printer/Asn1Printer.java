/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.printer;

import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.model.Asn1Any;
import de.rub.nds.asn1.model.Asn1Container;
import de.rub.nds.asn1.model.Asn1Encodable;

public class Asn1Printer<Chooser extends AbstractChooser> {

    public Asn1Printer() {}

    public String print(StringBuilder builder, Asn1Encodable<?> encodable, int depth) {
        if (encodable instanceof Asn1Any) {
            print(builder, ((Asn1Any<?>) encodable).getInstantiation(), depth);
            return builder.toString();
        } else {
            addWhiteSpace(builder, depth);
            builder.append(encodable.getIdentifier());
            builder.append("  ");
            builder.append(encodable.getClass().getSimpleName());
            builder.append("\n");
            if (encodable instanceof Asn1Container) {
                for (Object child : ((Asn1Container<?>) encodable).getChildren()) {
                    print(builder, (Asn1Encodable<?>) child, depth + 1);
                }
            }
            return builder.toString();
        }
    }

    private void addWhiteSpace(StringBuilder builder, int depth) {
        for (int i = 0; i < depth; i++) {
            builder.append("  ");
        }
    }
}

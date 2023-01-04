/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.printer;

import de.rub.nds.asn1.model.Asn1Container;
import de.rub.nds.asn1.model.Asn1Encodable;

public class Asn1Printer {

    private Asn1Printer() {}

    public static String print(StringBuilder builder, Asn1Encodable encodable, int depth) {
        addWhiteSpace(builder, depth);
        builder.append(encodable.getIdentifier());
        builder.append("  ");
        builder.append(encodable.getClass().getSimpleName());
        builder.append("\n");
        if (encodable instanceof Asn1Container) {
            for (Asn1Encodable child : ((Asn1Container) encodable).getChildren()) {
                print(builder, child, depth + 1);
            }
        }
        return builder.toString();
    }

    private static void addWhiteSpace(StringBuilder builder, int depth) {
        for (int i = 0; i < depth; i++) {
            builder.append("  ");
        }
    }
}

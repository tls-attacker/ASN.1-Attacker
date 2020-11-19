/*
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2020 Ruhr University Bochum, Paderborn University,
 * and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package de.rub.nds.asn1.translator.fieldtranslators;

import de.rub.nds.asn1.model.Asn1ObjectIdentifier;
import de.rub.nds.asn1.parser.IntermediateAsn1Field;

public final class Asn1ObjectIdentifierFT extends Asn1FieldFT<Asn1ObjectIdentifier> {

    private final IntermediateAsn1Field intermediateAsn1Field;

    private final Asn1ObjectIdentifier asn1ObjectIdentifier;

    public Asn1ObjectIdentifierFT(final IntermediateAsn1Field intermediateAsn1Field) {
        this(intermediateAsn1Field, new Asn1ObjectIdentifier());
    }

    protected Asn1ObjectIdentifierFT(final IntermediateAsn1Field intermediateAsn1Field,
        final Asn1ObjectIdentifier asn1ObjectIdentifier) {
        super(intermediateAsn1Field, asn1ObjectIdentifier);
        this.intermediateAsn1Field = intermediateAsn1Field;
        this.asn1ObjectIdentifier = asn1ObjectIdentifier;
    }

    @Override
    public Asn1ObjectIdentifier translate(final String identifier, final String type) {
        String value = this.decodeValue(this.intermediateAsn1Field.getContent());
        this.asn1ObjectIdentifier.setValue(value);
        return super.translate(identifier, type);
    }

    private String decodeValue(final byte[] content) {
        String result = "";
        if (content.length > 0) {
            result += (content[0] / 40) + "." + (content[0] % 40);
            int contentPos = 1;
            while (contentPos < content.length) {
                long oidPart = 0;
                boolean partFinished;
                do {
                    oidPart = (oidPart << 7) | (content[contentPos] & 0x7F);
                    partFinished = (content[contentPos] & 0x80) == 0;
                    contentPos++;
                } while (contentPos < content.length && partFinished == false);
                result += "." + oidPart;
            }
        }
        return result;
    }
}

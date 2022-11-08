/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.serializer;

import de.rub.nds.asn1.model.Asn1EncapsulatingBitString;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Asn1EncapsulatingBitStringSerializer extends Asn1FieldSerializer {

    private final Asn1EncapsulatingBitString asn1EncapsulatingBitString;

    public Asn1EncapsulatingBitStringSerializer(Asn1EncapsulatingBitString asn1EncapsulatingBitString) {
        super(asn1EncapsulatingBitString);
        this.asn1EncapsulatingBitString = asn1EncapsulatingBitString;
    }

    @Override
    public void updateLayers() {
        this.encodeEncapsulatingBitString();
        super.updateLayers();
    }

    private void encodeEncapsulatingBitString() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(new byte[1]); //TODO I think this is the number of unused bits
            //TODO Encode Childrend
            outputStream.write(this.asn1EncapsulatingBitString.getEncodedChildren().getValue());
            this.asn1EncapsulatingBitString.setContent(outputStream.toByteArray());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

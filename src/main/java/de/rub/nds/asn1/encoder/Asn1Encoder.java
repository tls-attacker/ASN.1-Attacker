/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.encoder;

import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1.encoder.encodingoptions.Asn1EncodingOptions;
import de.rub.nds.asn1.encoder.encodingoptions.DefaultAsn1EncodingOptions;
import de.rub.nds.asn1.encoder.typeprocessors.Asn1TypeProcessor;
import de.rub.nds.asn1.model.Asn1Container;
import de.rub.nds.util.ByteArrayUtils;
import java.util.LinkedList;
import java.util.List;

public class Asn1Encoder {

    private final Asn1EncodingOptions asn1EncodingOptions;

    private final List<Asn1Encodable> encodables;

    /**
     * Constructor for an Asn1Encoder.
     * 
     * @param encodables
     */
    public Asn1Encoder(final List<Asn1Encodable> encodables) {
        this(new DefaultAsn1EncodingOptions(), encodables);
    }

    /**
     * Constructor for an Asn1Encoder.
     * 
     * @param asn1EncodingOptions
     * @param encodables
     */
    public Asn1Encoder(final Asn1EncodingOptions asn1EncodingOptions, final List<Asn1Encodable> encodables) {
        this.asn1EncodingOptions = asn1EncodingOptions;
        this.encodables = encodables;
    }

    /**
     * Constructor for an Asn1Encoder.
     * 
     * @param firstAsn1Encodable
     * @param asn1Encodables
     */
    public Asn1Encoder(final Asn1Encodable firstAsn1Encodable, final Asn1Encodable... asn1Encodables) {
        this(new DefaultAsn1EncodingOptions(), firstAsn1Encodable, asn1Encodables);
    }

    /**
     * Constructor for an Asn1Encoder.
     * 
     * @param asn1EncodingOptions
     * @param firstAsn1Encodable
     * @param asn1Encodables
     */
    public Asn1Encoder(final Asn1EncodingOptions asn1EncodingOptions, final Asn1Encodable firstAsn1Encodable,
        final Asn1Encodable... asn1Encodables) {
        this.asn1EncodingOptions = asn1EncodingOptions;
        this.encodables = new LinkedList<>();
        this.encodables.add(firstAsn1Encodable);
        for (Asn1Encodable asn1Encodable : asn1Encodables) {
            this.encodables.add(asn1Encodable);
        }
    }

    /**
     * Encodes the Asn1Encodables specified in the constructor and returns its serialized representations.
     * 
     * @return
     */
    public byte[] encode() {
        byte[] encoded = new byte[0];
        for (Asn1Encodable asn1Encodable : this.encodables) {
            encoded = ByteArrayUtils.merge(encoded, this.encodeSingleAsn1Encodable(asn1Encodable));
        }
        return encoded;
    }

    /**
     * Encodes a single Asn1Encodable and returns its serialized representation.
     * 
     * @param  asn1Encodable
     * @return
     */
    protected byte[] encodeSingleAsn1Encodable(final Asn1Encodable asn1Encodable) {
        byte[] encoded;
        Asn1TypeRegister typeRegister = Asn1TypeRegister.getInstance();
        Asn1TypeProcessor asn1TypeProcessor = typeRegister.createTypeProcessor(this.asn1EncodingOptions, asn1Encodable);
        asn1TypeProcessor.onBeforeChildEncode();
        if (asn1Encodable instanceof Asn1Container) {
            this.encodeChildren((Asn1Container) asn1Encodable);
        }
        encoded = asn1TypeProcessor.encode();
        return encoded;
    }

    /**
     * Encodes the children of the given Asn1Container and stores the encoding in the Asn1Container.
     * 
     * @param asn1Container
     */
    protected void encodeChildren(final Asn1Container asn1Container) {
        Asn1Encoder childEncoder = this.invokeChildEncoder(asn1Container.getChildren());
        byte[] childEncoding = childEncoder.encode();
        asn1Container.setEncodedChildren(childEncoding);
    }

    /**
     * Invokes a new Asn1Encoder for encoding children.
     * 
     * @param  children
     * @return
     */
    protected Asn1Encoder invokeChildEncoder(List<Asn1Encodable> children) {
        return new Asn1Encoder(this.asn1EncodingOptions, children);
    }
}

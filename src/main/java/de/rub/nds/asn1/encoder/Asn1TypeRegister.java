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
import de.rub.nds.asn1.encoder.typeprocessors.Asn1TypeProcessor;
import de.rub.nds.asn1.encoder.typeprocessors.DefaultAsn1TypeProcessor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Asn1TypeRegister {

    private static Asn1TypeRegister instance = null;

    private final Map<String, TypeRegistration> registrations = new HashMap<>();

    private Class<? extends Asn1TypeProcessor> defaultTypeProcessorClass = DefaultAsn1TypeProcessor.class;

    /**
     * Private constructor for singleton.
     */
    private Asn1TypeRegister() {

    }

    /**
     * Singleton getInstance() method.
     *
     * @return An instance of Asn1AnyTypeRegister.
     */
    public static Asn1TypeRegister getInstance() {
        if (instance == null) {
            instance = new Asn1TypeRegister();
        }
        return instance;
    }

    /**
     * @return Returns the default Asn1TypeProcessor.
     */
    public Class<? extends Asn1TypeProcessor> getDefaultTypeProcessorClass() {
        return defaultTypeProcessorClass;
    }

    /**
     * Sets the default Asn1TypeProcessor.
     *
     * @param defaultTypeEncoderClass The new default Asn1TypeProcessor.
     */
    public void setDefaultTypeProcessorClass(Class<? extends Asn1TypeProcessor> defaultTypeEncoderClass) {
        this.defaultTypeProcessorClass = defaultTypeEncoderClass;
    }

    /**
     * Prevent cloning of this object.
     *
     * @return This.
     */
    @Override
    public Object clone() {
        return this;
    }

    /**
     * Registers a new type with the given typeEncoder.
     *
     * @param type
     * @param typeEncoder
     */
    public void register(final String type, final Class<? extends Asn1TypeProcessor> typeEncoder) {
        if (type != null & type.isEmpty() == false && typeEncoder != null) {
            String lowerType = type.toLowerCase();
            this.registrations.put(lowerType, new TypeRegistration(lowerType, typeEncoder));
        }
    }

    /**
     * Creates an instance of Asn1TypeProcessor for the given Asn1Encodable.
     *
     * @param asn1Encodable
     * @return
     */
    public Asn1TypeProcessor createTypeProcessor(final Asn1Encodable asn1Encodable) {
        String lowerType = asn1Encodable.getType().toLowerCase();
        Class<? extends Asn1TypeProcessor> typeEncoderClass = this.defaultTypeProcessorClass;
        if (this.registrations.containsKey(lowerType)) {
            typeEncoderClass = this.registrations.get(lowerType).typeEncoderClass;
        }
        return this.invokeTypeProcessor(typeEncoderClass, asn1Encodable);
    }

    private Asn1TypeProcessor invokeTypeProcessor(final Class<? extends Asn1TypeProcessor> typeEncoderClass,
            final Asn1Encodable asn1Encodable) {
        Asn1TypeProcessor asn1TypeProcessor = null;
        try {
            Constructor<? extends Asn1TypeProcessor> typeEncoderConstructor
                    = typeEncoderClass.getDeclaredConstructor(Asn1Encodable.class);
            try {
                asn1TypeProcessor = typeEncoderConstructor.newInstance(asn1Encodable);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return asn1TypeProcessor;
    }

}

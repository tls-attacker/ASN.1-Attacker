/**
 * ASN.1-Attacker - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.model;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.TagNumber;
import de.rub.nds.asn1.serializer.Asn1FieldSerializer;
import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.biginteger.ModifiableBigInteger;
import de.rub.nds.modifiablevariable.bool.ModifiableBoolean;
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;
import de.rub.nds.modifiablevariable.integer.ModifiableInteger;
import jakarta.xml.bind.annotation.*;
import java.math.BigInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Asn1Field implements Asn1Encodable {

    private static final Logger LOGGER = LogManager.getLogger();

    @XmlElement(name = "tagClass")
    private ModifiableInteger tagClass;

    @XmlElement(name = "tagConstructed")
    private ModifiableBoolean tagConstructed;

    @XmlElement(name = "tagNumber")
    private ModifiableInteger tagNumber;

    @XmlElement(name = "length")
    private ModifiableBigInteger length;

    @XmlElement(name = "content")
    private ModifiableByteArray content;

    @XmlAttribute(name = "identifier")
    private String identifier;

    @XmlElement(name = "tagOctets")
    private ModifiableByteArray tagOctets;

    @XmlElement(name = "lengthOctets")
    private ModifiableByteArray lengthOctets;

    private TagClass tagClassType;

    private TagConstructed tagConstructedType;

    @XmlTransient
    private TagNumber tagNumberType;

    @XmlTransient
    private boolean optional = false;

    public Asn1Field(String identifier, TagClass tagClassType, TagConstructed tagConstructedType,
        TagNumber tagNummerType) {
        assert (identifier != null);
        assert (tagClassType != null);
        assert (tagConstructedType != null);
        this.identifier = identifier;
        this.tagClassType = tagClassType;
        this.tagConstructedType = tagConstructedType;
        this.tagNumberType = tagNummerType;
    }

    @Override
    public boolean isCompatible(Integer tagNumber, Boolean constructed, Integer classType) {
        if (tagNumberType != null && tagNumber != tagNumberType.getIntValue()) {
            LOGGER.debug("Not compatible because of the tagNumber Expected " + this.tagNumberType.getIntValue()
                + " but found " + tagNumber);
            return false;
        }
        if (tagConstructedType != null && constructed != tagConstructedType.getBooleanValue()) {
            LOGGER.debug("Not compatible because of constructed type Expected "
                + this.tagConstructedType.getBooleanValue() + " but found " + constructed);
            return false;
        }
        if (tagClassType != null && classType != this.tagClassType.getIntValue()) {
            LOGGER.debug("Not compatible because of tag class type. Expected " + this.tagClassType.getIntValue()
                + " but found " + classType);
            return false;
        } else {
            LOGGER.debug("Asn1Field \'{}\' is compatible", identifier);
            return true;
        }

    }

    public Asn1Field(String identifier) {
        this.identifier = identifier;
    }

    public void setTagClassType(TagClass tagClassType) {
        this.tagClassType = tagClassType;
    }

    public void setTagConstructedType(TagConstructed tagConstructedType) {
        this.tagConstructedType = tagConstructedType;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public TagClass getTagClassType() {
        return tagClassType;
    }

    public TagConstructed getTagConstructedType() {
        return tagConstructedType;
    }

    public TagNumber getTagNumberType() {
        return tagNumberType;
    }

    public void setTagNumberType(TagNumber tagNumberType) {
        this.tagNumberType = tagNumberType;
    }

    public ModifiableInteger getTagClass() {
        return tagClass;
    }

    public void setTagClass(ModifiableInteger tagClass) {
        this.tagClass = tagClass;
    }

    public void setTagClass(int tagClass) {
        this.tagClass = ModifiableVariableFactory.safelySetValue(this.tagClass, tagClass);
    }

    public ModifiableBoolean getTagConstructed() {
        return tagConstructed;
    }

    public void setTagConstructed(ModifiableBoolean tagConstructed) {
        this.tagConstructed = tagConstructed;
    }

    public void setTagConstructed(boolean tagConstructed) {
        this.tagConstructed = ModifiableVariableFactory.safelySetValue(this.tagConstructed, tagConstructed);
    }

    public ModifiableInteger getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(ModifiableInteger tagNumber) {
        this.tagNumber = tagNumber;
    }

    public void setTagNumber(int tagNumber) {
        this.tagNumber = ModifiableVariableFactory.safelySetValue(this.tagNumber, tagNumber);
    }

    public ModifiableBigInteger getLength() {
        return length;
    }

    public void setLength(ModifiableBigInteger length) {
        this.length = length;
    }

    public void setLength(BigInteger length) {
        this.length = ModifiableVariableFactory.safelySetValue(this.length, length);
    }

    public ModifiableByteArray getContent() {
        return content;
    }

    public void setContent(ModifiableByteArray content) {
        this.content = content;
    }

    public void setContent(byte[] content) {
        this.content = ModifiableVariableFactory.safelySetValue(this.content, content);
    }

    public ModifiableByteArray getTagOctets() {
        return tagOctets;
    }

    public void setTagOctets(final ModifiableByteArray tagOctets) {
        this.tagOctets = tagOctets;
    }

    public void setTagOctets(final byte[] tagOctets) {
        this.tagOctets = ModifiableVariableFactory.safelySetValue(this.tagOctets, tagOctets);
    }

    public ModifiableByteArray getLengthOctets() {
        return lengthOctets;
    }

    public void setLengthOctets(final ModifiableByteArray lengthOctets) {
        this.lengthOctets = lengthOctets;
    }

    public void setLengthOctets(final byte[] lengthOctets) {
        this.lengthOctets = ModifiableVariableFactory.safelySetValue(this.lengthOctets, lengthOctets);
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public Asn1FieldSerializer getGenericSerializer() {
        return new Asn1FieldSerializer(this);
    }
}

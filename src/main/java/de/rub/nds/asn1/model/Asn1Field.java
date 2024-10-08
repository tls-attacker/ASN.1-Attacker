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
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.UniversalTagNumber;
import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.biginteger.ModifiableBigInteger;
import de.rub.nds.modifiablevariable.bool.ModifiableBoolean;
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;
import de.rub.nds.modifiablevariable.integer.ModifiableInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.math.BigInteger;
import java.util.Objects;
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

    @XmlTransient private final TagClass tagClassType;

    @XmlTransient private final TagConstructed tagConstructedType;

    /**
     * Note that this field may be null. This is either the case if the tag number is not
     * universally defined of if the tagClass is not UNIVERSAL.
     */
    @XmlTransient private final UniversalTagNumber universalTagNumber;

    private final Integer tagNumberConfig;

    @XmlTransient private boolean optional = false;

    /**
     * The constructor for universal asn1 fields
     *
     * @param identifier The identifier of the field
     * @param tagClassType The tag class of the field
     * @param tagConstructedType The tag constructed type of the field
     * @param tagNumber The universal tag number of the field
     */
    public Asn1Field(
            String identifier,
            TagClass tagClassType,
            TagConstructed tagConstructedType,
            UniversalTagNumber tagNumber) {
        assert (identifier != null);
        assert (tagClassType != null);
        assert (tagClassType == TagClass.UNIVERSAL);
        assert (tagConstructedType != null);
        this.identifier = identifier;
        this.tagClassType = tagClassType;
        this.tagConstructedType = tagConstructedType;
        this.universalTagNumber = tagNumber;
        tagNumberConfig = tagNumber.getIntValue();
    }

    public Asn1Field(
            String identifier,
            TagClass tagClassType,
            TagConstructed tagConstructedType,
            Integer implicitTagNumber) {
        assert (identifier != null);
        assert (tagClassType != null);
        assert (tagConstructedType != null);
        if (tagClassType == TagClass.UNIVERSAL) {
            universalTagNumber = UniversalTagNumber.fromIntValue(implicitTagNumber);
        } else {
            universalTagNumber = null;
        }
        this.identifier = identifier;
        this.tagClassType = tagClassType;
        this.tagConstructedType = tagConstructedType;
        this.tagNumberConfig = implicitTagNumber;
    }

    /** Private no-arg constructor to please JAXB */
    @SuppressWarnings("unused")
    protected Asn1Field() {
        this.tagClassType = null;
        this.tagConstructedType = null;
        this.tagNumberConfig = null;
        this.universalTagNumber = null;
    }

    @Override
    public final boolean matchesHeader(TagClass classType, Boolean constructed, Integer tagNumber) {
        if (!Objects.equals(tagNumber, tagNumberConfig)) {
            LOGGER.debug(
                    "{} not compatible because of the tagNumber Expected {} but found {}",
                    identifier,
                    tagNumberConfig,
                    tagNumber);
            return false;
        }
        if (constructed != tagConstructedType.getBooleanValue()) {
            LOGGER.debug(
                    "{} not compatible because of constructed type Expected {} but found {}",
                    identifier,
                    this.tagConstructedType.getBooleanValue(),
                    constructed);
            return false;
        }
        if (classType != this.tagClassType) {
            LOGGER.debug(
                    "{} not compatible because of tag class type. Expected {} but found {}",
                    identifier,
                    this.tagClassType.getIntValue(),
                    classType);
            return false;
        } else {
            LOGGER.debug("Asn1Field '{}' is compatible", identifier);
            return true;
        }
    }

    @Override
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

    public UniversalTagNumber getUniversalTagNumberType() {
        return universalTagNumber;
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
        this.tagConstructed =
                ModifiableVariableFactory.safelySetValue(this.tagConstructed, tagConstructed);
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

    @Override
    public ModifiableByteArray getContent() {
        return content;
    }

    public void setContent(ModifiableByteArray content) {
        this.content = content;
    }

    public void setContent(byte[] content) {
        this.content = ModifiableVariableFactory.safelySetValue(this.content, content);
    }

    @Override
    public ModifiableByteArray getTagOctets() {
        return tagOctets;
    }

    public void setTagOctets(final ModifiableByteArray tagOctets) {
        this.tagOctets = tagOctets;
    }

    public void setTagOctets(final byte[] tagOctets) {
        this.tagOctets = ModifiableVariableFactory.safelySetValue(this.tagOctets, tagOctets);
    }

    @Override
    public ModifiableByteArray getLengthOctets() {
        return lengthOctets;
    }

    public void setLengthOctets(final ModifiableByteArray lengthOctets) {
        this.lengthOctets = lengthOctets;
    }

    public void setLengthOctets(final byte[] lengthOctets) {
        this.lengthOctets =
                ModifiableVariableFactory.safelySetValue(this.lengthOctets, lengthOctets);
    }

    public Integer getTagNumberConfig() {
        return tagNumberConfig;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}

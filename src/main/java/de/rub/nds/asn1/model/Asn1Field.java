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

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Asn1Field implements Asn1Encodable {

    @XmlElement(name = "tag")
    private ModifiableInteger tag = new ModifiableInteger();

    @XmlElement(name = "tagClass")
    private ModifiableInteger tagClass = new ModifiableInteger();

    @XmlElement(name = "tagConstructed")
    private ModifiableBoolean tagConstructed = new ModifiableBoolean();

    @XmlElement(name = "longTagNumberBytes")
    private ModifiableInteger longTagLength = new ModifiableInteger();

    @XmlElement(name = "tagNumber")
    private ModifiableInteger tagNumber = new ModifiableInteger();

    @XmlElement(name = "longLengthBytes")
    private ModifiableInteger longLength = new ModifiableInteger();

    @XmlElement(name = "length")
    private ModifiableBigInteger length = new ModifiableBigInteger();

    @XmlElement(name = "content")
    private ModifiableByteArray content = new ModifiableByteArray();

    @XmlAttribute(name = "identifier")
    private String identifier;

    @XmlElement(name = "tagOctets")
    private ModifiableByteArray tagOctets = new ModifiableByteArray();

    @XmlElement(name = "lengthOctets")
    private ModifiableByteArray lengthOctets = new ModifiableByteArray();

    private final TagClass tagClassType;
    private final TagConstructed tagConstructedType;
    private final TagNumber tagNumberType;

    @XmlTransient
    private boolean optional = false;

    public Asn1Field(String identifier, TagClass tagClassType, TagConstructed tagConstructedType,
        TagNumber tagNummerType) {
        this.identifier = identifier;
        this.tagClassType = tagClassType;
        this.tagConstructedType = tagConstructedType;
        this.tagNumberType = tagNummerType;
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

    public ModifiableInteger getTagClass() {
        return tagClass;
    }

    public void setTagClass(ModifiableInteger tagClass) {
        this.tagClass = tagClass;
    }

    public void setTagClass(int tagClass) {
        this.tagClass = ModifiableVariableFactory.safelySetValue(this.tagClass, tagClass);
    }

    public ModifiableInteger getTag() {
        return tag;
    }

    public void setTag(ModifiableInteger tag) {
        this.tag = tag;
    }

    public void setTag(int tag) {
        this.tag = ModifiableVariableFactory.safelySetValue(this.tag, tag);
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

    public ModifiableInteger getLongTagLength() {
        return longTagLength;
    }

    public void setLongTagLength(ModifiableInteger longTagLength) {
        this.longTagLength = longTagLength;
    }

    public void setLongTagLength(int longTagLength) {
        this.longTagLength = ModifiableVariableFactory.safelySetValue(this.longTagLength, longTagLength);
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

    public ModifiableInteger getLongLength() {
        return longLength;
    }

    public void setLongLength(ModifiableInteger longLength) {
        this.longLength = longLength;
    }

    public void setLongLength(int longLength) {
        this.longLength = ModifiableVariableFactory.safelySetValue(this.longLength, longLength);
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

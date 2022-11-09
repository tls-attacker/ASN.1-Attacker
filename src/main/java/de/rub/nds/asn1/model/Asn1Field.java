/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2022 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import de.rub.nds.asn1.TagClass;
import de.rub.nds.asn1.TagConstructed;
import de.rub.nds.asn1.TagNumber;
import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.biginteger.ModifiableBigInteger;
import de.rub.nds.modifiablevariable.bool.ModifiableBoolean;
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;
import de.rub.nds.modifiablevariable.integer.ModifiableInteger;
import jakarta.xml.bind.annotation.*;
import java.math.BigInteger;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Asn1Field extends Asn1RawField {

    @XmlElement(name = "tag")
    private ModifiableInteger tag = new ModifiableInteger();

    @XmlElement(name = "tagClass")
    private ModifiableInteger tagClass = new ModifiableInteger();

    @XmlElement(name = "tagConstructed")
    private ModifiableBoolean tagConstructed = new ModifiableBoolean();

    @XmlElement(name = "longTagNumberBytes")
    private ModifiableInteger longTagNumberBytes = new ModifiableInteger();

    @XmlElement(name = "tagNumber")
    private ModifiableInteger tagNumber = new ModifiableInteger();

    @XmlElement(name = "longLengthBytes")
    private ModifiableInteger longLengthBytes = new ModifiableInteger();

    @XmlElement(name = "length")
    private ModifiableBigInteger length = new ModifiableBigInteger();

    @XmlElement(name = "content")
    private ModifiableByteArray content = new ModifiableByteArray();

    private TagClass tagClassType;
    private TagConstructed tagConstructedType;
    private TagNumber tagNumberType;

    public Asn1Field(TagClass tagClassType, TagConstructed tagConstructedType, TagNumber tagNummerType) {
        this.tagClassType = tagClassType;
        this.tagConstructedType = tagConstructedType;
        this.tagNumberType = tagNummerType;
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

    public ModifiableInteger getLongTagNumberBytes() {
        return longTagNumberBytes;
    }

    public void setLongTagNumberBytes(ModifiableInteger longTagNumberBytes) {
        this.longTagNumberBytes = longTagNumberBytes;
    }

    public void setLongTagNumberBytes(int longTagNumberBytes) {
        this.longTagNumberBytes = ModifiableVariableFactory.safelySetValue(this.longTagNumberBytes, longTagNumberBytes);
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

    public ModifiableInteger getLongLengthBytes() {
        return longLengthBytes;
    }

    public void setLongLengthBytes(ModifiableInteger longLengthBytes) {
        this.longLengthBytes = longLengthBytes;
    }

    public void setLongLengthBytes(int longLengthBytes) {
        this.longLengthBytes = ModifiableVariableFactory.safelySetValue(this.longLengthBytes, longLengthBytes);
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

}

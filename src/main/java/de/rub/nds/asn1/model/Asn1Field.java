/**
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2021 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.asn1.model;

import de.rub.nds.asn1.Asn1Encodable;
import de.rub.nds.asn1.serializer.Asn1Serializer;
import de.rub.nds.asn1.serializer.Asn1FieldSerializer;
import de.rub.nds.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.modifiablevariable.biginteger.ModifiableBigInteger;
import de.rub.nds.modifiablevariable.bool.BooleanExplicitValueModification;
import de.rub.nds.modifiablevariable.bool.ModifiableBoolean;
import de.rub.nds.modifiablevariable.bytearray.ModifiableByteArray;
import de.rub.nds.modifiablevariable.integer.IntegerExplicitValueModification;
import de.rub.nds.modifiablevariable.integer.ModifiableInteger;
import java.io.IOException;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Asn1Field extends Asn1RawField {

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

    public Asn1Field() {
        this(0, false, 0);
    }

    public Asn1Field(final int tagClass, final boolean tagConstructed, final int tagNumber) {
        this.tagClass.setOriginalValue(tagClass);
        this.tagConstructed.setOriginalValue(tagConstructed);
        this.tagNumber.setOriginalValue(tagNumber);
        this.length.setOriginalValue(BigInteger.ZERO);
        this.content.setOriginalValue(new byte[0]);
        this.longLengthBytes.setOriginalValue(0);
        this.longTagNumberBytes.setOriginalValue(0);
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

    public void setTagClassModification(int tagClass) {
        this.tagClass.setModification(new IntegerExplicitValueModification(tagClass));
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

    public void setTagConstructedModification(boolean tagConstructed) {
        this.tagConstructed.setModification(new BooleanExplicitValueModification(tagConstructed));
    }

    public ModifiableInteger getLongTagNumberBytes() {
        return longTagNumberBytes;
    }

    public void setLongTagNumberBytes(ModifiableInteger longTagNumberBytes) {
        this.longTagNumberBytes = longTagNumberBytes;
    }

    public void setLongTagNumberBytes(int longTagNumberBytes) {
        this.longTagNumberBytes.setModification(new IntegerExplicitValueModification(longTagNumberBytes));
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

    public void setTagNumberModification(int tagNumber) {
        this.tagNumber.setModification(new IntegerExplicitValueModification(tagNumber));
    }

    public ModifiableInteger getLongLengthBytes() {
        return longLengthBytes;
    }

    public void setLongLengthBytes(ModifiableInteger longLengthBytes) {
        this.longLengthBytes = longLengthBytes;
    }

    public void setLongLengthBytes(int longLengthBytes) {
        this.longLengthBytes.setModification(new IntegerExplicitValueModification(longLengthBytes));
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

    @Override
    public Asn1Serializer getSerializer() {
        return new Asn1FieldSerializer(this);
    }

    @Override
    public Asn1Encodable getCopy() throws JAXBException, IOException, XMLStreamException {
        
    }
}

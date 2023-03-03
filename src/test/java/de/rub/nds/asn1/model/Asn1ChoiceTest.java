/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model;

import static org.junit.jupiter.api.Assertions.*;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagNumber;
import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.context.EmptyChooser;
import de.rub.nds.asn1.handler.Handler;
import de.rub.nds.asn1.parser.Asn1ChoiceParser;
import de.rub.nds.asn1.preparator.Asn1BooleanPreparator;
import de.rub.nds.asn1.serializer.Asn1FieldSerializer;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Asn1ChoiceTest {

    private AbstractChooser chooser;
    private Asn1Choice choice;

    @BeforeEach
    public void setUp() {
        chooser = new EmptyChooser();
        choice =
                new Asn1ChoiceImpl(
                        "test",
                        new Asn1Integer("integer"),
                        new Asn1PrimitiveBitString("bitstring"));
    }

    @Test
    public void testCanMakeValidChoice() {
        byte[] tagOctets = ArrayConverter.hexStringToByteArray("02140A");
        assertTrue(choice.canMakeValidChoice(chooser, tagOctets), "The integer is selectable");

        tagOctets = ArrayConverter.hexStringToByteArray("032A00");
        assertTrue(
                choice.canMakeValidChoice(chooser, tagOctets),
                "The primitive bitstring is selectable");

        tagOctets = ArrayConverter.hexStringToByteArray("302C");
        assertFalse(choice.canMakeValidChoice(chooser, tagOctets), "Sequence is not selectable");
    }

    @Test
    public void testMakeSelection() {
        byte[] tagOctets = ArrayConverter.hexStringToByteArray("02140A");
        choice.makeSelection(chooser, tagOctets);
        assertTrue(choice.getSelectedChoice() instanceof Asn1Integer);

        // Reset
        choice =
                new Asn1ChoiceImpl(
                        "test",
                        new Asn1Integer("integer"),
                        new Asn1PrimitiveBitString("bitstring"));
        tagOctets = ArrayConverter.hexStringToByteArray("032A00");
        choice.makeSelection(chooser, tagOctets);
        assertTrue(choice.getSelectedChoice() instanceof Asn1PrimitiveBitString);

        // Reset
        choice =
                new Asn1ChoiceImpl(
                        "test",
                        new Asn1Integer("integer"),
                        new Asn1PrimitiveBitString("bitstring"));
        tagOctets = ArrayConverter.hexStringToByteArray("302C");
        choice.makeSelection(chooser, tagOctets);
        assertNull(choice.getSelectedChoice());
    }

    @Test
    public void testGetSelectedChoice() {
        assertNull(choice.getSelectedChoice());
        byte[] tagOctets = ArrayConverter.hexStringToByteArray("02140A");
        choice.makeSelection(chooser, tagOctets);
        assertTrue(choice.getSelectedChoice() instanceof Asn1Integer);
    }

    @Test
    public void testSetSelectedChoice() {
        assertNull(choice.getSelectedChoice());
        choice.setSelectedChoice(new Asn1Boolean("test"));
        assertTrue(choice.getSelectedChoice() instanceof Asn1Boolean);
    }

    @Test
    public void testGetIdentifier() {
        assertEquals("test", choice.getIdentifier());
    }

    @Test
    public void testSetIdentifier() {
        assertEquals("test", choice.getIdentifier());
        choice.setIdentifier("other");
        assertEquals("other", choice.getIdentifier());
        choice.setIdentifier(null);
        assertNull(choice.getIdentifier());
    }

    @Test
    public void testGetSerializer() {
        choice.setSelectedChoice(new Asn1Boolean("bool"));
        assertTrue(choice.getSerializer() instanceof Asn1FieldSerializer);
    }

    @Test
    public void testGetSerializerNotSelected() {
        assertThrows(
                RuntimeException.class,
                () -> {
                    choice.getSerializer();
                });
    }

    @Test
    public void testGetPreparator() {
        choice.setSelectedChoice(new Asn1Boolean("bool"));
        assertTrue(choice.getPreparator(chooser) instanceof Asn1BooleanPreparator);
    }

    @Test
    public void testGetPreparatorNotSelected() {
        assertThrows(
                RuntimeException.class,
                () -> {
                    choice.getPreparator(chooser);
                });
    }

    @Test
    public void testGetParser() {
        assertTrue(choice.getParser(chooser) instanceof Asn1ChoiceParser);
    }

    @Test
    public void testIsOptional() {
        assertFalse(choice.isOptional());
    }

    @Test
    public void testIsCompatible() {
        assertTrue(
                choice.isCompatible(
                        TagNumber.INTEGER.getIntValue(), false, TagClass.UNIVERSAL.getIntValue()));
        assertTrue(
                choice.isCompatible(
                        TagNumber.BIT_STRING.getIntValue(),
                        false,
                        TagClass.UNIVERSAL.getIntValue()));
        assertFalse(
                choice.isCompatible(
                        TagNumber.EMBEDDED_PDV.getIntValue(),
                        false,
                        TagClass.UNIVERSAL.getIntValue()));
    }

    public class Asn1ChoiceImpl extends Asn1Choice {

        public Asn1ChoiceImpl(String identifier, Asn1Field... asn1fields) {
            super(identifier, asn1fields);
        }

        @Override
        public Handler getHandler(AbstractChooser chooser) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}

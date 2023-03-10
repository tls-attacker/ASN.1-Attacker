/*
 * ASN.1 Tool - A project for creating arbitrary ASN.1 structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.model.helper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import de.rub.nds.asn1.constants.TagClass;
import de.rub.nds.asn1.constants.TagConstructed;
import de.rub.nds.asn1.constants.TagNumber;
import de.rub.nds.asn1.context.EmptyChooser;
import de.rub.nds.asn1.model.Asn1Boolean;
import de.rub.nds.asn1.parser.Asn1BooleanParser;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SelectableChoiceTest {

    @Mock private Asn1Boolean<EmptyChooser> field;
    private SelectableChoice<EmptyChooser> choice;
    private EmptyChooser chooser;

    @BeforeEach
    public void setUp() {
        choice = new SelectableChoice<EmptyChooser>(field);
        chooser = new EmptyChooser();
    }

    /** Test of isSelectable method, of class SelectableChoice. */
    @Test
    public void testIsSelectable() {
        when(field.getTagClassType()).thenReturn(TagClass.APPLICATION);
        when(field.getTagConstructedType()).thenReturn(TagConstructed.CONSTRUCTED);
        when(field.getTagNumberType()).thenReturn(TagNumber.BIT_STRING);
        when(field.getParser(chooser))
                .thenReturn(new Asn1BooleanParser<EmptyChooser>(chooser, field));
        assertFalse(
                choice.isSelectable(
                        chooser, ArrayConverter.hexStringToByteArray("23"))); // same but universal
        assertFalse(
                choice.isSelectable(
                        chooser, ArrayConverter.hexStringToByteArray("43"))); // same but primitive
        assertFalse(
                choice.isSelectable(
                        chooser,
                        ArrayConverter.hexStringToByteArray("62"))); // same but different tag
        assertTrue(
                choice.isSelectable(chooser, ArrayConverter.hexStringToByteArray("63"))); // correct
    }

    /** Test of getField method, of class SelectableChoice. */
    @Test
    public void testGetField() {
        assertEquals(field, choice.getField());
    }
}

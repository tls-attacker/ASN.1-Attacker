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
import de.rub.nds.asn1.context.AbstractChooser;
import de.rub.nds.asn1.context.EmptyChooser;
import de.rub.nds.asn1.model.Asn1Boolean;
import de.rub.nds.asn1.model.Asn1Encodable;
import de.rub.nds.asn1.model.Asn1Field;
import de.rub.nds.asn1.parser.Asn1BooleanParser;
import de.rub.nds.asn1.parser.Asn1Parser;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SelectableChoiceTest {

    private Asn1Boolean field;
    private SelectableChoice choice;
    private AbstractChooser context;

    @BeforeEach
    public void setUp() {
        field = Mockito.mock(Asn1Boolean.class);
        choice = new SelectableChoice(field);
        context = new EmptyChooser();
    }

    /** Test of isSelectable method, of class SelectableChoice. */
    @Test
    public void testIsSelectable() {
        when(field.getTagClassType()).thenReturn(TagClass.APPLICATION);
        when(field.getTagConstructedType()).thenReturn(TagConstructed.CONSTRUCTED);
        when(field.getTagNumberType()).thenReturn(TagNumber.BIT_STRING);
        when(field.getParser(context)).thenReturn(new Asn1BooleanParser(context, field));
        assertFalse(
                choice.isSelectable(
                        context, ArrayConverter.hexStringToByteArray("23"))); // same but universal
        assertFalse(
                choice.isSelectable(
                        context, ArrayConverter.hexStringToByteArray("43"))); // same but primitive
        assertFalse(
                choice.isSelectable(
                        context,
                        ArrayConverter.hexStringToByteArray("62"))); // same but different tag
        assertTrue(
                choice.isSelectable(context, ArrayConverter.hexStringToByteArray("63"))); // correct
    }

    /** Test of getField method, of class SelectableChoice. */
    @Test
    public void testGetField() {
        assertEquals(field, choice.getField());
    }

    private class Asn1ParserImpl<Context extends AbstractChooser>
            extends Asn1Parser<Context, Asn1Encodable<Context>> {

        public Asn1ParserImpl(Context context, Asn1Field<Context> field) {
            super(context, field);
        }

        @Override
        public void parse(InputStream inputStream) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from
            // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void parseWithoutTag(InputStream inputStream, byte[] tagOctets) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from
            // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void parseIndividualContentFields(InputStream inputStream) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from
            // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}

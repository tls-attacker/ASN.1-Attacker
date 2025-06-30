/*
 * ASN.1-Attacker - A Library for Arbitrary ASN.1 Structures
 *
 * Copyright 2014-2023 Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package de.rub.nds.asn1.preparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import de.rub.nds.asn1.model.Asn1Null;
import org.junit.jupiter.api.Test;

class Asn1PreparatorHelperTest {

    @Test
    void testPrepareFieldAsn1NullWithNullParameter() {
        // Test that the method correctly handles null input without reassigning the parameter
        Asn1Null result = Asn1PreparatorHelper.prepareField(null);

        assertNotNull(result, "Result should not be null");
        assertEquals("null", result.getIdentifier(), "Identifier should be 'null'");
        assertNotNull(result.getContent(), "Content should be set");
        assertArrayEquals(
                new byte[0],
                result.getContent().getValue(),
                "Null content should be empty byte array");
    }

    @Test
    void testPrepareFieldAsn1NullWithExistingObject() {
        // Test that the method correctly handles an existing Asn1Null object
        Asn1Null input = new Asn1Null("testNull");
        Asn1Null result = Asn1PreparatorHelper.prepareField(input);

        assertNotNull(result, "Result should not be null");
        assertEquals(input, result, "Result should be the same object as input");
        assertEquals("testNull", result.getIdentifier(), "Identifier should be preserved");
        assertNotNull(result.getContent(), "Content should be set");
        assertArrayEquals(
                new byte[0],
                result.getContent().getValue(),
                "Null content should be empty byte array");
    }
}

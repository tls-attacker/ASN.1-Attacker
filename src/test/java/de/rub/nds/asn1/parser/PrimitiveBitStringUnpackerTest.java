package de.rub.nds.asn1.parser;

import de.rub.nds.asn1.parser.contentunpackers.PrimitiveBitStringUnpacker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PrimitiveBitStringUnpackerTest {

    // class under test
    private PrimitiveBitStringUnpacker cut;
    private static final String STRING_SIZE_31 = "This_Content_Has_A_Length_Of_31";
    private static final String STRING_SIZE_30 = "his_Content_Has_A_Length_Of_31";

    @BeforeEach
    void initialize() {
        cut = new PrimitiveBitStringUnpacker();
    }

    @Nested
    class callUnpack {
        @Test
        void andContentLengthIsZero() {
            // given
            byte[] content = new byte[0];
            byte[] expected = new byte[0];

            // when
            byte[] actual = cut.unpack(content);

            // then
            Assertions.assertArrayEquals(expected, actual, "The result should be empty!");
        }

        @Test
        void andContentLengthIsOne() {
            // given
            byte[] content = "1".getBytes();
            byte[] expected = new byte[0];

            // when
            byte[] actual = cut.unpack(content);

            // then
            Assertions.assertArrayEquals(expected, actual, "The result should be empty!");
        }

        @Test
        void andContentLengthIs31() {
            // given
            byte[] content = STRING_SIZE_31.getBytes();
            byte[] expected = STRING_SIZE_30.getBytes();

            // when
            byte[] actual = cut.unpack(content);

            // then
            Assertions.assertArrayEquals(expected, actual, "The result should have a length of 30!");
            Assertions.assertNotSame(expected, actual, "The returning result should be a different instance!");
        }

        @Test
        void andNullPointerExceptionIsThrownWhenContentLengthIsNull() {
            // given
            byte[] content = null;

            // when + then
            Assertions.assertThrows(NullPointerException.class, () -> {
                cut.unpack(content);
            });
        }
    }
}
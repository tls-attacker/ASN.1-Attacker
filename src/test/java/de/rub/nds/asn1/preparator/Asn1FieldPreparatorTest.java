package de.rub.nds.asn1.preparator;

import de.rub.nds.asn1.model.Asn1Integer;
import de.rub.nds.modifiablevariable.util.ArrayConverter;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Asn1FieldPreparatorTest {

    public Asn1FieldPreparatorTest() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testPrepare() {
        testSize(1, "020100");
        testSize(2, "02020000");
        testSize(127, "027F00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        testSize(128, "0281800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
    }

    public void testSize(int size, String expectedResult) {
        Asn1FieldPreparator instance = new Asn1FieldPreparatorImpl(size);
        instance.prepare();
        assertArrayEquals(ArrayConverter.hexStringToByteArray(expectedResult), instance.field.getGenericSerializer().serialize(), "Expected: " + expectedResult + " Found: " + ArrayConverter.bytesToHexString(instance.field.getGenericSerializer().serialize()));
    }

    public class Asn1FieldPreparatorImpl extends Asn1FieldPreparator {

        private Asn1Integer integer;

        private final int size;

        public Asn1FieldPreparatorImpl(int size) {
            super(new Asn1Integer("testInteger"));
            this.size = size;
        }

        @Override
        public byte[] encodeContent() {
            return new byte[size];
        }
    }

}

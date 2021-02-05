package byog.lab5.test;

import byog.lab5.HexWorld;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestHexWorld {

    @Test
    public void testCalculateMaxLength() {
        HexWorld hex = new HexWorld();
        int size2 = 4;
        assertEquals(size2, hex.calculateMaxLength(2));

        int size3 = 7;
        assertEquals(size3, hex.calculateMaxLength(3));

        int size4 = 10;
        assertEquals(size4, hex.calculateMaxLength(4));

        int size5 = 13;
        assertEquals(size5, hex.calculateMaxLength(5));

        int size6 = 16;
        assertEquals(size6, hex.calculateMaxLength(6));


    }

}

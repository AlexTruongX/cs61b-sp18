import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {

    /* Tests whether Flik.isSameNumber works. */
    @Test
    public void testIsSameNumber() {
        int x = 1;
        int y = 1;
        int z = 2;

        assertEquals(true, Flik.isSameNumber(x, y));
        assertEquals(false, Flik.isSameNumber(x, z));
    }
}

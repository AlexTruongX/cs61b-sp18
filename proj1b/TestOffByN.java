import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    /* Tests OffByN.equalChars for distinct N */
    @Test
    public void testEqualChars() {
        CharacterComparator ccObj = new OffByN(5);
        assertTrue(ccObj.equalChars('a', 'f'));
        assertTrue(ccObj.equalChars('f', 'a'));
        assertFalse(ccObj.equalChars('f', 'h'));
    }
}

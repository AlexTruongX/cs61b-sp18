import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        /* Lowercase */
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('b', 'a'));
        assertTrue(offByOne.equalChars('r', 'q'));
        assertTrue(offByOne.equalChars('q', 'r'));
        assertFalse(offByOne.equalChars('a', 'a'));
        assertFalse(offByOne.equalChars('a', 'e'));
        assertFalse(offByOne.equalChars('e', 'a'));
        assertFalse(offByOne.equalChars('a', 'c'));
        assertFalse(offByOne.equalChars('c', 'e'));

        /* Uppercase */
        assertTrue(offByOne.equalChars('A', 'B'));
        assertTrue(offByOne.equalChars('B', 'A'));
        assertFalse(offByOne.equalChars('A', 'A'));
        assertFalse(offByOne.equalChars('A', 'G'));
        assertFalse(offByOne.equalChars('G', 'A'));

        /* Symbols/Non-letters */
        assertTrue(offByOne.equalChars('&', '%'));
        assertTrue(offByOne.equalChars('%', '&'));
        assertFalse(offByOne.equalChars('&', '!'));
        assertFalse(offByOne.equalChars('!', '&'));
        assertFalse(offByOne.equalChars('#', '^'));
        assertFalse(offByOne.equalChars('^', '#'));
        assertFalse(offByOne.equalChars('&', '&'));
        assertFalse(offByOne.equalChars('%', '%'));

        /* Mixed characters */
        assertFalse(offByOne.equalChars('A', 'a'));
        assertFalse(offByOne.equalChars('a', 'A'));
        assertFalse(offByOne.equalChars('A', '%'));
        assertFalse(offByOne.equalChars('%', 'A'));
        assertFalse(offByOne.equalChars('a', '%'));
        assertFalse(offByOne.equalChars('%', 'a'));
        assertFalse(offByOne.equalChars('1', 'a'));
        assertFalse(offByOne.equalChars('1', '3'));
    }
}

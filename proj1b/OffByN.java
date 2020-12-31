public class OffByN implements CharacterComparator{
    private int n;

    /* OffByN constructor for unique int N */
    public OffByN(int N) {
        this.n = N;
    }

    /* Checks if char x & y have a diff of exactly N */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == n;
    }
}

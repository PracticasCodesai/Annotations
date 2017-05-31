public class Number {

    private final int n;

    public Number(int n) {
        this.n = n;
    }

    @AddToResult(increment = 5)
    public int getInt() {
        return n;
    }

}

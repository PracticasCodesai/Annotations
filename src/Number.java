public class Number {

    private int n;

    public Number(int n) {
        this.n = n;
    }

    @AddToResult(increment = 5)
    public int getInt() {
        return n;
    }

    @AddToResult(increment = 10)
    public int multiplication(int mult) {return n * mult;}

}

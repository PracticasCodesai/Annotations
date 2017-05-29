public class Number {

    private final int number;

    public Number(int number) {
        this.number = number;
    }

    @AddToResult(increment = 5)
    public int getNumber() {
        return number;
    }

}

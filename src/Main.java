public class Main {
    public static void main(String[] args){
        Number number = new Number(5);
        UpdateNumber updateNumber = new UpdateNumber();
        updateNumber.updateResults(number);
        System.out.println(number.getNumber());
    }
}


public class DecoratorNumber extends Number {

    private Number number;

    public DecoratorNumber(Number number) {
        super(number.getInt());
        this.number = number;
    }

    @Override
    public int getInt() {
        int result = super.getInt();

        try {
            AddToResult annotation = number.getClass().getMethod(getCurrentMethodName())
                    .getAnnotation(AddToResult.class);
            if(annotation != null){
                    result = addToResult(annotation.increment(),result);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return result;
    }


    private int addToResult(int increment, int result){
        return result + increment;
    }

    private String getCurrentMethodName(){
        return new Exception().getStackTrace()[1].getMethodName();
    }
}

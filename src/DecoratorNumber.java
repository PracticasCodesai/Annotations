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
            if (annotation != null) {
                result = addToResult(getIncrement(annotation), result);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return result;
    }

    protected int getIncrement(AddToResult annotation) {
        // The pattern template is use for help test annotation
        return annotation.increment();
    }

    private int addToResult(int increment, int result) {
        return result + increment;
    }

    private String getCurrentMethodName() {
        return new Exception().getStackTrace()[1].getMethodName();
    }
}

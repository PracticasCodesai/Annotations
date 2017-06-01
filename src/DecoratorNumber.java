import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DecoratorNumber extends Number {

    private Number parentNumber;

    DecoratorNumber(Number number) {
        super(number.getInt());
        this.parentNumber = number;
    }

    @Override
    public int getInt() {
        return addToResult();
    }

    @Override
    public int multiplication(int mult) {
        return addToResult(mult);
    }

    private int addToResult(Object... params) {
        Method method = getMethod(parentNumber.getClass(), getCallMethodName());
        int result = (int) dynamicSuper(method, params);
        AddToResult annotation = method.getAnnotation(AddToResult.class);
        return addToResult(annotation.increment(), result);
    }

    private int addToResult(int increment, int result) {
        return result + increment;
    }

    private String getCallMethodName() {
        return new Exception().getStackTrace()[2].getMethodName();
    }

    private Method getMethod(Class clazz, String name){
        for (Method method: clazz.getDeclaredMethods()) {
            if(method.getName().equals(name)){
                return method;
            }
        }
        return null;
    }

    private Object dynamicSuper(Method method, Object... params){
        try {
            return (params == null) ?
                    method.invoke(parentNumber) :
                    method.invoke(parentNumber, params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}

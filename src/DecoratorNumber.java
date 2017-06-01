import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DecoratorNumber implements InvocationHandler{

    private Number parentNumber;

    DecoratorNumber(Number number) {
        this.parentNumber = number;
    }

    private int addToResult(int increment, int result) {
        return result + increment;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(parentNumber, args);
        Method numberMethod = getMethod(method);
        if (numberMethod != null) {
            AddToResult annotation = numberMethod.getAnnotation(AddToResult.class);
            return addToResult((int)result, annotation.increment());
        }
        return null;
    }

    private Method getMethod(Method method) {
        Method[] methods = parentNumber.getClass().getMethods();
        for (Method currentMethod : methods) {
            if(currentMethod.getName().equals(method.getName())){return currentMethod;}
        }
        return null;
    }
}

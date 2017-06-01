import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DecoratorNumber implements InvocationHandler{

    private Number number;

    DecoratorNumber(Number number) {
        this.number = number;
    }

    private int addToResult(int result, Method numberMethod) {
        if (numberMethod != null) {
            AddToResult annotation = numberMethod.getAnnotation(AddToResult.class);
            return annotation.increment() + result;
        }
        return result;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(number, args);
        Method numberMethod = getNumberMethod(method);

        return addToResult((int)result, numberMethod);
    }

    private Method getNumberMethod(Method method) {
        Method[] methods = number.getClass().getDeclaredMethods();
        for (Method currentMethod : methods) {
            if(currentMethod.getName().equals(method.getName())){return currentMethod;}
        }
        return null;
    }
}

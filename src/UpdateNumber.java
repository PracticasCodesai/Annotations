import java.lang.reflect.Method;

public class UpdateNumber {

    public Number updateResults(Number number) {
        Class<?> typeClass = number.getClass();

        Method[] methods = typeClass.getMethods();
        for (Method method : methods) {
            DecoratorNumber decorator = updateResult(number, method);
            if(decorator != null){
                return decorator;
            }
        }
        return number;
    }

    private DecoratorNumber updateResult(Number number, Method method) {
        if (isMethodValid(method)){
            DecoratorNumber decoratorNumber = new DecoratorNumber(number);
            return decoratorNumber;
        }else{
            return null;
        }
    }

    private boolean isMethodValid(Method method) {
        return method.getAnnotation(AddToResult.class) != null
                && method.getReturnType().toString().equals("int");
    }

}

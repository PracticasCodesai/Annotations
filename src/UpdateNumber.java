import java.lang.reflect.Method;

class UpdateNumber {

    Number updateResults(Number number) {
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
        return isMethodValid(method) ? new DecoratorNumber(number) : null;
    }

    private boolean isMethodValid(Method method) {
        return method.getAnnotation(AddToResult.class) != null
                && method.getReturnType().toString().equals("int");
    }
}

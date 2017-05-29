import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UpdateNumber {

    public void updateResults(Object number) {
        Class<?> typeClass = number.getClass();

        Method[] methods = typeClass.getMethods();
        for (Method method : methods) {
            updateResult(number, method);
        }
    }

    private void updateResult(Object object, Method method) {
        if (isMethodValid(method)) try {
            int result = (Integer) method.invoke(object, (Object[]) null);
            System.out.println(result + method.getAnnotation(AddToResult.class).increment());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private boolean isMethodValid(Method method) {
        return method.getAnnotation(AddToResult.class) != null
                && method.getReturnType().toString().equals("int");
    }

}

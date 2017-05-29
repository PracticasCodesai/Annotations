import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UpdateNumber {

    public void updateResults(Object number) {
        Class<?> typeClass = number.getClass();

        Method[] methods = typeClass.getMethods();
        for (Method method : methods) {
            injectResult(number, method, typeClass);
        }
    }

    private void injectResult(Object object, Method method, Class<?> typeClass) {
        if (isMethodValid(method)) try {
                /*
                Field var = object.getClass().getField("number");
                int value = (int) var.get(object);
                var.setInt(object, value + 5);
                */
            int result = (Integer) method.invoke(object, (Object[]) null);
            System.out.println(result + method.getAnnotation(AddToResult.class).increment());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private boolean isMethodValid(Method method) {
        System.out.println(method.getAnnotation(AddToResult.class));
        System.out.println(method.getReturnType().toString().equals("int"));
        return method.getAnnotation(AddToResult.class) != null
                && method.getReturnType().toString().equals("int");
    }

}

import java.lang.reflect.Proxy;

class FactoryNumber {
    static Number number(int num){
        Number number = new NumberWrapper(num);
        DecoratorNumber decoratorNumber = new DecoratorNumber(number);

        Class[] interfaces = new Class[]{Number.class};
        return (Number) Proxy.newProxyInstance(NumberWrapper.class.getClassLoader(),
                interfaces, decoratorNumber);
    }
}

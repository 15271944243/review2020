package review.proxy;

import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/2 17:44
 */
public class Proxy01 {

    public static void main(String[] args) {
        MyInvocationHandler invocationHandler = new MyInvocationHandler();
        MyInterface myInterface = (MyInterface) Proxy.newProxyInstance(MyInterface.class.getClassLoader(),
                new Class[]{MyInterface.class}, invocationHandler);
        myInterface.helloWorld();
    }
}

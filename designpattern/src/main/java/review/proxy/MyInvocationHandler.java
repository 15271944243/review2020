package review.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/2/25 15:20
 */
public class MyInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        method.invoke(args);
        System.out.println("hello world");
        return null;
    }
}

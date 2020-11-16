package review.classloader;

/**
 * 观察父类与子类的初始化关系
 * @author: xiaoxiaoxiang
 * @date: 2020/11/16 17:21
 */
public class SubClass extends SuperClass {
    static {
        System.out.println("subclass init");
    }

    public static final int value2 = 123;
}

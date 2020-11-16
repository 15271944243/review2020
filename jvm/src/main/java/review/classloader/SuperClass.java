package review.classloader;

/**
 * 观察父类与子类的初始化关系
 * @author: xiaoxiaoxiang
 * @date: 2020/11/16 17:21
 */
public class SuperClass {
    static {
        System.out.println("superclass init");
    }

    public static int value = 123;
}

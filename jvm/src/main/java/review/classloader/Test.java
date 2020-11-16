package review.classloader;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/11/16 17:25
 */
public class Test {

    public static void main2(String[] args) {
        // 对于静态字段,只有直接定义这个字段的类才会被初始化,
        // 因为通过其子类来引用父类中定义的静态字段,只会触发弗雷德初始化而不会触发子类的初始化
        System.out.println(SubClass.value);
        System.out.println(SuperClass.value);
    }

    public static void main(String[] args) {
        // 被final修饰的基本类型或字符串,直接从常量池拿,不需要初始化
        System.out.println(SubClass.value2);
    }
}

package review;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/5/20 14:05
 */
public class ClassLoaderDemo {

    public static void main(String[] args) {
        ClassLoaderDemo demo = new ClassLoaderDemo();
        demo.getClassLoader();
    }

    /**
     * 获取 ClassLoader 的方式
     */
    public void getClassLoader() {
        ClassLoaderDemo demo = new ClassLoaderDemo();
        // 1. 通过对象实例获取当前类的 ClassLoader
        ClassLoader classLoader1 = demo.getClass().getClassLoader();
        ClassLoader classLoader2 = this.getClass().getClassLoader();
        // 2. 获取当前线程的ClassLoader
        ClassLoader classLoader3 = Thread.currentThread().getContextClassLoader();
        // 使用系统ClassLoader,即系统的入口点所使用的ClassLoader
        ClassLoader classLoader4 = ClassLoader.getSystemClassLoader();
        System.out.println(111);
    }
}

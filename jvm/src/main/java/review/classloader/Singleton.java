package review.classloader;

/**
 * 通过该类查看类加载过程
 * @author: xiaoxiaoxiang
 * @date: 2020/11/16 15:51
 */
public class Singleton {

    private Singleton() {
        System.out.println("create Singleton Object");
    }

    private static class LazyHolder {

        static {
            System.out.println("LazyHolder.<clinit>");
        }
        // 可与静态代码块调换顺序,查看初始化过程
        static final Singleton INSTANCE = new Singleton();
    }

    public static Object getInstance(boolean flag) {
        if (flag) {
            // 不会导致LazyHolder初始化
            return new LazyHolder[2];
        }
        return LazyHolder.INSTANCE;
    }

    /**
     * 执行java -verbose:class Singleton 查看类加载过程
     * 在VM options项里添加-verbose:class即可
     * @param args
     */
    /*public static void main(String[] args) {
        getInstance(true);
        System.out.println("----");
        getInstance(false);
    }*/
}

package review.classloader;

/**
 * jvm 对class文件的执行模式
 * @author: xiaoxiaoxiang
 * @date: 2020/8/19 22:50
 */
public class ClassWayToRun {

    /**
     * jvm 对class文件的执行模式
     * 默认是 混合模式(-Xmixed) -XX:CompileThreshold = 10000  默认值是10000
     * 可修改JVM参数为  纯解释模式(-Xint)
     * 纯编译模式(-Xcomp)
     * 看看时间
     * @param args
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i=0;i<10000;i++) {
            m();
        }
        long end1 = System.currentTimeMillis();
        for (int i=0;i<10000;i++) {
            m();
        }
        long end2 = System.currentTimeMillis();
        System.out.println(end1 - start);
        System.out.println(end2 - end1);
    }

    public static void m() {
        for (long i=0;i<10000;i++) {
            long j = i%3;
        }
    }
}

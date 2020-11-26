package review.gc;

/**
 * 长期存活对象将进入老年代
 * @author: xiaoxiaoxiang
 * @date: 2020/11/24 17:32
 */
public class MaxTenuringThreshold {

    private static final int _1M = 1024 * 1024;

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M (年轻代) -XX:+PrintGCDetails -XX:SurvivorRatio=8 (Eden:Survivor = 8:1)
     * -XX:MaxTenuringThreshold 决定什么时候进入老年代(最大值15)
     * -XX:+PrintTenuringDistribution
     * 可以分别执行-XX:MaxTenuringThreshold=1 和-XX:MaxTenuringThreshold=15 并观察gc日志
     * @param args
     */
    public static void main(String[] args) {
        byte[] allocation1 = new byte[_1M / 4];
        byte[] allocation2 = new byte[4 * _1M];
        byte[] allocation3 = new byte[4 * _1M];
        allocation3 = null;
        allocation3 = new byte[4 * _1M];
    }
}

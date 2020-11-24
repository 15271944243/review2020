package review.gc;

/**
 * 对象优先分配在Eden区
 * @author: xiaoxiaoxiang
 * @date: 2020/11/24 17:32
 */
public class EdenAllocation {

    private static final int _1M = 1024 * 1024;

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M (年轻代) -XX:+PrintGCDetails -XX:SurvivorRatio=8 (Eden:Survivor = 8:1)
     * @param args
     */
    public static void main(String[] args) {
        byte[] allocation1 = new byte[2 * _1M];
        byte[] allocation2 = new byte[2 * _1M];
        byte[] allocation3 = new byte[2 * _1M];
        // 出现一次minor gc
        byte[] allocation4 = new byte[4 * _1M];
    }
}

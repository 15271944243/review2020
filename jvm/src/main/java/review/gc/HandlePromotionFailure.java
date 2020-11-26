package review.gc;

/**
 * 空间分配担保
 * 参考: https://cloud.tencent.com/developer/article/1082730
 * @author: xiaoxiaoxiang
 * @date: 2020/11/24 17:32
 */
public class HandlePromotionFailure {

    private static final int _1M = 1024 * 1024;

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M (年轻代) -XX:+PrintGCDetails -XX:SurvivorRatio=8 (Eden:Survivor = 8:1)
     * -XX:-HandlePromotionFailure 是否允许担保失败 JDK1.5以及之前版本中默认是关闭的, JDK1.6之后就默认开启
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

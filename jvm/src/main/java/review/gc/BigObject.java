package review.gc;

/**
 * 大对象直接进入老年代
 * @author: xiaoxiaoxiang
 * @date: 2020/11/24 18:23
 */
public class BigObject {

    private static final int _1M = 1024 * 1024;
    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M (年轻代) -XX:+PrintGCDetails
     * -Xloggc: .... 打印 gc 日志到文件中
     * -XX:SurvivorRatio=8 (Eden:Survivor = 8:1)
     * -XX:PretenureSizeThreshold=3145728 指定大于该设置值的对象直接在老年代分配 只对 Serial 和 ParNew 两款收集器有效
     * @param args
     */
    public static void main(String[] args) {
        byte[] allocation = new byte[4 * _1M];
    }
}

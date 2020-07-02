package review;

/**
 * 位运算
 * @author: xiaoxiaoxiang
 * @date: 2020/7/2 09:51
 */
public class BitOperation {

    /**
     * 获取int所能表示的最大整数
     *   用 -1 无符号右移1位 ：-1>>>1
     *  ~(1 << 31)
     *  (1 << -1)-1
     *  (~(1 << -1)
     * @param args
     */
    public static void main(String[] args) {
        BitOperation bitOperation = new BitOperation();
        // 每个1出现的情况
        bitOperation.onePosition();
        // 每个0出现的情况
        bitOperation.zeroPosition();
        // 判断是否是2的n次幂
        System.out.println(bitOperation.power2(3));
        System.out.println(bitOperation.power2(32));
        // 交换数值
        bitOperation.swap(2,3);
        // 奇偶判断
        System.out.println(bitOperation.oddOrEven(3));
        System.out.println(bitOperation.oddOrEven(4));
        // 计算绝对值
        System.out.println(bitOperation.abs(-2));
    }

    /**
     * 求二进制数里每个1出现的的情况
     */
    private void onePosition() {
        int a = 0b011110111;
        System.out.println("a=" + get32BitBinString(a));
        while (a != 0) {
            int x = a & -a;
            System.out.println(get32BitBinString(x));
            a = x ^ a;
        }
    }

    /**
     * 求二进制数里每个0出现的情况
     */
    private void zeroPosition() {
        int b = 0b011110111;
        System.out.println("b=" + get32BitBinString(b));
        b = b ^ 0b111111111;
        while (b != 0) {
            int x = ((b - 1) & b) ^ b;
            System.out.println(get32BitBinString(x));
            b = b ^ x;
        }
    }

    /**
     * 判断是否是2的n次幂
     * @param x
     * @return
     */
    private boolean power2(int x) {
        return ((x&(x-1))==0)&&(x!=0);
    }

    /**
     * 不用临时变量交换两个整数
     * @param x
     * @param y
     */
    private void swap(int x , int y){
        x ^= y;
        y ^= x;
        x ^= y;
        System.out.println(111);
    }

    /**
     * 判断一个数a的奇偶性
     */
    private String oddOrEven(int x) {
        if ((x & 1) == 1) {
            return "odd";
        } else {
            return "even";
        }
    }

    /**
     * 计算绝对值
     * @param x
     * @return
     */
    private int abs(int x) {
        int y;
        y = x >> 31;
        // or: (x+y)^y
        return (x^y)-y;
    }

    /**
     * int转化位32位二进制字符串（方便输出查看）
     * @param number
     * @return
     */
    private static String get32BitBinString(int number) {
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < 32; i++){
            sBuilder.append(number & 1);
            number = number >>> 1;
        }
        return sBuilder.reverse().toString();
    }
}

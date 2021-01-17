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
        System.out.println(get32BitBinString(0));
        System.out.println(get32BitBinString(~0));
        System.out.println(-9 << 28);
        System.out.println(get32BitBinString(-9 << 28));

        System.out.println(-2 >> 2);
        System.out.println(get32BitBinString(-2 >> 2));

        System.out.println(-6 >>> 2);
        System.out.println(get32BitBinString(-6 >>> 2));


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

        System.out.println(get32BitBinString(bitOperation.setRightPosition(15, 3)));
        System.out.println(bitOperation.getPositionValue(15, 3));

        System.out.println(bitOperation.getPositionPower(15, 3));

        // 取模
        int mask = bitOperation.tableSizeFor(12) - 1;
        System.out.println(bitOperation.mod(7, mask));
        System.out.println(bitOperation.mod(6, mask));
        System.out.println(bitOperation.mod(22, mask));
    }

    /**
     * 使用 `&` 来取模, 但有个前提条件是 被取模的掩码为2的n次方减1
     */
    private int mod(int value, int mod) {
        return value & mod;
    }

    /**
     * 找到不小于cap的最小 2 次幂
     * copy from {@link java.util.HashMap}
     * @param cap
     * @return
     */
    private int tableSizeFor(int cap) {
        int maxCapacity = 1 << 30;
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= maxCapacity) ? maxCapacity : n + 1;
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
            System.out.println("b=" + get32BitBinString(b));
            int x = ((b - 1) & b) ^ b;
            int y = b & -b ;
            System.out.println(get32BitBinString(x));
            System.out.println(get32BitBinString(y));
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

    /**
     * 把最低位的1改为0
     * @param x
     * @return
     */
    private int lowone(int x) {
        return x & (x - 1);
    }

    /**
     * 得到最低位的1
     * @param x
     * @return
     */
    private int getlowone(int x) {
          //  (x & (x - 1)) ^ b
        return x & -x;
    }

    /**
     * 将x最右边的n位改为0 (n > 0)
     */
    private int setRightPosition(int x, int n) {
        return x & (~0 << n);
    }

    /**
     * 获取x的第n位值(0或1) (n > 0)
     */
    private int getPositionValue(int x, int n) {
        return  (x >> (n - 1)) & 1;
    }

    /**
     * 获取x的第n位的幂值 (n > 0)
     */
    private int getPositionPower(int x, int n) {
        return  x & (1 << (n - 1));
    }

    /**
     * 仅将第n位改为1 (n > 0)
     */
    private int setPositionOne(int x, int n) {
        return  x | (1 << (n - 1));
    }

    /**
     * 仅将第n位改为0 (n > 0)
     */
    private int setPositionZero(int x, int n) {
        return  x & (~(1 << (n - 1)));
    }

    /**
     * 仅将最高位至(含)第n位改为0 (n > 0)
     */
    private int aaa(int x, int n) {
        return  x & ((1 << n) - 1);
    }

    /**
     * 将第n位至第1位(含)改为0 (n > 0)
     */
    private int bbb(int x, int n) {
        return  x & (~((1 << n) - 1));
    }
}

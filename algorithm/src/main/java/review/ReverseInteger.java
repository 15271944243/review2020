package review;

/**
 * https://leetcode.cn/problems/reverse-integer/ No.7 整数反转
 * @author: xiaoxiaoxiang
 * @date: 2022/5/23 8:00
 */
public class ReverseInteger {

    /**
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     *
     * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
     *
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     *
     * 输入：x = 123
     * 输出：321
     *
     * 输入：x = -123
     * 输出：-321
     *
     * 输入：x = 120
     * 输出：21
     *
     * 输入：x = 0
     * 输出：0
     */
    public static void main(String[] args) {
        int x = 1534236469;
        ReverseInteger fun = new ReverseInteger();
        int result = fun.reverse(x);
        System.out.println(111);
    }

    /**
     * 思路:
     * 遍历除10,,取余数
     * 123        123/10 = 12          12/10=1
     * 余数=0     123%10 = 3           12%10=2
     * r = 0     r = r * 10 + 余数 =3  r = r * 10 + 余数 = 32
     * @param x
     * @return
     */
    public int reverse(int x) {
        int r = 0;
        while (x != 0) {
            // 防止超过 int 的范围
            if (r < Integer.MIN_VALUE / 10 || r > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int d = x % 10;
            r = r * 10 + d;
            x = x / 10;
        }
        return r;
    }
}

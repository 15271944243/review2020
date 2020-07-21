package review.bit;

/**
 * https://leetcode.com/problems/power-of-two/
 * @author: xiaoxiaoxiang
 * @date: 2020/7/21 16:30
 */
public class PowerOfTwo {

    /**
     * Given an integer, write a function to determine if it is a power of two.
     *
     * Example 1:
     *   Input: 1
     *   Output: true
     *   Explanation: 2^0 = 1
     *
     * Example 2:
     *   Input: 16
     *   Output: true
     *   Explanation: 2^4 = 16
     *
     * Example 3:
     *   Input: 218
     *   Output: false
     */

    /**
     * 题目意思: 给一个整型数字,判断它是不是2的n次幂
     * 思路一: 使用位运算,循环右移动,每次移动一位(int类型移动32次),如果只有1个1,说明是2的n次幂
     * 思路二: 使用位运算 (n & (n - 1) == 0) && (n != 0)
     */

    public static void main(String[] args) {
        PowerOfTwo powerOfTwo = new PowerOfTwo();
        System.out.println(powerOfTwo.isPowerOfTwo(0));
        System.out.println(powerOfTwo.isPowerOfTwo(2));
        System.out.println(powerOfTwo.isPowerOfTwo(256));
        System.out.println(powerOfTwo.isPowerOfTwo(111));
        System.out.println("--------------------------");
        System.out.println(powerOfTwo.isPowerOfTwo2(0));
        System.out.println(powerOfTwo.isPowerOfTwo2(2));
        System.out.println(powerOfTwo.isPowerOfTwo2(256));
        System.out.println(powerOfTwo.isPowerOfTwo2(111));
    }

    public boolean isPowerOfTwo(int n) {
        if (n > 0) {
            return ((n & (n - 1)) == 0) && (n != 0);
        }
        return false;
    }

    public boolean isPowerOfTwo2(int n) {
        if (n > 0) {
            int rst = 0;
            int t = n;
            for (int i = 0; i < 32; i++) {
                if ((t & 1) == 1) {
                    rst += 1;
                }
                t = t >> 1;
            }
            return rst == 1;
        }
        return false;
    }
}

package review.bit;

/**
 * https://leetcode.com/problems/number-of-1-bits/
 * @author: xiaoxiaoxiang
 * @date: 2020/7/17 17:59
 */
public class NumberOf1Bits {

    /**
     * Write a function that takes an unsigned integer and return the number of '1' bits
     * it has (also known as the Hamming weight).
     */

    /**
     * 题目意思: 给一个无符号整型数字,返回这个数字中1bit的数量
     * 思路一: 按位判断每位是否是1
     * 思路二: 使用位运算n & (n - 1)
     */
    public static void main(String[] args) {
        NumberOf1Bits obj = new NumberOf1Bits();
        System.out.println(obj.hammingWeight(32));
        System.out.println(obj.hammingWeight2(32));
        System.out.println(obj.hammingWeight2(33));
    }

    public int hammingWeight(int n) {
        int k = 0;
        while (n != 0) {
            k++;
            n = n & (n - 1);
        }
        return k;
    }

    public int hammingWeight2(int n) {
        int rst = 0;
        int mask = 1;
        int t = n;
        for (int i = 0 ; i < 32; i++) {
            if ((t & mask) == 1) {
                rst += 1;
            }
            t = t >> 1;
        }
        return rst;
    }

}

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
     * 思路一: 摩2取余数,并右移1位
     * 思路二: 使用位运算n & (n - 1)
     */

    public int hammingWeight(int n) {
        int k = 0;
        while (n != 0) {
            k++;
            n = n & (n - 1);
        }
        return k;
    }
}

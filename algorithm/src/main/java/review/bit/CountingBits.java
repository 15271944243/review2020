package review.bit;

/**
 * https://leetcode.com/problems/counting-bits/
 * @author: xiaoxiaoxiang
 * @date: 2020/7/21 17:40
 */
public class CountingBits {

    /**
     * Given a non negative integer number num.
     * For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary
     * representation and return them as an array.
     *
     * Example 1:
     *   Input: 2
     *   Output: [0,1,1]
     *
     * Example 2:
     *   Input: 5
     *   Output: [0,1,1,2,1,2]
     *
     * Follow up:
     *   - It is very easy to come up with a solution with run time O(n*sizeof(integer)).
     *     But can you do it in linear time O(n) /possibly in a single pass?
     *   - Space complexity should be O(n).
     *   - Can you do it like a boss? Do it without using any builtin function
     *   like __builtin_popcount in c++ or in any other language.
     */


    /**
     * 题目意思: 给一个非负整型数字num,对每个在 0 ≤ i ≤ num 范围那的数字i
     * 计算计算它的二进制表达式中1的数量,以数组的方式返回它们
     * 要求: 时间复杂度O(n),空间复杂度O(n),不要使用类似c++里的 __builtin_popcount 函数
     *
     * 思路一: 0 ≤ i ≤ num, 把i当作数组的下标,该下标对应的值就是它1的个数
     * i & (i - 1)可以去掉i最右边的一个1（如果有）,因此 i & (i - 1）是比 i 小的,
     * 而且i & (i - 1)的1的个数已经在前面算过了,所以i的1的个数就是 i & (i - 1)的1的个数加上1
     * 思路二: i >> 1会把最低位去掉,因此i >> 1 也是比i小的,同样也是在前面的数组里算过.
     * 当 i 的最低位是0,则 i 中1的个数和i >> 1中1的个数相同; 当i的最低位是1, i 中1的个数是 i >> 1中1的个数再加1
     */

    public static void main(String[] args) {
        CountingBits obj = new CountingBits();
        int[] result = obj.countBits(5);
        int[] result2 = obj.countBits(7);
        System.out.println(111);
    }

    public int[] countBits(int num) {
        int[] result = new int[num + 1];

        for (int i=1;i<result.length;i++) {
            result[i] = result[i&(i-1)] + 1;
        }

        return result;
    }

    public int[] countBits2(int num) {
        int[] result = new int[num + 1];

        for (int i=1;i<result.length;i++) {
            result[i] = result[i >> 1] + (i & 1);
        }

        return result;
    }
}

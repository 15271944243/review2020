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
     * 思路一: 计算num中1的个数为t,然后循环num,每循环一次就就对num减1,判断(num-1)的最后一位是否是1,如果是1,
     * 则(num-1)中的1的个数为t-1,否则(num-1)中的1的个数为t (错误)
     * 思路二:
     */

    public static void main(String[] args) {
        CountingBits obj = new CountingBits();
        int[] result = obj.countBits(5);
        System.out.println(111);
    }

    public int[] countBits(int num) {
        int[] result = new int[num + 1];

        // 计算num中1的个数k
        int n1 = num;
        int k = 0;
        while (n1 != 0) {
            k++;
            n1= n1 & (n1 - 1);
        }

        if (k == 0) {
            return result;
        }
        result[num] = k;

        // 计算 1 ～ num - 1的数字i中1的个数
        int i = num;
        while (i > 0) {
            // 如果i的最后一位是1
            if ((i & 1) == 1) {
                // 个数为k-1,反之个数为k
                k = k - 1;
            }
            result[i-1] = k;
            i--;
        }

        return result;
    }
}

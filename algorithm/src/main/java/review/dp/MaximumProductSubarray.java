package review.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/maximum-product-subarray/ No. 152 乘积最大子序列
 * @author: xiaoxiaoxiang
 * @date: 2020/8/21 16:24
 */
public class MaximumProductSubarray {

    /**
     * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
     *
     * Example 1:
     *
     * Input: [2,3,-2,4]
     * Output: 6
     * Explanation: [2,3] has the largest product 6.
     *
     * Example 2:
     *
     * Input: [-2,0,-1]
     * Output: 0
     * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
     */

    /**
     * 乘积最大子序列: 给定一个整型数字数组(至少有一个元素),找到这个数组中最大乘积的连续子数组(即连续元素乘积最大)
     *
     * 思路一: 循环找出所有子序列的乘积,比较大小
     * 思路二: 采用动态规划思想进行递推
     * ```
     * dp[][] 一维数组表示第i位,
     *
     * ```
     */

    public int maxProduct(int[] nums) {

        int[][] dp = new int[2][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 第i个数的在dp中的位置,即i模2,即i位奇数,值为1,i为偶数,值为0
            int x = i & 1;
            // 第i - 1个数的在dp中的位置,即i - 1模2
            int y = (i - 1) & 1;

        }
        return 0;
    }
}

package review.dp;

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
     * dp[][] 一维数组表示第i位,二维数组表示正数最大值还是负数最小值,1:最大值,2:最小值
     * 思路: 最大值乘以负数得到最小值,最小值乘以负数得到最大值
     * 初始值: dp[0][0] = nums[0],dp[0][1] = nums[0];
     *
     * 如果nums[1] >= 0
     * dp[1][1] = max(dp[0][1] * nums[1], nums[1])
     * dp[1][0] = min(dp[0][0] * nums[1], nums[1])
     * 如果nums[1] < 0
     * dp[1][1] = max(dp[0][0] * nums[1], nums[1])
     * dp[1][0] = min(dp[0][1] * nums[1], nums[1])
     * ```
     */

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[][] dp = new int[nums.length][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= 0) {
                dp[i][1] = Math.max(dp[i-1][1] * nums[i], nums[i]);
                dp[i][0] = Math.min(dp[i-1][0] * nums[i], nums[i]);
            } else {
                dp[i][1] = Math.max(dp[i-1][0] * nums[i], nums[i]);
                dp[i][0] = Math.min(dp[i-1][1] * nums[i], nums[i]);
            }
            max = Math.max(max, dp[i][1]);
        }
        return max;
    }
}

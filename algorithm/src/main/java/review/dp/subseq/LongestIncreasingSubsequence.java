package review.dp.subseq;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/ No. 300 最长递增子序列
 * @author: xiaoxiaoxiang
 * @date: 2021/3/10 08:24
 */
public class LongestIncreasingSubsequence {

    /**
     * Given an integer array nums, return the length of the longest strictly increasing subsequence.
     *
     * A subsequence is a sequence that can be derived from an array by deleting some or no elements
     * without changing the order of the remaining elements.
     * For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
     *
     * Input: nums = [10,9,2,5,3,7,101,18]
     * Output: 4
     * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
     *
     * Input: nums = [0,1,0,3,2,3]
     * Output: 4
     *
     * Input: nums = [7,7,7,7,7,7,7]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 2500
     * -10^4 <= nums[i] <= 10^4
     */

    /**
     * 题目意思: 给你一个整数数组 nums,找到其中最长严格递增子序列的长度
     * 子序列是由数组派生而来的序列,删除（或不删除）数组中的元素而不改变其余元素的顺序
     * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列
     *
     * 思路: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */
    public static void main(String[] args) {
        LongestIncreasingSubsequence demo = new LongestIncreasingSubsequence();
//        int[] nums = new int[]{10,9,2,5,3,7,101,18};
//        int[] nums = new int[]{0,1,0,3,2,3};
//        int[] nums = new int[]{7,7,7,7,7,7,7};
        int[] nums = new int[]{1,3,6,7,9,4,10,5,6};
//        int[] nums = new int[]{4,10,4,3,8,9};
        int result = demo.lengthOfLIS(nums);
        System.out.println(result);
    }

    /**
     *
     * 1. dp[i]的定义为: 从0至第i个元素的最长严格递增子序列的长度
     *
     * 2. 递推公式:
     * dp[i] = max(
     * nums[i] > nums[i-1] ? dp[i-1] + 1 : 1,
     * nums[i] > nums[i-2] ? dp[i-2] + 1 : 1,
     * ...
     * nums[i] > nums[0] ? dp[0] + 1 : 1)
     *
     * 即  dp[i] = max(dp[i], nums[i] > nums[j] ? dp[j] + 1 : 1), j = i-1, j--
     * 第i位的元素需要与 0,1,2... i-1 位进行比较
     *
     * 3. dp数组初始化: dp[0] = 1
     * 4. 遍历顺序:
     * 5. 举例推导dp数组:
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i-1; j >= 0; j--) {
                dp[i] = Math.max(dp[i], nums[i] > nums[j] ? dp[j] + 1 : 1);
            }
        }
        // 找出所有dp中最大的那个
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

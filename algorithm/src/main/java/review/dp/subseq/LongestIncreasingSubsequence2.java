package review.dp.subseq;

/**
 * https://leetcode.com/problems/longest-continuous-increasing-subsequence/ No.674 最长连续递增子序列
 * @author: xiaoxiaoxiang
 * @date: 2021/3/16 09:47
 */
public class LongestIncreasingSubsequence2 {

    /**
     * Given an unsorted array of integers nums, return the length of the longest continuous
     * increasing subsequence (i.e. subarray). The subsequence must be strictly increasing.
     *
     * A continuous increasing subsequence is defined by two indices l and r (l < r)
     * such that it is [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]]
     * and for each l <= i < r, nums[i] < nums[i + 1].
     *
     * Input: nums = [1,3,5,4,7]
     * Output: 3
     * Explanation: The longest continuous increasing subsequence is [1,3,5] with length 3.
     * Even though [1,3,5,7] is an increasing subsequence,
     * it is not continuous as elements 5 and 7 are separated by element 4.
     *
     * Input: nums = [2,2,2,2,2]
     * Output: 1
     * Explanation: The longest continuous increasing subsequence is [2] with length 1.
     * Note that it must be strictly increasing.
     *
     * Constraints:
     * 0 <= nums.length <= 10^4
     * -10^9 <= nums[i] <= 10^9
     */

    /**
     *  题目意思: 给定一个未经排序的整数数组,找到最长且连续递增的子序列,并返回该序列的长度
     *  连续递增的子序列可以由两个下标 l 和 r（l < r）确定,如果对于每个 l <= i < r,都有 nums[i] < nums[i + 1],
     *  那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列
     *
     *  思路: 动态规划
     *  1. 确定dp数组(dp table)以及下标的含义
     *  2. 确定递推公式
     *  3. dp数组如何初始化
     *  4. 确定遍历顺序
     *  5. 举例推导dp数组
     */
    public static void main(String[] args) {
        LongestIncreasingSubsequence2 demo = new LongestIncreasingSubsequence2();
//        int[] nums = new int[]{1,3,5,4,7};
//        int[] nums = new int[]{2,2,2,2,2};
//        int[] nums = new int[]{10,9,2,5,3,7,101,18};
//        int[] nums = new int[]{0,1,0,3,2,3};
//        int[] nums = new int[]{7,7,7,7,7,7,7};
        int[] nums = new int[]{1,3,6,7,9,4,10,5,6};
//        int[] nums = new int[]{4,10,4,3,8,9};
        int result = demo.findLengthOfLCIS(nums);
        System.out.println(result);
    }

    /**
     * DP思路一
     * 1. dp[i][j]的定义为:
     * dp[i][1] nums 数组的 0 ~ i 个数的最大连续递增子序列,且包含第i个数
     * dp[i][1] nums 数组的 0 ~ i 个数的最大连续递增子序列,且不包含第i个数
     *
     * 2. 递推公式:
     * if (nums[i] > nums[i-1]) {
     *     dp[i][1] = dp[i-1][1] + 1
     *     dp[i][0] = dp[i-1][0]
     * } else {
     *     dp[i][1] = 1
     *     dp[i][0] = max(dp[i-1][1], dp[i-1][0])
     * }
     * 3. dp数组初始化: dp[0][1] = 1, dp[0][0] = 0
     * 4. 遍历顺序: 正序遍历
     * 5. 举例推导dp数组:
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[][] dp = new int[nums.length][2];
        dp[0][1] = 1;
        dp[0][0] = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                dp[i][1] = dp[i-1][1] + 1;
                dp[i][0] = dp[i-1][0];
            } else {
                dp[i][1] = 1;
                dp[i][0] = Math.max(dp[i-1][1], dp[i-1][0]);
            }
        }
        return Math.max(dp[nums.length-1][1], dp[nums.length-1][0]);
    }

    /**
     * DP思路二
     * 1. dp[i]的定义为:
     * dp[i] nums 数组的 0 ~ i 个数的最大连续递增子序列,且包含第i个数
     *
     * 2. 递推公式:
     * if (nums[i] > nums[i-1]) {
     *     dp[i] = dp[i-1] + 1
     * } else {
     *     dp[i] = 1
     * }
     * 最后结果就是 max(dp[0], dp[1] ... dp[n])
     * 3. dp数组初始化: dp[0] = 1
     * 4. 遍历顺序: 正序遍历
     * 5. 举例推导dp数组:
     * @param nums
     * @return
     */
    public int findLengthOfLCIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int result = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                dp[i] = dp[i-1] + 1;
            } else {
                dp[i] = 1;
            }
            if (result < dp[i]) {
                result = dp[i];
            }
        }
        return result;
    }
}

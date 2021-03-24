package review.dp.subseq;

/**
 * https://leetcode.com/problems/maximum-subarray/ No.53 最大子序和
 * @author: xiaoxiaoxiang
 * @date: 2021/3/24 09:00
 */
public class MaximumSubarray {

    /**
     * Given an integer array nums, find the contiguous subarray (containing at least one number)
     * which has the largest sum and return its sum.
     *
     * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * Output: 6
     * Explanation: [4,-1,2,1] has the largest sum = 6.
     *
     * Input: nums = [1]
     * Output: 1
     *
     * Input: nums = [5,4,-1,7,8]
     * Output: 23
     *
     * 1 <= nums.length <= 3 * 104
     * -105 <= nums[i] <= 105
     */

    /**
     *  题目意思: 给定一个整数数组 nums,找到一个具有最大和的连续子数组（子数组最少包含一个元素）,返回其最大和
     *
     *  思路: 动态规划
     *  1. 确定dp数组(dp table)以及下标的含义
     *  2. 确定递推公式
     *  3. dp数组如何初始化
     *  4. 确定遍历顺序
     *  5. 举例推导dp数组
     */
    public static void main(String[] args) {
        MaximumSubarray demo = new MaximumSubarray();
//        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
//        int[] nums = new int[]{-2,-1};
        int[] nums = new int[]{5,4,-1,7,8};
        int result = demo.maxSubArray(nums);
        System.out.println(result);
    }

    /**
     *
     * 1. dp[i][j]的定义为:
     *  dp[i][0] 从 0 ~ i 个范围内且不包含第i个元素的最大连续子数组的和
     *  dp[i][0] 从 0 ~ i 个范围内且包含第i个元素的最大连续子数组的和
     *
     * 2. 递推公式:
     * dp[i][0] = max(dp[i-1][1], dp[i-1][0])
     * dp[i][1] = max(dp[i-1][1] + nums[i], nums[i])
     *
     * 3. dp数组初始化: dp[0][0] = 0; dp[0][1] = nums[0]
     * 4. 遍历顺序: 正序遍历
     * 5. 举例推导dp数组:
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        int[][] dp = new int[length][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.max(dp[i-1][1], dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][1] + nums[i], nums[i]);
        }
        return Math.max(dp[length-1][0], dp[length-1][1]);
    }

    /**
     * 优化版, dp[i] 从 0 ~ i 个元素的最大连续子数组的和
     * 如果在 dp[i-1] + nums[i] < nums[i] 时,即 dp[i-1] < 0,那直接从 nums[i]开始从新计数就行了
     * 所以 dp[i]只有两个方向可以推出来:
     * - dp[i - 1] + nums[i],即nums[i]加入当前连续子序列和
     * - nums[i],即从头开始计算当前连续子序列和
     * 所以 dp[i] = Math.max(dp[i-1] + nums[i], nums[i])
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = nums[0];
        int result = dp[0];
        for (int i = 1; i < length; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            if (dp[i] > result) {
                result = dp[i];
            }
        }
        return result;
    }
}

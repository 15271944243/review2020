package review.dp.backpack;

/**
 * https://leetcode.com/problems/target-sum/ No.494 目标和
 * @author: xiaoxiaoxiang
 * @date: 2021/2/23 14:04
 */
public class TargetSum {

    /**
     * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
     * Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
     *
     * Find out how many ways to assign symbols to make sum of integers equal to target S.
     *
     * Input: nums is [1, 1, 1, 1, 1], S is 3.
     * Output: 5
     * Explanation:
     *
     * -1+1+1+1+1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * +1+1+1-1+1 = 3
     * +1+1+1+1-1 = 3
     *
     * There are 5 ways to assign symbols to make the sum of nums be target 3.
     *
     * Constraints:
     *
     * The length of the given array is positive and will not exceed 20.
     * The sum of elements in the given array will not exceed 1000.
     * Your output answer is guaranteed to be fitted in a 32-bit integer.
     */


    /**
     * 题目意思:
     * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。
     * 对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
     * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
     *
     * 约束条件:
     * 数组非空，且长度不会超过 20 。
     * 初始的数组的和不会超过 1000 。
     * 保证返回的最终结果能被 32 位整数存下。
     *
     * 思路一: 递归求解
     *
     * 思路二: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */

    public static void main(String[] args) {
        TargetSum demo = new TargetSum();
//        int[] nums = new int[]{1, 1, 1, 1, 1};
//        int s = 3;
//        int[] nums = new int[]{1};
//        int s = 2;
        int[] nums = new int[]{7,9,3,8,0,2,4,8,3,9};
        int s = 0;
        int result = demo.findTargetSumWays(nums, s);
    }

    /**
     * 本题要如何使表达式结果为target,既然为target,那么就一定有 left组合 - right组合 = target
     * left + right等于sum,而sum是固定的. 公式来了: left - (sum - left) = target ->  left = (target + sum)/2
     * target是固定的,sum是固定的,left就可以求出来
     *
     * 1. dp[j]: 填满容量为j的背包,有dp[j]种方式
     * 2. 确定递推公式: dp[j] += dp[j - nums[i]]
     * 3. dp数组如何初始化: dp[0] = 1; dp[0] 一定要初始化为1,因为dp[0]是在公式中一切递推结果的起源,如果dp[0]是0的话,递归结果将都是0
     * 4. 确定遍历顺序:  先遍历 nums[], 再遍历背包容量(倒序遍历)
     * 5. 举例推导dp数组
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (S > sum) {
            // 没有方案
            return 0;
        }
        if (((S + sum) & 1) == 1) {
            // 奇数也没有方案
            return 0;
        }
        // 奇数向下取整就行了
        int cap = (sum + S) / 2;
        int[] dp = new int[cap + 1];
        // dp[0] 一定要初始化为1,因为dp[0]是在公式中一切递推结果的起源,如果dp[0]是0的话,递归结果将都是0
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            // 倒序遍历背包容量
            for (int j = cap; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[cap];
    }
}

package review.dp.backpack;

/**
 * https://leetcode.com/problems/combination-sum-iv/ No.377 组合总和4
 * @author: xiaoxiaoxiang
 * @date: 2021/2/25 09:59
 */
public class CombinationSum4 {

    /**
     * Given an integer array with all positive numbers and no duplicates,
     * find the number of possible combinations that add up to a positive integer target.
     *
     * Input:
     * nums = [1, 2, 3]
     * target = 4
     *
     * The possible combination ways are:
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * (3, 1)
     *
     * Note that different sequences are counted as different combinations.
     *
     * Therefore the output is 7.
     *
     * Follow up:
     * What if negative numbers are allowed in the given array?
     * How does it change the problem?
     * What limitation we need to add to the question to allow negative numbers?
     */

    /**
     * 题目意思:
     * 给定一个由正整数组成且不存在重复数字的数组,找出和为给定目标正整数的组合的个数
     *
     * 进阶：
     * 如果给定的数组中含有负数会怎么样？
     * 问题会产生什么变化？
     * 我们需要在题目中添加什么限制来允许负数的出现？
     *
     * 注意: 本题名义上叫组合总和,实际上根据题目给的Example,本题应该是排列总和,例如 {1,1,2} 和 {1,2,1} 是算两种组合的
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
        int[] nums = new int[]{1, 2, 3};
        int target = 4;
//        int[] coins = new int[]{2};
//        int amount = 3;
        CombinationSum4 demo = new CombinationSum4();
        int result = demo.combinationSum4(nums, target);
    }

    /**
     * 本题可以转化为完全背包求解:
     * 背包总容量为 target,从 nums[] 数组中取任意个石头(重量 nums[i],价值 nums[i])放入背包中,
     * 每个石头可以使用任意次,求正好能放满背包的情况的排列数
     *
     * 1. dp[j]: 容量为[j]的背包,有多少种方案可以正好放满,求方案的排列数
     * 2. 确定递推公式: dp[j] += dp[j - weight[i]];
     * 3. dp数组如何初始化: dp[0] = 1
     * 4. 确定遍历顺序:  因为是求排列数而不是组合数,所以先遍历背包容量(正序遍历), 再遍历物品
     * 5. 举例推导dp数组: target = 4, nums = [1, 2, 3]; result = 7
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}

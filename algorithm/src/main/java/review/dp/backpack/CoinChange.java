package review.dp.backpack;

/**
 * https://leetcode.com/problems/coin-change/ No.322 零钱兑换
 * @author: xiaoxiaoxiang
 * @date: 2021/2/25 09:10
 */
public class CoinChange {

    /**
     * You are given coins of different denominations and a total amount of money amount.
     * Write a function to compute the fewest number of coins that you need to make up that amount.
     * If that amount of money cannot be made up by any combination of the coins, return -1.
     *
     * Input: coins = [1,2,5], amount = 11
     * Output: 3
     * Explanation: 11 = 5 + 5 + 1
     *
     * Input: coins = [2], amount = 3
     * Output: -1
     *
     * Input: coins = [1], amount = 0
     * Output: 0
     *
     * Input: coins = [1], amount = 1
     * Output: 1
     *
     * Input: coins = [1], amount = 2
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= coins.length <= 12
     * 1 <= coins[i] <= 2^31 - 1
     * 0 <= amount <= 10^4
     */

    /**
     * 题目意思:
     * 给定不同面额的硬币 coins 和一个总金额 amount
     * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数.如果没有任何一种硬币组合能组成总金额,返回 -1
     *
     * 你可以认为每种硬币的数量是无限的
     *
     * 约束条件:
     * 0 <= amount <= 5000
     * 1 <= coin <= 5000
     * 硬币数量小于500
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
//        int[] coins = new int[]{1, 2, 5};
//        int amount = 11;
        int[] coins = new int[]{1};
        int amount = 0;
//        int[] coins = new int[]{2};
//        int amount = 3;
        CoinChange demo = new CoinChange();
        int result = demo.coinChange(coins, amount);
    }

    /**
     * 硬币可以用无限次,完全背包问题
     *
     * dp[j]为容量为[j]的背包,正好放满的最小物品数
     * 得到dp[j],只有一个来源，dp[j - coins[i]]（没有考虑coins[i]）
     * 凑足总额为j - coins[i]的最少个数为dp[j - coins[i]],那么只需要加上一个钱币coins[i]即dp[j - coins[i]] + 1就是dp[j]（考虑coins[i]）
     * 因为 i 有很多个, 所以要取 (dp[j-coins[0]] + 1) ... dp[j-coins[i]] + 1  中最小的
     * 即 dp[j] = min(dp[j], dp[j-coins[i]] + 1)
     *
     * 1. dp[j]: 容量为[j]的背包,正好放满的最小硬币数
     * 2. 确定递推公式: dp[j] = min(dp[j], dp[j-coins[i]] +1)
     * 3. dp数组如何初始化: dp[0] = 0, 其他的初始化为 Integer.MAX_VALUE
     * 4. 确定遍历顺序:  先遍历 coins[], 再遍历背包容量(正序遍历)
     * 5. 举例推导dp数组: amount = 5, coins = [1, 2, 5]; result = 4
     * @param amount
     * @param coins
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        // dp[0] =0, 其他是 Integer.MAX_VALUE
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < coins.length; i++) {
            // 因为硬币可以重复使用,正序遍历
            for (int j = coins[i]; j <= amount; j++) {
                if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}

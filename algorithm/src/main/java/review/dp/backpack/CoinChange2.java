package review.dp.backpack;

/**
 * https://leetcode.com/problems/coin-change-2/ No.518 零钱兑换 II
 * @author: xiaoxiaoxiang
 * @date: 2021/2/25 09:10
 */
public class CoinChange2 {

    /**
     * You are given coins of different denominations and a total amount of money.
     * Write a function to compute the number of combinations that make up that amount.
     * You may assume that you have infinite number of each kind of coin.
     *
     * Input: amount = 5, coins = [1, 2, 5]
     * Output: 4
     * Explanation: there are four ways to make up the amount:
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     *
     * Input: amount = 3, coins = [2]
     * Output: 0
     * Explanation: the amount of 3 cannot be made up just with coins of 2.
     *
     * Input: amount = 10, coins = [10]
     * Output: 1
     *
     * 0 <= amount <= 5000
     * 1 <= coin <= 5000
     * the number of coins is less than 500
     */

    /**
     * 题目意思:
     * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个
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
        int[] coins = new int[]{1, 2, 5};
        int amount = 5;
//        int[] coins = new int[]{2};
//        int amount = 3;
        CoinChange2 demo = new CoinChange2();
        int result = demo.change(amount, coins);
    }

    /**
     * 该问题可以看作一个完全背包问题,物品的重量和质量都是 coins[i], 选任意个物品,每个物品可以选任意次,
     * 放入背包,求正好放满背包有多少种方法
     * 1. dp[j]: 容量为[j]的背包,有多少种方案可以正好放满
     * 2. 确定递推公式: dp[j] += dp[j - weight[i]];
     * 3. dp数组如何初始化: dp[0] = 1
     * 4. 确定遍历顺序:  先遍历 coins[], 再遍历背包容量(正序遍历)
     * 5. 举例推导dp数组: amount = 5, coins = [1, 2, 5]; result = 4
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
}

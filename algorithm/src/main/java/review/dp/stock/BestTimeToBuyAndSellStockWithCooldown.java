package review.dp.stock;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/ No. 309 买卖股票的最佳时机含冷静期
 * @author: xiaoxiaoxiang
 * @date: 2020/9/11 11:00
 */
public class BestTimeToBuyAndSellStockWithCooldown {

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
     *
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
     *
     * Example:
     *
     * Input: [1,2,3,0,2]
     * Output: 3
     * Explanation: transactions = [buy, sell, cooldown, buy, sell]
     */

    /**
     * 题目意思:  给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)
     *
     * 思路: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */
    public static void main(String[] args) {
        BestTimeToBuyAndSellStockWithCooldown obj = new BestTimeToBuyAndSellStockWithCooldown();
        int[] nums = new int[]{1,2,3,0,2};
//        int[] nums = new int[]{3,3,5,0,0,3,1,4};
//        int[] nums = new int[]{7,6,4,3,1};
        int result = obj.maxProfit(nums);
    }

    /**
     *
     * 其实一开始现金是0，那么加入第i天买入股票现金就是 -prices[i]， 这是一个负数
     * 1. dp[i][j]的定义为:
     * dp[i][0] 表示第i天不持有股票,且非当天卖出所得现金最大值
     * dp[i][1]表示第i天不持有股票,且当天卖出所得现金最大值
     * dp[i][2] 表示第i天持有股票所得现金最大值
     *
     * 2. 递推公式:
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1])
     * dp[i][1] = dp[i-1][2] + prices[i]
     * dp[i][2] = max(dp[i-1][0] - price[i], dp[i-1][2])
     *
     * 3. dp数组初始化: dp[0][1] = 0,dp[0][0] = 0, dp[0][2] = -prices[0]
     * 4. 遍历顺序:
     * 5. 举例推导dp数组:
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[][] dp = new int[prices.length][3];
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[0][2] = -prices[0];
        for (int i = 1; i <prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][2] + prices[i];
            dp[i][2] = Math.max(dp[i-1][0] - prices[i], dp[i-1][2]);
        }
        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][1]);
    }
}

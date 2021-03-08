package review.dp.stock;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/ No. 714 买卖股票的最佳时机含手续费
 * @author: xiaoxiaoxiang
 * @date: 2021/3/8 11:00
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {

    /**
     * You are given an array prices where prices[i] is the price of a given stock on the ith day,
     * and an integer fee representing a transaction fee.
     *
     * Find the maximum profit you can achieve. You may complete as many transactions as you like,
     * but you need to pay the transaction fee for each transaction.
     *
     * Note: You may not engage in multiple transactions simultaneously
     * (i.e., you must sell the stock before you buy again).
     *
     * Input: prices = [1,3,2,8,4,9], fee = 2
     * Output: 8
     * Explanation: The maximum profit can be achieved by:
     * - Buying at prices[0] = 1
     * - Selling at prices[3] = 8
     * - Buying at prices[4] = 4
     * - Selling at prices[5] = 9
     * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
     *
     * Input: prices = [1,3,7,5,10,3], fee = 3
     * Output: 6
     *
     * Constraints:
     *
     * 1 < prices.length <= 5 * 10^4
     * 0 < prices[i], fee < 5 * 10^4
     */

    /**
     * 题目意思: 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格; 非负整数 fee 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 返回获得利润的最大值。
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     *
     * 思路: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */
    public static void main(String[] args) {
        BestTimeToBuyAndSellStockWithTransactionFee obj = new BestTimeToBuyAndSellStockWithTransactionFee();
//        int[] prices = new int[]{1,3,2,8,4,9};
//        int fee = 2;
        int[] prices = new int[]{1,3,7,5,10,3};
        int fee = 3;
//        int[] nums = new int[]{7,6,4,3,1};
        int result = obj.maxProfit(prices, fee);
    }

    /**
     *
     * 此题与 BestTimeToBuyAndSellStock2 相比,增加了手续费,即每次卖出时,都会扣除手续费
     * 可以理解为[实际卖出的价格] = [真实卖出价格] - [手续费]
     *
     * 其实一开始现金是0，那么加入第i天买入股票现金就是 -prices[i]， 这是一个负数
     * 1. dp[i][j]的定义为:
     * dp[i][0] 表示第i天不持有股票所得现金最大值
     * dp[i][1]表示第i天持有股票所得现金最大值
     *
     * 2. 递推公式:
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i] - fee)
     * dp[i][1] = max(dp[i-1][0] - prices[i], dp[i-1][1])
     *
     * 3. dp数组初始化: dp[0][0] = 0, dp[0][1] = -prices[0]
     * 4. 遍历顺序:
     * 5. 举例推导dp数组:
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i <prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i-1][0] - prices[i], dp[i-1][1]);
        }
        return dp[prices.length - 1][0];
    }
}

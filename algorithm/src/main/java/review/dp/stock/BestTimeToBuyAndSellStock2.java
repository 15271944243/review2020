package review.dp.stock;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/ No. 122 买卖股票的最佳时机2
 * @author: xiaoxiaoxiang
 * @date: 2020/6/7 21:51
 */
public class BestTimeToBuyAndSellStock2 {

    /**
     * Say you have an array prices for which the ith element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
     *
     * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
     *
     * Input: prices = [7,1,5,3,6,4]
     * Output: 7
     * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
     * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
     *
     * Input: prices = [1,2,3,4,5]
     * Output: 4
     * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
     * Note that you cannot buy on day 1, buy on day 2 and sell them later,
     * as you are engaging multiple transactions at the same time. You must sell before buying again.
     *
     * Input: prices = [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e., max profit = 0.
     */

    /**
     * 题目意思: 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 思路一: 贪心算法详见 {@link review.greedy.BestTimeToBuyAndSellStock2}
     * 思路二: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock2 demo = new BestTimeToBuyAndSellStock2();
//        int[] prices = new int[]{7,1,5,3,6,4};
        int[] prices = new int[]{1,2,3,4,5};
//        int[] prices = new int[]{7,6,4,3,1};
        int result = demo.maxProfit(prices);
    }

    /**
     *
     * 其实一开始现金是0，那么加入第i天买入股票现金就是 -prices[i]， 这是一个负数
     * 1. dp[i][j]的定义为:
     * dp[i][1] 表示第i天持有股票所得现金最大值
     * dp[i][0] 表示第i天不持有股票所得现金最大值
     * [持有]不代表就是当天[买入],也有可能是昨天就买入了,今天保持持有的状态
     * 2. 递推公式:
     * [第i天持有股票所得的现金最大值] = max([第i-1天持有的最大值,第i天不买], [第i-1天不持有的最大值,但是第i天买,买是要花钱的])
     * 即 dp[i][1] = max(dp[i-1][1],  dp[i][0] - prices[i])
     *
     * [第i天不持有股票所得的现金最大值] = max([第i-1天不持有股票所得的现金最大值], [第i-1天持有股票,第i天卖出])
     * 即 dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     *
     * 3. dp数组初始化: dp[0][1] = -prices[0],dp[0][0] = 0
     * 4. 遍历顺序:
     * 5. 举例推导dp数组:
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        dp[0][1] = -prices[0];
        dp[0][0] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
        }
        return dp[prices.length - 1][0];
    }

}

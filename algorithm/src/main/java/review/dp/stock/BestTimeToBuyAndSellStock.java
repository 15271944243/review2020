package review.dp.stock;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/ No. 121
 * @author: xiaoxiaoxiang
 * @date: 2020/9/4 09:58
 */
public class BestTimeToBuyAndSellStock {

    /**
     * Say you have an array for which the ith element is the price
     * of a given stock on day i.
     *
     * If you were only permitted to complete at most one transaction
     * (i.e., buy one and sell one share of the stock),
     * design an algorithm to find the maximum profit.
     *
     * Note that you cannot sell a stock before you buy one.
     */

    /**
     * Example 1:
     *
     * Input: [7,1,5,3,6,4]
     * Output: 5
     * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
     *              Not 7-1 = 6, as selling price needs to be larger than buying price.
     * Example 2:
     *
     * Input: [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     */

    /**
     * 题目意思: 给定一个数组,数组的第i个元素就是股票第i天第价格
     * 但是你只能完成最多一笔交易(买卖一次),设计一个算法找到最大利润
     * 思路一: 遍历求解
     * 思路二: 遍历求解优化
     * 思路三: 动态规划

     *
     */

    /**
     * 思路一: 遍历求解 时间复杂度O(n^2)
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        if (prices == null) {
            return 0;
        }
        int max = 0;
        for (int i=0;i<prices.length;i++) {
            for (int j=i;j<prices.length;j++) {
                int profit = prices[j] - prices[i];
                if (profit > max) {
                    max = profit;
                }
            }
        }
        return max;
    }


    /**
     * 思路二: 遍历求解优化 时间复杂度O(n)
     * 其实只需要关心之前（不包括现在）看到的最低股价
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // 当天买当天卖就是0
        int max = 0;
        int min = prices[0];
        for (int i=1;i<prices.length;i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            if (max < prices[i] - min) {
                max = prices[i] - min;
            }
        }
        return max;
    }

    /**
     * 思路三: 动态规划
     * 以 [7,1,5,3,6,4] 为例
     * 因为只能买卖1次;
     * 如果第1天持有,最大利润是-7,记为 dp[0][1] = -7
     * 如果第1天卖出后不持有,最大利润是0,记为 dp[0][0] = 0
     *
     * 如果第2天持有,最大利润是dp[1][1] = max(dp[0][1], -prices[1])
     * 如果第2天卖出后不持有,最大利润是dp[1][0] = max((dp[0][1] + prices[1]), dp[0][0]) 即  max(第1天持有,然后第2天卖了, 第1天就已经卖了)
     *
     * 如果第3天持有,最大利润是dp[2][1] = max(dp[1][1], -prices[2])
     * 如果第2天卖出后不持有,最大利润是dp[2][0] = max((dp[1][1] + prices[2]), dp[1][0]) 即  max(第2天持有,然后第3天卖了, 前几天就已经卖了)
     *
     * dp[][]的第1维数组,表示第i天,第2维数组,0表示卖出后不持有(不一定是当天卖出),1表示持有; dp[i][0]就是当天的最大利润
     *
     * 推导出状态转移方程:
     * dp[i][0] = max((dp[i-1][1] + prices[i]), dp[i-1][0])
     *
     * dp[i][1] = max(dp[i-1][1], -prices[i])
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // dp[][]的第1维数组,表示第i天,第2维数组,0表示卖出后不持有(不一定是当天卖出),1表示持有; dp[i][0]就是当天的最大利润
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i=1;i<prices.length;i++) {
            dp[i][0] = Math.max((dp[i-1][1] + prices[i]), dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }

        return dp[prices.length - 1][0];
    }
}

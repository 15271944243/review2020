package review.dp.stock;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/ No. 121 买卖股票的最佳时机
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
     *
     * Constraints:
     *
     * 1 <= prices.length <= 10^5
     * 0 <= prices[i] <= 10^4
     */

    /**
     * 题目意思:
     *
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择某一天买入这只股票，并选择在未来的某一个不同的日子卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     * 只能完成最多一笔交易(买卖一次),且不能当天买当天卖
     *
     * 思路一: 遍历求解
     * 思路二: 遍历求解优化
     * 思路三: 动态规划
     *
     */
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock demo = new BestTimeToBuyAndSellStock();
//        int[] prices = new int[]{7,1,5,3,6,4};
        int[] prices = new int[]{7,6,4,3,1};
        int result = demo.maxProfit(prices);
    }

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
     *
     * 其实一开始现金是0，那么加入第i天买入股票现金就是 -prices[i]， 这是一个负数
     * 1. dp[i][j]的定义为: dp[i][1] 表示第i天持有股票所得现金最大值, dp[i][0] 表示第i天不持有股票所得现金最大值
     * [持有]不代表就是当天[买入],也有可能是昨天就买入了,今天保持持有的状态
     * 2. 递推公式:
     * [第i天持有股票所得的现金最大值] = max([第i-1天持有的最大值,第i天不买,因为只能买一次], [之前一直不持有(因为只能买一次),但是第i天买,买是要花钱的])
     * 即 dp[i][1] = max(dp[i-1][1], - prices[i])
     *
     * [第i天不持有股票所得的现金最大值] = max([第i-1天不持有股票所得的现金最大值], [第i-1天持有股票,第i天卖出])
     * 即 dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
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

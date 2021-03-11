package review.dp.stock;


/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/ No. 188 买卖股票的最佳时机4
 * @author: xiaoxiaoxiang
 * @date: 2021/3/9 08:50
 */
public class BestTimeToBuyAndSellStock4 {

    /**
     * You are given an integer array prices where prices[i] is the price of a given stock on the ith day,
     * and an integer k.
     *
     * Find the maximum profit you can achieve. You may complete at most k transactions.
     *
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     */

    /**
     * Input: k = 2, prices = [2,4,1]
     * Output: 2
     * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
     *
     * Input: k = 2, prices = [3,2,6,5,0,3]
     * Output: 7
     * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
     *
     * Constraints:
     *
     * 0 <= k <= 100
     * 0 <= prices.length <= 1000
     * 0 <= prices[i] <= 1000
     */

    /**
     * 题目意思: 给定一个整数数组 prices, 它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
     *
     * 思路: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock4 obj = new BestTimeToBuyAndSellStock4();
//        int[] prices = new int[]{3,2,6,5,0,3};
        int[] prices = new int[]{2,4,1};
        int k = 2;
        int result = obj.maxProfit(k, prices);
    }

    /**
     *
     * 其实一开始现金是0，那么加入第i天买入股票现金就是 -prices[i]， 这是一个负数
     * 1. dp[i][j]的定义为:
     * dp[i][0] 第1次持有股票所得现金最大值
     * dp[i][1] 第1次卖出股票所得现金最大值
     * dp[i][2] 第2次持有股票所得现金最大值
     * dp[i][3] 第2次卖出股票所得现金最大值
     * ...
     * dp[i][2*(k-1)] 第k次持有股票所得现金最大值
     * dp[i][2*(k-1) + 1] 第k次卖出股票所得现金最大值
     *
     * [持有]不代表就是当天[买入],也有可能是昨天就买入了,今天保持持有的状态
     *
     * dp[i][0] = max(dp[i-1][0], -prices[i])
     * dp[i][1] = max(dp[i-1][0] + prices[i], dp[i-1][1])
     *
     * dp[i][2] = max(dp[i-1][2], dp[i-1][1] - prices[i])
     * dp[i][3] = max(dp[i-1][2] + prices[i], dp[i-1][3])
     *
     * ...
     *
     * 2. 递推公式:
     * j = 0 dp[i][j] = max(dp[i-1][j], -prices[i])
     * i = 奇数 dp[i][j] = max(dp[i-1][j-1] + prices[i], dp[i-1][j])
     * i = 偶数 dp[i][j] = max(dp[i-1][j], dp[i-1][j-1] - prices[i])
     *
     * 3. dp数组初始化: dp[0][0] = 0, dp[0][1] = -prices[0], dp[0][2] = 0, dp[0][3] = -prices[0] ...
     * 4. 遍历顺序:
     * 5. 举例推导dp数组:
     * @param prices
     * @param k
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int length2 = 2 * k;
        int[][] dp = new int[prices.length][length2];
        for (int i = 0; i < length2; i++) {
            if ((i & 1) == 1) {
                // 奇数
                dp[0][i] = 0;
            } else {
                // 偶数
                dp[0][i] = -prices[0];
            }
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < length2; j++) {
                if ((j & 1) == 1) {
                    // 奇数
                    dp[i][j] = Math.max(dp[i-1][j-1] + prices[i], dp[i-1][j]);
                } else {
                    // 偶数
                    // 注意(dp[i-1][j-1] - prices[i])会导致多次购买同一股票,但是没有卖出,但是不影响最终结果
                    dp[i][j] = Math.max(dp[i-1][j], j == 0 ? -prices[i] : (dp[i-1][j-1] - prices[i]));
                }
            }
        }
        int maxProfit = 0;
        for (int i = 1; i < length2; i+=2) {
            maxProfit = Math.max(maxProfit, dp[prices.length-1][i]);
        }
        return maxProfit;
    }

    /**
     * 其实一开始现金是0，那么加入第i天买入股票现金就是 -prices[i]， 这是一个负数
     * 1. dp[i][j]的定义为:
     * dp[i][0] 表示第1次交易,第i天不持有股票所得现金最大值
     * dp[i][1] 表示第1次交易,第i天持有股票所得现金最大值
     * dp[i][2] 表示第2次交易,第i天不持有股票所得现金最大值
     * dp[i][3] 表示第3次交易,第i天持有股票所得现金最大值
     * [持有]不代表就是当天[买入],也有可能是昨天就买入了,今天保持持有的状态
     * 2. 递推公式:
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], - prices[i])
     * dp[i][2] = max(dp[i-1][3] + prices[i], dp[i-1][2])
     * dp[i][3] = max(dp[i][0] - prices[i], dp[i-1][3])
     *
     * 3. dp数组初始化: dp[0][1] = -prices[0],dp[0][0] = 0,dp[0][2] = 0,dp[0][2] = -prices[0]
     * 4. 遍历顺序:
     * 5. 举例推导dp数组:
     * @param prices
     * @return
     */
    /*public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[][] dp = new int[prices.length][4];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], - prices[i]);
            dp[i][2] = Math.max(dp[i-1][3] + prices[i], dp[i-1][2]);
            dp[i][3] = Math.max(dp[i][0] - prices[i], dp[i-1][3]);
        }
        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][2]);
    }*/
}

package review.dp.stock;


/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/ No. 123 买卖股票的最佳时机3
 * @author: xiaoxiaoxiang
 * @date: 2020/9/4 09:58
 */
public class BestTimeToBuyAndSellStock3 {

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     *
     * Note: You may not engage in multiple transactions at the same time (i.e.,
     * you must sell the stock before you buy again).
     */

    /**
     * Example 1:
     *
     * Input: prices = [3,3,5,0,0,3,1,4]
     * Output: 6
     * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
     * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
     *
     * Example 2:
     *
     * Input: prices = [1,2,3,4,5]
     * Output: 4
     * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
     * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time.
     * You must sell before buying again.
     *
     * Example 3:
     *
     * Input: prices = [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     *
     * Constraints:
     *
     * 1 <= prices.length <= 10^5
     * 0 <= prices[i] <= 10^5
     */

    /**
     * 题目意思: 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 思路一: 两次买卖拆分
     * 思路二: 动态规划
     */
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock3 obj = new BestTimeToBuyAndSellStock3();
        int[] nums = new int[]{6,1,3,2,4,7};
//        int[] nums = new int[]{3,3,5,0,0,3,1,4};
//        int[] nums = new int[]{7,6,4,3,1};
        int result = obj.maxProfit(nums);
        int result2 = obj.maxProfit2(nums);
    }

    /**
     * 思路一: 两次买卖拆分
     * 因为最多允许买卖两次,将问题拆分为两次买卖处理,转化为之前做过的一次买卖求最大利润的问题
     * 假设第一次买卖的范围是 0 ~ i,第二次买卖的范围是 i+1 ~ n
     * 我们可以把每个i 在 0 ~ i 和 i+1 ~ n返回的最大利润算出来,
     * 然后相加,就得到每个i对应两次买卖的最大值,然后从这n个最大值中取最大的那个,就得到最大利润
     *
     * 这里 0 ~ i 的最大利润可以利用之前的算法,一次循环即可,但是i+1 ~ n 怎么算呢?
     * 可以采用反向的方式,从n往i+1的位置算,不过这时候计算的最大亏损(反向计算的最大亏损其实就是正向计算的最大利润)
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        int max = 0;
        int min = prices[0];
        int reverseMax = 0;
        int reverseMin = prices[len - 1];

        int[] profit = new int[len];
        int[] reverseProfit = new int[len];

        for (int i = 1;i < len; i++) {
            // 正向最大利润
            min = Math.min(min, prices[i]);
            max = Math.max(max, prices[i] - min);
            profit[i] = max;
            // 反向最大亏损,reverseMin其实是最大值,而reverseMax是负数,它的绝对值是最大亏损
            reverseMin = Math.max(reverseMin, prices[len - 1 - i]);
            reverseMax = Math.min(reverseMax, prices[len - 1 - i] - reverseMin);
            reverseProfit[len - 1 - i] = reverseMax;
        }
        int maxProfit = 0;
        for (int i = 1;i < len; i++) {
            maxProfit = Math.max(maxProfit, profit[i] - reverseProfit[i]);
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
    public int maxProfit2(int[] prices) {
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
    }
}

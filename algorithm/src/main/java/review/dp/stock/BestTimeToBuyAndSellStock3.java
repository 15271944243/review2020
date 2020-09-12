package review.dp.stock;

import review.dp.MaximumProductSubarray;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/ No. 123
 * @author: xiaoxiaoxiang
 * @date: 2020/9/4 09:58
 */
public class BestTimeToBuyAndSellStock3 {

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     *
     * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
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
     * 题目意思: 给定一个数组,数组的第i个元素就是股票第i天第价格
     * 但是你只能完成最多两笔交易(买卖两次),必须卖掉股票才能再次买入,设计一个算法找到最大利润
     * 思路一: 两次买卖拆分
     * 思路二: 动态规划
     */

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
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock3 obj = new BestTimeToBuyAndSellStock3();
//        int[] nums = new int[]{6,1,3,2,4,7};
//        int[] nums = new int[]{3,3,5,0,0,3,1,4};
        int[] nums = new int[]{7,6,4,3,1};
        int result = obj.maxProfit(nums);
        System.out.println(result);
    }

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
    public int maxProfit2(int[] prices) {
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

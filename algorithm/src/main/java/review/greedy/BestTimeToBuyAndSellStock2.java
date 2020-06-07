package review.greedy;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
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
     */

    /**
     * 题目意思: 有一个价格数组,数组里第i个元素表示第i天的股票价格;
     * 设计一个算法,找出最大利润; 您不能同时进行多项交易(最多只能持有1股),每天可以进行许多次交易
     * 思路: 采用动态规划或贪心算法
     */

    /**
     * 采用贪心算法: 只要后一天的价格比前一天高,就在前一天买入,后一天卖出;反之则不操作;
     * @param prices
     */
    public int greedy(int[] prices) {
        int maxProfit = 0;
        for (int i=0;i<prices.length-1;i++) {
            if (prices[i] < prices[i+1]) {
                maxProfit += prices[i+1] - prices[i];
            }
        }
        return maxProfit;
    }
}

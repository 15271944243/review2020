package review.dp.backpack;

/**
 * https://leetcode.com/problems/perfect-squares/ No.279 完全平方数
 * @author: xiaoxiaoxiang
 * @date: 2021/2/26 14:33
 */
public class PerfectSquares {

    /**
     * Given an integer n, return the least number of perfect square numbers that sum to n.
     * A perfect square is an integer that is the square of an integer;
     * in other words, it is the product of some integer with itself.
     * For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
     *
     * Input: n = 12
     * Output: 3
     * Explanation: 12 = 4 + 4 + 4.
     *
     * Input: n = 13
     * Output: 2
     * Explanation: 13 = 4 + 9.
     *
     * Constraints:
     *
     * 1 <= n <= 10^4
     */

    /**
     * 题目意思:
     * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
     * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
     * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
     * 例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     *
     * 约束条件:
     * 1 <= n <= 10^4
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
        int n = 13;
        PerfectSquares demo = new PerfectSquares();
        int result = demo.numSquares(n);
    }

    /**
     * 该问题可以看作一个完全背包问题,物品的重量和质量都是 1 ~ (n开平方后向下取整), 选任意个物品,每个物品可以选任意次,
     * 放入背包,求正好放满背包时,最少使用的物品数
     * 1. dp[j]: 容量为[j]的背包,求正好放满背包时,最少使用的物品数
     * 2. 确定递推公式: dp[j] = min(dp[j], dp[j-(i*i)]+1)
     * 3. dp数组如何初始化: dp[0] = 0, dp[i] = Integer.MAX_VALUE
     * 4. 确定遍历顺序:  先遍历 1 ~ (n开平方后向下取整), 再遍历背包容量(正序遍历)
     * 5. 举例推导dp数组: n = 12, result = 3
     * @param n
     * @return
     */
    public int numSquares(int n) {
        // 物品为 1、2、3 ... c
        int c = (int) Math.sqrt(n);
        int[] dp = new int[n+1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= c; i++) {
            int s = i * i;
            for (int j = s; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - s] + 1);
            }
        }
        return dp[n];
    }
}

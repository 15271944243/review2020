package review.dp;

/**
 * https://leetcode.com/problems/fibonacci-number/ No.509
 * 斐波那契数
 * @author: xiaoxiaoxiang
 * @date: 2021/2/10 10:48
 */
public class Fibonacci {

    /**
     * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence,
     * such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
     *
     * F(0) = 0, F(1) = 1
     * F(n) = F(n - 1) + F(n - 2), for n > 1.
     * Given n, calculate F(n).
     *
     * Constraints:
     *
     * 0 <= n <= 30
     */

    /**
     * 题目意思: 计算斐波那契额数 F(n)
     *
     * 思路: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */
    public static void main(String[] args) {
        Fibonacci demo = new Fibonacci();
        int n = 10;
        int result = demo.fib(n);
    }

    /**
     * 1. dp[i]的定义为: 第i个数的斐波那契数值是dp[i]
     * 2. 递推公式 : dp[i] = dp[i-1] + dp[i-2]
     * 3. dp数组初始化: dp[0] = 1; dp[1] = 1
     * 4. 遍历顺序: dp[i]是依赖 dp[i-1] 和 dp[i-2],那么遍历的顺序一定是从前到后遍历的
     * 5. i = 10 时, dp数组为: 0 1 1 2 3 5 8 13 21 34 55
     * @param n
     * @return
     */
    public int fib(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            if (i < 2) {
                dp[i] = i;
                continue;
            }
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    /**
     * 解法2: 递归
     * @param n
     * @return
     */
    public int fib2(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

}

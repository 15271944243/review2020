package review.dp;

/**
 * https://leetcode.com/problems/integer-break/ No.343 整数拆分
 * @author: xiaoxiaoxiang
 * @date: 2021/2/18 15:06
 */
public class IntegerBreak {
    /**
     * Given a positive integer n, break it into the sum of at least two positive integers
     * and maximize the product of those integers. Return the maximum product you can get.
     *
     * Input: 2
     * Output: 1
     * Explanation: 2 = 1 + 1, 1 × 1 = 1.
     *
     * Input: 10
     * Output: 36
     * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
     *
     * Note: You may assume that n is not less than 2 and not larger than 58.
     */

    /**
     * 题目意思:
     * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
     *
     * 约束条件:
     * 你可以假设 n 不小于 2 且不大于 58
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
        IntegerBreak demo = new IntegerBreak();
        int n = 10;
        int result = demo.integerBreak2(n);
    }

    /**
     * 1. dp[i]的定义为: 整数 i 拆分后的最大乘积
     * 2. 递推公式: 根据思路一:递归的解法, dp[i] = max(dp[i], max(j * (i - j), j * dp[i - j]))
     * 3. dp数组初始化: dp[2] = 1
     * 4. 遍历顺序: 从 i = 3 开始循环,直到 i = n; 每层 i 循环时, j的范围是[1, i/2(向下取整)]
     * 5.
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3;i <= n;i++) {
            // 假设 n = 10, 那么 j=1,i=9 和j=9,i=1 结果一样,所以这里 j<=i/2
            for (int j=1;j<=i/2;j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    /**
     * 递归
     * 在leetcode上如果使用递归会超时
     * @param n
     * @return
     */
    public int integerBreak2(int n) {
        return recursive(n);
    }

    private int recursive(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        if (n == 4) {
            return 4;
        }
        int max = 0;
        for (int i = 1; i <= n / 2; i++) {
            max = Math.max(max, i * (n - i));
            max = Math.max(max, i * recursive(n - i));
        }
        return max;
    }
}

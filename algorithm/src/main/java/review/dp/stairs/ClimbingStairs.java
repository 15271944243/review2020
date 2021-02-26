package review.dp.stairs;

/**
 * https://leetcode.com/problems/climbing-stairs/ NO. 70 爬楼梯
 * @author: xiaoxiaoxiang
 * @date: 2020/8/21 11:24
 */
public class ClimbingStairs {

    /**
     * You are climbing a stair case. It takes n steps to reach to the top.
     *
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     *
     * Example 1:
     *
     * Input: 2
     * Output: 2
     * Explanation: There are two ways to climb to the top.
     * 1. 1 step + 1 step
     * 2. 2 steps
     * Example 2:
     *
     * Input: 3
     * Output: 3
     * Explanation: There are three ways to climb to the top.
     * 1. 1 step + 1 step + 1 step
     * 2. 1 step + 2 steps
     * 3. 2 steps + 1 step
     *
     * Constraints:
     *
     * 1 <= n <= 45
     */

    /**
     * 题目意思: 爬楼梯,总共需要n步爬到顶,每次爬1步或2步,有多少中不重复的方法爬到顶.
     * 约束: 1 <= n <= 45
     * 思路: 采用动态规划思想
     * 每次爬1步或2步,爬到第1层楼梯有一种方法,爬到2层楼梯有两种方法
     * 那如何爬到第3层,即第1层爬2步,或第2层爬1步,所以爬到第3层的次数 = 爬到第1层的次数 + 爬到第2层的次数
     * 以此类推,爬到第4层的次数 = 爬到第3层的次数 + 第2层的次数
     * ...
     * 爬到第n层的次数 = 爬到第n-2层的次数 + 爬到第n-1层的次数
     * 总结:
     * f(1) = 1
     * f(2) = 2
     * f(n) = f(n-2) + f(n-1)
     * 这里就直接依次从f(2)计算到f(n)的值,就能得出结果
     *
     * 题目升级: 每次可以爬1、2或者m 个台阶. 问有多少种不同的方法可以爬到楼顶呢
     */

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        int result = climbingStairs.climbStairs(10);
        // 总共需要n步爬到顶,每次爬1,2,...,m步,有多少中不重复的方法爬到顶
        int result2 = climbingStairs.climbStairs3(10, 3);
    }

    /**
     * 1. dp[i]的定义为: 爬到第i层的总方法数
     * 2. 递推公式 : dp[i] = dp[i-1] + dp[i-2]
     * 3. dp数组初始化: dp[1] = 0 dp[2] = 1
     * 4. 遍历顺序: dp[i]是依赖 dp[i-1]、dp[i-2],那么遍历的顺序一定是从前到后遍历的
     * 5. n = 10 时, dp数组为: 0 1 2 3 5 8 13 21 34 55 89
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n < 3) {
            return n;
        }
        int[] result = new int[n + 1];
        result[1] = 1;
        result[2] = 2;
        if (n > 2) {
            for (int i=3; i < result.length; i++) {
                result[i] = result[i - 2] + result[i - 1];
            }
        }
       return result[n];
    }

    /**
     * 在climbStairs上升级,不使用数组存储全部结果,而使用两个变量,记录n-1和n-2的结果
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        if (n < 3) {
            return n;
        }
        // n-1的结果
        int resultN1 = 2;
        // n-2的结果
        int resultN2 = 1;
        // n的结果
        int result = 0;
        for (int i=3; i < n + 1; i++) {
            result = resultN2 + resultN1;
            resultN2 = resultN1;
            resultN1 = result;
        }
        return result;
    }

    /**
     * 题目升级: 每次可以爬1、2或者m个台阶.问有多少种不同的方法可以爬到楼顶呢
     *
     * 可以转换为完全背包问题: 背包容量为n,物品重量(1,2,...,m),使用任意个物品,每个物品使用任意次,
     * 求正好放满背包的情况有多少次,注意这里求排列数,而不是组合数
     *
     * 1. dp[j]: 容量为[j]的背包,有多少种方案可以正好放满,求方案的排列数
     * 2. 确定递推公式: dp[j] += dp[j - weight[i]];
     * 3. dp数组如何初始化: dp[0] = 1
     * 4. 确定遍历顺序:  因为是求排列数而不是组合数,所以先遍历背包容量(正序遍历), 再遍历物品
     * 5. 举例推导dp数组: target = 4, nums = [1, 2, 3]; result = 7
     *
     * @param n 楼梯阶数
     * @param m 每次可以爬1、2或者m个台阶
     * @return
     */
    public int climbStairs3(int n, int m) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i >= j) {
                    dp[i] += dp[i - j];
                }
            }
        }
        return dp[n];
    }
}

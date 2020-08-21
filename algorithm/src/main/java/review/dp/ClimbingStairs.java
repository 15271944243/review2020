package review.dp;

/**
 * https://leetcode.com/problems/climbing-stairs/  70 爬楼梯
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
     * 每次爬1步或2步,那从第1步爬到第2步就只有1中方式,从第1步爬到第3步有两种方式.
     * 那如何爬到第4步,即第2步爬2步,或第3步爬1步,所以第1步爬到第4步的次数 = 第1步爬到第2步的次数 + 第1步爬到第3步的次数
     * 以此类推,第1步爬到第5步的次数 = 第1步爬到第3步的次数 + 第1步爬到第4步的次数
     * ...
     * 第1步爬到第n步的次数 = 第1步爬到第n-2步的次数 + 第1步爬到第n-1步的次数
     * 总结:
     * f(1) = 0
     * f(2) = 1
     * f(3) = 2
     * f(n) = f(n-2) + f(n-1)
     * 这里就直接依次从f(2)计算到f(n)的值,就能得出结果
     */

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        int result = climbingStairs.climbStairs(1);
        System.out.println(result);
    }

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
}

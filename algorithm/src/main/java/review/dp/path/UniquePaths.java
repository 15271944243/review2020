package review.dp.path;

/**
 * https://leetcode.com/problems/unique-paths/ No.62 不同路径
 * @author: xiaoxiaoxiang
 * @date: 2021/2/18 11:16
 */
public class UniquePaths {

    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * The robot can only move either down or right at any point in time.
     * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     *
     * How many possible unique paths are there?
     *
     * Input: m = 3, n = 7
     * Output: 28
     *
     * Input: m = 3, n = 2
     * Output: 3
     * Explanation:
     * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
     * 1. Right -> Down -> Down
     * 2. Down -> Down -> Right
     * 3. Down -> Right -> Down
     *
     * Input: m = 3, n = 3
     * Output: 6
     *
     * 1 <= m, n <= 100
     * It's guaranteed that the answer will be less than or equal to 2 * 109.
     */

    /**
     * 题目意思:
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     *
     * 约束条件:
     * 1 <= m, n <= 100
     * 题目数据保证答案小于等于 2 * 10^9
     *
     * 思路: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */

    public static void main(String[] args) {
        UniquePaths demo = new UniquePaths();
        int m = 3, n = 7;
        int result = demo.uniquePaths(m, n);
    }

    /**
     * 由于是一个网格,所以 dp[] 应该是一个二维数组
     * 1. dp[m,n]的定义为: 走到 dp[m,n] 的不同路径数量
     * 2. 递推公式: 由于每次只能向下或者向右移动一步, 即 dp[m,n] 只能从dp[m-1,n] 或 dp[m,n-1] 走到,
     * 所以 dp[m,n] = dp[m-1,n] + dp[m,n-1]
     * 3. dp数组初始化: dp[i,0] = 1; dp[0, j] = 1
     * 4. 遍历顺序: 两层循环, 从 1 ～ m-1, 1 ~ n-1 的遍历顺序
     * 5. m = 3, n = 3, 结果是 dp[2,2] = 6
     * @param m   行数
     * @param n   列数
     * @return   dp[m-1, n-1]
     */
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        int[][] dp = new int[m][n];
        for (int i = 0;i < m;i++) {
            dp[i][0] = 1;
        }
        for (int j = 0;j < n;j++) {
            dp[0][j] = 1;
        }
        for (int i = 1;i < m;i++) {
            for (int j = 1;j < n;j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m -1][n - 1];
    }
}

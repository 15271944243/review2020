package review.dp.path;

/**
 * https://leetcode.com/problems/unique-paths-ii/ No.63 不同路径2 (增加障碍物)
 * @author: xiaoxiaoxiang
 * @date: 2021/2/18 11:16
 */
public class UniquePaths2 {

    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * The robot can only move either down or right at any point in time.
     * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     *
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     * An obstacle and space is marked as 1 and 0 respectively in the grid.
     *
     * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
     * Output: 2
     * Explanation: There is one obstacle in the middle of the 3x3 grid above.
     * There are two ways to reach the bottom-right corner:
     * 1. Right -> Right -> Down -> Down
     * 2. Down -> Down -> Right -> Right
     *
     * Input: obstacleGrid = [[0,1],[0,0]]
     * Output: 1
     *
     * Constraints:
     *
     * m == obstacleGrid.length
     * n == obstacleGrid[i].length
     * 1 <= m, n <= 100
     * obstacleGrid[i][j] is 0 or 1.
     */

    /**
     * 题目意思:
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     *
     * 约束条件:
     * m == obstacleGrid.length
     * n == obstacleGrid[i].length
     * 1 <= m, n <= 100
     * obstacleGrid[i][j] is 0 or 1.
     *
     * 思路: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */

    public static void main(String[] args) {
        UniquePaths2 demo = new UniquePaths2();
        int[][] obstacleGrid = new int[][]{
                /*{0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}*/

                /*{0, 1},
                {0, 0}*/

                {1, 0}
        };
        int result = demo.uniquePathsWithObstacles(obstacleGrid);
    }

    /**
     * 由于是一个网格,所以 dp[] 应该是一个二维数组
     * 1. dp[m,n]的定义为: 走到 dp[m,n] 的不同路径数量
     * 2. 递推公式: 由于每次只能向下或者向右移动一步, 即 dp[m,n] 只能从dp[m-1,n] 或 dp[m,n-1] 走到,
     * 所以 dp[m,n] = dp[m-1,n] + dp[m,n-1]
     * 3. dp数组初始化: 因为存在障碍物, 所以令 dp[i,0] = 1; dp[0, j] = 1 时
     * 要添加限制条件 obstacleGrid[i, 0] == 1时, 后续都为0, dp[i ~ m-1, 0] = 0;
     * 同理 obstacleGrid[0, j] == 1时, 后续都为0, dp[0, j ~ n-1] = 0;
     * 4. 遍历顺序: 两层循环, 从 1 ～ m-1, 1 ~ n-1 的遍历顺序
     * 5.
     * @param obstacleGrid   障碍物数组
     * @return   dp[m-1, n-1]
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0;i < m;i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }
        for (int j = 0;j < n;j++) {
            if (obstacleGrid[0][j] == 1) {
                break;
            }
            dp[0][j] = 1;
        }
        for (int i = 1;i < m;i++) {
            for (int j = 1;j < n;j++) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m -1][n - 1];
    }
}

package review;

/**
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/ No.329 矩阵中的最长递增路径
 * @author: xiaoxiaoxiang
 * @date: 2021/5/24 15:53
 */
public class LongestIncreasingPathInMatrix {

    /**
     * 给定一个 m x n 整数矩阵 matrix,找出其中[最长递增路径]的长度
     *
     * 对于每个单元格,你可以往上，下，左，右四个方向移动. 你不能在对角线方向上移动或移动到边界外
     *
     *
     * 思路: 深度优先搜索 + 回溯
     */

    public static void main(String[] args) {
        LongestIncreasingPathInMatrix demo = new LongestIncreasingPathInMatrix();

        int[][] matrix = new int[][]{
                {9,9,4},
                {6,6,8},
                {2,1,1}
        };
        int result = demo.longestIncreasingPath(matrix);
    }

    public int longestIncreasingPath(int[][] matrix) {
        int result = 0;
        int[][] memory = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int r = dfs(matrix, i, j, memory);
                result = Math.max(result, r);
            }
        }
        return result + 1;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] memory) {
        if (memory[i][j] != 0) {
            return memory[i][j];
        }
        // 行
        int l1 = matrix[0].length;
        // 列
        int l2 = matrix.length;

        // 左边元素
        if (j > 0) {
            int left = matrix[i][j-1];
            if (matrix[i][j] < left) {
                memory[i][j] = Math.max(memory[i][j], dfs(matrix, i, j-1, memory) + 1);
            }
        }
        // 右边元素
        if (j + 1 < l1) {
            int right = matrix[i][j+1];
            if (matrix[i][j] < right) {
                memory[i][j] = Math.max(memory[i][j], dfs(matrix, i, j+1, memory) + 1);
            }
        }
        // 上边元素
        if (i > 0) {
            int up = matrix[i-1][j];
            if (matrix[i][j] < up) {
                memory[i][j] = Math.max(memory[i][j], dfs(matrix, i-1, j, memory) + 1);
            }
        }
        // 下边元素
        if (i + 1 < l2) {
            int down = matrix[i+1][j];
            if (matrix[i][j] < down) {
                memory[i][j] = Math.max(memory[i][j], dfs(matrix, i+1, j, memory) + 1);
            }
        }
        return memory[i][j];
    }

    /**
     * leetcode 上会超时
     * @param matrix
     * @param i
     * @param j
     * @param memory
     * @return
     */
    private int dfs2(int[][] matrix, int i, int j, int[][] memory) {
        if (memory[i][j] != -1) {
            return memory[i][j];
        }
        // 行
        int l1 = matrix[0].length;
        // 列
        int l2 = matrix.length;

        int leftCount = -1;
        int rightCount = -1;
        int upCount = -1;
        int downCount = -1;
        // 左边元素
        if (j > 0) {
            int left = matrix[i][j-1];
            if (matrix[i][j] < left) {
                leftCount = dfs(matrix, i, j-1, memory) + 1;
            }
        }
        // 右边元素
        if (j + 1 < l1) {
            int right = matrix[i][j+1];
            if (matrix[i][j] < right) {
                rightCount = dfs(matrix, i, j+1, memory) + 1;
            }
        }
        // 上边元素
        if (i > 0) {
            int up = matrix[i-1][j];
            if (matrix[i][j] < up) {
                upCount = dfs(matrix, i-1, j, memory) + 1;
            }
        }
        // 下边元素
        if (i + 1 < l2) {
            int down = matrix[i+1][j];
            if (matrix[i][j] < down) {
                downCount = dfs(matrix, i+1, j, memory) + 1;
            }
        }
        if (leftCount == -1 && rightCount == -1 && upCount == -1 && downCount == -1) {
            return 0;
        } else {
            return Math.max(leftCount, Math.max(rightCount, Math.max(upCount, downCount)));
        }
    }
}

package review.uf;


/**
 * https://leetcode.com/problems/number-of-islands/ No. 200 岛屿数量
 * @author: xiaoxiaoxiang
 * @date: 2021/3/12 10:10
 */
public class NumberOfIslands {

    /**
     * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
     * return the number of islands.
     *
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
     * You may assume all four edges of the grid are all surrounded by water.
     *
     * Input: grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * Output: 1
     *
     * Input: grid = [
     *   ["1","1","0","0","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","1","0","0"],
     *   ["0","0","0","1","1"]
     * ]
     * Output: 3
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 300
     * grid[i][j] is '0' or '1'.
     */

    /**
     * 题目意思:
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格,请你计算网格中岛屿的数量
     *
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     *
     * 此外，你可以假设该网格的四条边均被水包围。
     *
     * 思路一: 广度/深度优先搜索
     * 思路二: 并查集
     */

    public static void main(String[] args) {
        NumberOfIslands demo = new NumberOfIslands();
        /*char[][] grid = new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };*/
        /*char[][] grid = new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };*/
        /*char[][] grid = new char[][]{
                {'1','1','1'},
                {'0','1','0'},
                {'1','1','1'}
        };*/
        char[][] grid = new char[][]{
                {'1','0','1','1','1'},
                {'1','0','1','0','1'},
                {'1','1','1','0','1'}
        };
        int result = demo.numIslands(grid);
        System.out.println(result);
    }

    /**
     * 按从上到下,从左到右的顺序,每个岛屿的第一个'1'为root,root的左右/上下相邻为子节点,以此类推,组成并查集
     * 最终在并查集里有多少个集合,就有多少个岛屿
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int l1 = grid.length;
        int l2 = grid[0].length;
        // 并查集长度
        int l = l1 * l2;
        // 按从上到下,从左到右的顺序,将二维数组grid映射到一维数组roots中
        int[] roots = new int[l];
        // 因为最终只需要找有多少个根节点,所以初始化为-1
        for (int i = 0; i < l; i++) {
            roots[i] = -1;
        }
        // 先判断该元素的上/左相邻元素是不是1
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                // 将二维数组的index转化为一维数组的index
                int n = i * l2 + j;
                if (grid[i][j] == '1') {
                    // 因为是从上到下,从左到右遍历grid
                    // 判断该元素的上/左相邻元素是不是1,如果是1,则设为当前元素的父节点
                    // 上相邻元素
                    int upNodeIndex = n - l2;
                    if (upNodeIndex >= 0 && roots[upNodeIndex] != -1) {
                        roots[n] = roots[upNodeIndex];
                        continue;
                    }
                    // 左相邻元素
                    if (n % l2 != 0 && roots[n - 1] != -1) {
                        roots[n] = roots[n - 1];
                        continue;
                    }
                    // 没有上和左相邻元素,则当前元素为根节点
                    roots[n] = n;
                }
            }
        }
        // 再补充该元素的右相邻元素是不是1的情况
        //  {'1','1','1'},
        //  {'0','1','0'},
        //  {'1','1','1'}  如果不补充的话,这一行的第1个1就当作了独立的1
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                // 将二维数组的index转化为一维数组的index
                int n = i * l2 + j;
                if (roots[n] == n && j + 1 < l2 && roots[n+1] != -1) {
                    roots[n] = roots[n+1];
                }
            }
        }
        // 遍历有多个个根节点,即为岛屿数
        int count = 0;
        for (int i = 0; i < roots.length; i++) {
            if (roots[i] == i) {
                count++;
            }
        }
        return count;
    }

    public int numIslands2(char[][] grid) {
        int l1 = grid.length;
        int l2 = grid[0].length;
        // 并查集长度
        int l = l1 * l2;
        // 按从上到下,从左到右的顺序,将二维数组grid映射到一维数组roots中
        int[] roots = new int[l];
        // 初始化
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                // 将二维数组的index转化为一维数组的index
                int n = i * l2 + j;
                // 初始化
                roots[n] = grid[i][j] == '1' ? n : -1;
            }
        }
        // 整理集合关系,从上到下,从左到右遍历grid
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                // 将二维数组的index转化为一维数组的index
                int n = i * l2 + j;
                if (roots[n] == -1) {
                    continue;
                }
                // 判断该元素的上/左/右相邻元素是不是1

            }
        }

        // 先判断该元素的上/左相邻元素是不是1
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                // 将二维数组的index转化为一维数组的index
                int n = i * l2 + j;
                if (grid[i][j] == '1') {
                    // 因为是从上到下,从左到右遍历grid
                    // 判断该元素的上/左相邻元素是不是1,如果是1,则设为当前元素的父节点
                    // 上相邻元素
                    int upNodeIndex = n - l2;
                    if (upNodeIndex >= 0 && roots[upNodeIndex] != -1) {
                        roots[n] = roots[upNodeIndex];
                        continue;
                    }
                    // 左相邻元素
                    if (n % l2 != 0 && roots[n - 1] != -1) {
                        roots[n] = roots[n - 1];
                        continue;
                    }
                    // 没有上和左相邻元素,则当前元素为根节点
                    roots[n] = n;
                }
            }
        }
        // 再补充该元素的右相邻元素是不是1的情况
        //  {'1','1','1'},
        //  {'0','1','0'},
        //  {'1','1','1'}  如果不补充的话,这一行的第1个1就当作了独立的1
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                // 将二维数组的index转化为一维数组的index
                int n = i * l2 + j;
            }
        }


        // 遍历有多个个根节点,即为岛屿数

        int count = 0;
        for (int i = 0; i < roots.length; i++) {
            if (roots[i] == i) {
                count++;
            }
        }
        return count;
    }
}

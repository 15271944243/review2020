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
        char[][] grid = new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };char[][] grid2 = new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
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
        /*char[][] grid = new char[][]{
                {'1','0','1','1','1'},
                {'1','0','1','0','1'},
                {'1','1','1','0','1'}
        };*/
        int result = demo.numIslands(grid);
        int result2 = demo.numIslands2(grid2);
        System.out.println(result);
        System.out.println(result2);
    }

    /**
     * 找到一个1后,以DFS(深度优先搜索)的方式将这个1的上下左右的1的数都改为0,统计总共有多少个1
     * 时间复杂度 O(m * n)
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int count = 0;
//        char[][] tmp = new char[grid.length][grid[0].length];
        // 内层数组还是相同的引用
//        System.arraycopy(grid, 0, tmp, 0, grid.length);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < grid.length && i > -1 && j < grid[0].length && j > -1) {
            if (grid[i][j] == '1') {
                grid[i][j] = '0';
                // 上
                dfs(grid, i-1, j);
                // 下
                dfs(grid, i+1, j);
                // 左
                dfs(grid, i, j-1);
                // 右
                dfs(grid, i, j+1);
            }
        }
    }

    /**
     * 组成并查集
     * 时间复杂度比DFS高
     * @param grid
     * @return
     */
    public int numIslands2(char[][] grid) {
        int l1 = grid.length;
        int l2 = grid[0].length;
        UnionFind unionFind = new UnionFind(grid);
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    int q = i * l2 + j;
                    // 上
                    if (i > 0 && grid[i-1][j] == '1') {
                        int p = (i - 1) * l2 + j;
                        unionFind.union(p, q);
                    }
                    // 下
                    if (i < l1 - 1 && grid[i+1][j] == '1') {
                        int p = (i + 1) * l2 + j;
                        unionFind.union(p, q);
                    }
                    // 左
                    if (j > 0 && grid[i][j-1] == '1') {
                        int p = i * l2 + j - 1;
                        unionFind.union(p, q);
                    }
                    // 右
                    if (j < l2 - 1 && grid[i][j+1] == '1') {
                        int p = i * l2 + j + 1;
                        unionFind.union(p, q);
                    }
                }
            }
        }
        return unionFind.getCount();
    }

    static class UnionFind {
        int[] roots;
        int count;
        public UnionFind(char[][] grid) {
            int l1 = grid.length;
            int l2 = grid[0].length;
            // 并查集长度
            int l = l1 * l2;
            // 按从上到下,从左到右的顺序,将二维数组grid映射到一维数组roots中
            this.roots = new int[l];
            // 初始化
            for (int i = 0; i < l1; i++) {
                for (int j = 0; j < l2; j++) {
                    // 将二维数组的index转化为一维数组的index
                    int n = i * l2 + j;
                    // 初始化
                    if (grid[i][j] == '1') {
                        this.roots[n] = n;
                        this.count++;
                    } else {
                        this.roots[n] = -1;
                    }
                }
            }
        }

        public void union(int p, int q) {
            int qroot = findRoot(q);
            int proot = findRoot(p);
            if (qroot != proot) {
                this.roots[proot] = qroot;
                this.count--;
            }
        }

        public int getCount() {
            return count;
        }

        private int findRoot(int i) {
            int root = i;
            // 找到根节点了
            while (root != roots[root]) {
                root = roots[root];
            }
            // 路径压缩优化,即路径扁平化,将当前节点指向根节点
            while (i != roots[i]) {
                int tmp = roots[i];
                roots[i] = root;
                i = tmp;
            }
            return root;
        }
    }
}

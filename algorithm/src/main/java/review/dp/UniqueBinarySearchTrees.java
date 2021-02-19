package review.dp;

/**
 * https://leetcode.com/problems/unique-binary-search-trees/ No.96
 * @author: xiaoxiaoxiang
 * @date: 2021/2/18 17:25
 */
public class UniqueBinarySearchTrees {
    /**
     * Given an integer n, return the number of structurally unique BST's (binary search trees)
     * which has exactly n nodes of unique values from 1 to n.
     *
     * Input: n = 3
     * Output: 5
     *
     * Input: n = 1
     * Output: 1
     *
     * Constraints:
     * 1 <= n <= 19
     */

    /**
     * 题目意思:
     * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
     *
     * 约束条件:
     * 1 <= n <= 19
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
        UniqueBinarySearchTrees demo = new UniqueBinarySearchTrees();
        int n = 10;
        int result = demo.numTrees(n);
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
    public int numTrees(int n) {
        return 0;
    }
}

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
        int n = 5;
        int result = demo.numTrees(n);
    }

    /**
     * 递推公式推导过程:
     * 定义两个函数：
     * 1. G(n): 长度为 n 的序列能构成的不同二叉搜索树的个数
     * 2. F(i, n): 以 i 为根、序列长度为 n 的不同二叉搜索树个数(1≤i≤n)
     *
     * 不同的二叉搜索树的总数 G(n)，是对遍历所有 i (1≤i≤n) 的 F(i, n) 之和
     *       n
     * G(n)= ∑ F(i,n)  且 G(0)=1,G(1)=1
     *      i=1
     * 给定序列 1⋯n,我们选择数字 i 作为根，则根为 i 的所有二叉搜索树的集合是左子树集合和右子树集合的笛卡尔积
     * 举例而言，创建以 3 为根、长度为 7 的不同二叉搜索树，整个序列是 [1, 2, 3, 4, 5, 6, 7]，
     * 我们需要从左子序列 [1, 2] 构建左子树，从右子序列 [4, 5, 6, 7] 构建右子树，然后将它们组合（即笛卡尔积）
     *
     * 对于这个例子，不同二叉搜索树的个数为 F(3, 7)。我们将 [1,2] 构建不同左子树的数量表示为 G(2),
     * 从 [4, 5, 6, 7] 构建不同右子树的数量表示为 G(4)，注意到 G(n) 和序列的内容无关，只和序列的长度有关。
     * 于是，F(3,7)=G(2) * G(4)。 因此，我们可以得到以下公式：
     *       n
     * G(n)= ∑ G(i-1) * G(n-i)
     *      i=1
     * 则G(n) 的值依赖于 G(0)⋯G(n−1)
     */

    /**
     * 1. dp[i]的定义为: 1到i为节点组成的二叉搜索树的个数
     * 2. 递推公式:
     *        n
     * dp[n]= ∑ dp[i-1] * dp[n-i]
     *       i=1
     * 3. dp数组初始化: dp[0] = 1;dp[1] = 1
     * 4. 遍历顺序: 从 i = 2 开始循环
     * 5. n = 4, 结果是 dp[4] = 14
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2;i <= n;i++) {
            for (int j = 1;j <= i;j++) {
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }
}

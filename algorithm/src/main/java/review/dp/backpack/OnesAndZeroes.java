package review.dp.backpack;

/**
 * https://leetcode.com/problems/ones-and-zeroes/ No.474 一和零
 * @author: xiaoxiaoxiang
 * @date: 2021/2/24 11:09
 */
public class OnesAndZeroes {

    /**
     * You are given an array of binary strings strs and two integers m and n.
     * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
     *
     * A set x is a subset of a set y if all elements of x are also elements of y.
     *
     * Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
     * Output: 4
     * Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
     * Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
     * {"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
     *
     * Input: strs = ["10","0","1"], m = 1, n = 1
     * Output: 2
     * Explanation: The largest subset is {"0", "1"}, so the answer is 2.
     *
     * Constraints:
     *
     * 1 <= strs.length <= 600
     * 1 <= strs[i].length <= 100
     * strs[i] consists only of digits '0' and '1'.
     * 1 <= m, n <= 100
     */

    /**
     * 题目意思:
     * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
     * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
     * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
     *
     * 约束条件:
     * 1 <= strs.length <= 600
     * 1 <= strs[i].length <= 100
     * strs[i] consists only of digits '0' and '1'.
     * 1 <= m, n <= 100
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
        OnesAndZeroes demo = new OnesAndZeroes();
        String[] strs = new String[]{"10","0001","111001","1","0"};
        int m = 3;
        int n = 3;
        int result = demo.findMaxForm(strs, m, n);
    }


    /**
     * 容量为 [m,n] 的背包(m为0的数量,n为1的数量),
     * 从 strs[] 里选 j 个元素能放入背包,求 j 的最大值, j <= strs.length
     * 即容量为[m, n]的背包最多能放入 j 个元素
     * 转换为 01 背包, strs.length 个物品, 每个物品的weight是[x, y], value 是1, 要放入容量为 [m, n] 的背包,求最大value
     *
     * 1. dp[i][j]: 容量为[i,j]的背包,能放入的最多的元素个数,即放入元素的最大价值
     * 2. 确定递推公式: 01背包的递推公式是 dp[j] = max(dp[j], dp[j - weight[i]] + value[i])
     * 这里 weight[i] = [zero[i], one[i]], value[i] = 1
     * 推出递推公式为 dp[i,j] = max(dp[i][j], dp[i - zero[t]][j - one[t]] + 1)
     * 3. dp数组如何初始化: dp[0][0] = 0
     * 4. 确定遍历顺序:  先遍历 strs[], 再遍历背包容量(倒序遍历)
     * 5. 举例推导dp数组
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        // 求 strs 中每个元素 1 和 0 的个数
        int[] one = new int[strs.length];
        int[] zero = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int oneCount = 0;
            int zeroCount = 0;
            char[] chars = str.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == '1') {
                    oneCount++;
                } else {
                    zeroCount++;
                }
            }
            one[i] = oneCount;
            zero[i] = zeroCount;
        }
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (int t = 0; t < strs.length; t++) {
            for (int i = m; i >= zero[t]; i--) {
                for (int j = n; j >= one[t]; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zero[t]][j - one[t]] + 1);
                }
            }
        }
        return dp[m][n];
    }
}

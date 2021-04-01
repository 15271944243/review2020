package review.dp.str;

/**
 * https://leetcode.com/problems/longest-common-subsequence/ No.1143 最长公共子序列
 * @author: xiaoxiaoxiang
 * @date: 2021/3/17 11:07
 */
public class LongestCommonSubsequence {

    /**
     * Given two strings text1 and text2, return the length of their longest common subsequence.
     * If there is no common subsequence, return 0.
     *
     * A subsequence of a string is a new string generated from the original string
     * with some characters (can be none) deleted without changing the relative order of the remaining characters.
     *
     * For example, "ace" is a subsequence of "abcde".
     * A common subsequence of two strings is a subsequence that is common to both strings.
     *
     * Input: text1 = "abcde", text2 = "ace"
     * Output: 3
     * Explanation: The longest common subsequence is "ace" and its length is 3.
     *
     * Input: text1 = "abc", text2 = "abc"
     * Output: 3
     * Explanation: The longest common subsequence is "abc" and its length is 3.
     *
     * Input: text1 = "abc", text2 = "def"
     * Output: 0
     * Explanation: There is no such common subsequence, so the result is 0.
     *
     * Constraints:
     * 1 <= text1.length, text2.length <= 1000
     * text1 and text2 consist of only lowercase English characters.
     */

    /**
     *  题目意思: 给定两个字符串 text1 和 text2,返回这两个字符串的最长公共子序列的长度
     *
     * 一个字符串的子序列是指这样一个新的字符串: 它是由原字符串在不改变字符的相对顺序的情况下删除某些字符(也可以不删除任何字符)后组成的新字符串
     * 例如,"ace" 是 "abcde" 的子序列,但 "aec" 不是 "abcde" 的子序列.两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列
     *
     * 若这两个字符串没有公共子序列,则返回 0
     *
     *  思路: 动态规划
     *  1. 确定dp数组(dp table)以及下标的含义
     *  2. 确定递推公式
     *  3. dp数组如何初始化
     *  4. 确定遍历顺序
     *  5. 举例推导dp数组
     */
    public static void main(String[] args) {
        LongestCommonSubsequence demo = new LongestCommonSubsequence();
//        String text1 = "abcde", text2 = "ace";
//        String text1 = "ezupkr", text2 = "ubmrapg";
        String text1 = "oxcpqrsvwf", text2 = "shmtulqrypy";
        int result = demo.longestCommonSubsequence(text1, text2);
        System.out.println(result);
    }

    /**
     *
     * 1. dp[i][j]的定义为: text1的0~i个字符与text2的0~j个字符的最长公共子序列的长度
     *
     * 2. 递推公式:
     * if (text1.charAt(k) == text2.charAt(t)) {
     *     dp[i][j] = dp[i-1][j-1] + 1;
     * } else {
     *     dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
     * }
     *
     * 最后结果就是 dp[i][j]
     * 3. dp数组初始化:
     * 4. 遍历顺序: 正序遍历
     * 5. 举例推导dp数组:
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1 == null ? 0 : text1.length()+1;
        int n = text2 == null ? 0 : text2.length()+1;
        int[][] dp = new int[m][n];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int k = i-1, t = j-1;
                if (text1.charAt(k) == text2.charAt(t)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }

            }
        }
        return dp[m-1][n-1];
    }
}

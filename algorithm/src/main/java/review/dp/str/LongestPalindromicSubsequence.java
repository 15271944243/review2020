package review.dp.str;

/**
 * https://leetcode.com/problems/longest-palindromic-subsequence/ No.516 最长回文子序列
 * @author: xiaoxiaoxiang
 * @date: 2021/4/15 09:00
 */
public class LongestPalindromicSubsequence {

    /**
     * Given a string s, find the longest palindromic subsequence's length in s.
     *
     * A subsequence is a sequence that can be derived from another sequence by
     * deleting some or no elements without changing the order of the remaining elements.
     *
     * Input: s = "bbbab"
     * Output: 4
     * Explanation: One possible longest palindromic subsequence is "bbbb".
     *
     * Input: s = "cbbd"
     * Output: 2
     * Explanation: One possible longest palindromic subsequence is "bb".
     *
     * 1 <= s.length <= 1000
     * s consists only of lowercase English letters.
     */

    /**
     * 题目意思: 给定一个字符串 s,找到其中最长的回文子序列,并返回该序列的长度
     *
     * 思路一: 暴力解法
     * 思路二: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */
    public static void main(String[] args) {
        LongestPalindromicSubsequence demo = new LongestPalindromicSubsequence();
        String s = "cbbd";
        int result = demo.longestPalindromeSubseq(s);
        System.out.println(result);
    }

    /**
     * 1. dp[i][j]的定义为: 字符串s 的 i-j 位的子字符串的最长回文子序列长度
     * 2. 递推公式:
     * if (s[i] == s[j]) {
     *     dp[i][j] = dp[i+1][j-1] + 2
     * } else {
     *     dp[i][j] = max(dp[i][j-1],dp[i+1][j])
     * }
     *
     * 3. dp数组初始化: i==j时,dp[i][j] = 1,否则 dp[i][j]=0
     * 4. 遍历顺序: i 依赖 i+1,j 依赖 j-1 所有 i 倒叙,j 正序遍历
     * 5. 举例推导dp数组:
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int l = s.length();
        int[][] dp = new int[l][l];
        for (int i = 0; i < l; i++) {
            dp[i][i] = 1;
        }
        for (int i = l - 1; i >=0; i--) {
            for (int j = i+1; j < l; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][l-1];
    }
}

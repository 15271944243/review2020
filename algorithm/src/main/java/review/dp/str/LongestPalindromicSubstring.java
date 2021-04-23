package review.dp.str;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/ No.5 最长回文子串
 * @author: xiaoxiaoxiang
 * @date: 2021/4/23 09:00
 */
public class LongestPalindromicSubstring {

    /**
     * Given a string s, return the longest palindromic substring in s.
     *
     * Input: s = "babad"
     * Output: "bab"
     * Note: "aba" is also a valid answer.
     *
     * Input: s = "cbbd"
     * Output: "bb"
     *
     * Input: s = "a"
     * Output: "a"
     *
     * Input: s = "ac"
     * Output: "a"
     *
     * 1 <= s.length <= 1000
     * s consist of only digits and English letters (lower-case and/or upper-case),
     */

    /**
     * 题目意思: 给你一个字符串 s,找到 s 中最长的回文子串
     *
     * 思路一: 找出所有回文子串,并取最长的那个,时间复杂度O(n)
     * 思路二: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */
    public static void main(String[] args) {
        String s = "babad";
        LongestPalindromicSubstring demo = new LongestPalindromicSubstring();
        String result = demo.longestPalindrome(s);
        System.out.println(result);
    }

    /**
     * 1. dp[i][j]的定义为: 字符串s 的第i位到第j位的子字符串是否是回文字符串
     *
     * 2. 递推公式:
     * if (s[i] == s[j] && (dp[i+1][j-1] || i+1 > j-1)) {
     *     dp[i][j] = true
     * }
     *
     * 3. dp数组初始化: i == j 时,dp[i][j] = true
     * 4. 遍历顺序: i根据 i+1,j 根据j-1推倒,所有 i 是倒叙,j是正序
     * 5. 举例推导dp数组:
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int l = s.length();
        boolean[][] dp = new boolean[l][l];
        for (int i = 0; i < l; i++) {
            dp[i][i] = true;
        }
        // 默认首字母是最长回文子串
        int resultLength = 1;
        int resultLeftIndex = 0;
        int resultRightIndex = 0;
        for (int i = l-1; i >= 0; i--) {
            for (int j = i+1; j < l; j++) {
                if (s.charAt(i) == s.charAt(j) && (dp[i+1][j-1] || i+1 > j-1)) {
                    dp[i][j] = true;
                    if (j - i + 1 > resultLength) {
                        resultLength = j - i + 1;
                        resultLeftIndex = i;
                        resultRightIndex = j;
                    }
                }
            }
        }
        return s.substring(resultLeftIndex, resultRightIndex + 1);
    }
}

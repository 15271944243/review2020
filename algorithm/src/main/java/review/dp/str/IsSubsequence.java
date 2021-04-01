package review.dp.str;

/**
 * https://leetcode.com/problems/is-subsequence/ No.392 判断子序列
 * @author: xiaoxiaoxiang
 * @date: 2021/3/25 09:56
 */
public class IsSubsequence {

    /**
     * Given two strings s and t, check if s is a subsequence of t.
     *
     * A subsequence of a string is a new string that is formed from the original string by
     * deleting some (can be none) of the characters without disturbing the relative positions
     * of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
     *
     * Input: s = "abc", t = "ahbgdc"
     * Output: true
     *
     * Input: s = "axc", t = "ahbgdc"
     * Output: false
     *
     * 0 <= s.length <= 100
     * 0 <= t.length <= 104
     * s and t consist only of lowercase English letters.
     *
     * Follow up: If there are lots of incoming s, say s1, s2, ..., sk where k >= 109,
     * and you want to check one by one to see if t has its subsequence.
     * In this scenario, how would you change your code?
     */

    /**
     * 题目意思: 给定字符串 s 和 t,判断 s 是否为 t 的子序列
     * 字符串的一个子序列是原始字符串删除一些(也可以不删除)字符而不改变剩余字符相对位置形成的新字符串
     * 例如,"ace"是"abcde"的一个子序列,而"aec"不是
     *
     * 进阶: 如果有大量输入的 S,称作 S1, S2, ... , Sk 其中 k >= 10亿,你需要依次检查它们是否为 T 的子序列
     * 在这种情况下，你会怎样改变代码?
     *
     * 思路一: 两重循环-遍历 时间复杂度O(n^2)
     * 思路二: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */
    public static void main(String[] args) {
        IsSubsequence demo = new IsSubsequence();
        String s = "abc", t = "ahbgdc";
        boolean result = demo.isSubsequence(s, t);
        System.out.println(result);
    }

    /**
     * 本题dp公式同 LongestCommonSubsequence No.1143 最长公共子序列
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int l1 = s == null || s.length() == 0 ? 1 : s.length() + 1;
        int l2 = t == null || t.length() == 0 ? 1 : t.length() + 1;
        if (l1 > l2) {
            return false;
        }
        int[][] dp = new int[l1][l2];
        boolean result = false;
        f: for (int i = 1; i < l1; i++) {
            for (int j = 1; j < l2; j++) {
                if (dp[i][j] + 1 == l1) {
                    result = true;
                    break f;
                }
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        if (!result && dp[l1-1][l2-1] + 1 == l1) {
            result = true;
        }
        return result;
    }
}

package review.dp.subseq;

/**
 * https://leetcode.com/problems/distinct-subsequences/ No.115 不同的子序列
 * @author: xiaoxiaoxiang
 * @date: 2021/3/30 08:47
 */
public class DistinctSubsequences {

    /**
     * Given two strings s and t, return the number of distinct subsequences of s which equals t.
     *
     * A string's subsequence is a new string formed from the original string by
     * deleting some (can be none) of the characters without disturbing the remaining characters' relative
     * positions. (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).
     *
     * It is guaranteed the answer fits on a 32-bit signed integer.
     *
     * Input: s = "rabbbit", t = "rabbit"
     * Output: 3
     * Explanation:
     * As shown below, there are 3 ways you can generate "rabbit" from S.
     * (上箭头符号 ^ 表示选取的字母)
     * rabbbit
     * ^^^^ ^^
     * rabbbit
     * ^^ ^^^^
     * rabbbit
     * ^^^ ^^^
     *
     * Input: s = "babgbag", t = "bag"
     * Output: 5
     * Explanation:
     * As shown below, there are 5 ways you can generate "bag" from S.
     * (上箭头符号 ^ 表示选取的字母)
     * babgbag
     * ^^ ^
     * babgbag
     * ^^    ^
     * babgbag
     * ^    ^^
     * babgbag
     *   ^  ^^
     * babgbag
     *     ^^^
     *
     * 1 <= s.length, t.length <= 1000
     * s and t consist of English letters.
     */

    /**
     * 题目意思: 给定一个字符串 s 和一个字符串 t,计算在 s 的子序列中 t 出现的个数。
     * 字符串的一个子序列 是指,通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串
     * （例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
     *
     * 题目数据保证答案符合 32 位带符号整数范围。
     *
     * 0 <= s.length, t.length <= 1000
     * s 和 t 由英文字母组成
     *
     * 思路一: 递归回溯
     * 思路二: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */
    public static void main(String[] args) {
        DistinctSubsequences demo = new DistinctSubsequences();
//        String s = "rabbbit", t = "rabbit";
        String s = "babgbag", t = "bag";
        int result = demo.numDistinct(s, t);
        System.out.println(result);
    }

    /**
     *
     * 1. dp[i][j]的定义为: 以i-1为结尾的s子序列中出现以j-1为结尾的t的个数为dp[i][j]
     *
     * 2. 递推公式:
     * - s[i - 1] 与 t[j - 1]相等,dp[i][j]可以有两部分组成
     *   1. 一部分是用s[i - 1]来匹配,那么个数为dp[i - 1][j - 1]
     *   2. 一部分是不用s[i - 1]来匹配,个数为dp[i - 1][j]
     * 即 dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
     *
     * - s[i - 1] 与 t[j - 1]不相等
     *  dp[i][j] = dp[i - 1][j];
     *
     * 3. dp数组初始化:  dp[i][0] = 1
     * 4. 遍历顺序: 正序遍历
     * 5. 举例推导dp数组:
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int l1 = s == null ? 0 : s.length() + 1;
        int l2 = t == null ? 0 : t.length() + 1;
        int[][] dp = new int[l1][l2];
        // 初始化 dp[0][j]
        for (int i = 0; i < l1; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < l1; i++) {
            for (int j = 1; j < l2; j++) {
                if (j > i) {
                    continue;
                }
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[l1-1][l2-1];
    }
}

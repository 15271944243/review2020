package review.dp.str;

/**
 * https://leetcode.com/problems/palindromic-substrings/ No.647 回文子串
 * @author: xiaoxiaoxiang
 * @date: 2021/4/14 08:49
 */
public class PalindromicSubstrings {

    /**
     * Given a string, your task is to count how many palindromic substrings in this string.
     *
     * The substrings with different start indexes or end indexes are counted as different
     * substrings even they consist of same characters.
     *
     * Input: "abc"
     * Output: 3
     * Explanation: Three palindromic strings: "a", "b", "c".
     *
     * Input: "aaa"
     * Output: 6
     * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
     *
     * Note:
     * The input string length won't exceed 1000.
     */

    /**
     * 题目意思: 给定一个字符串,你的任务是计算这个字符串中有多少个回文子串
     * 具有不同开始位置或结束位置的子串,即使是由相同的字符组成,也会被视作不同的子串
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
        PalindromicSubstrings demo = new PalindromicSubstrings();
        String s = "baaab";
        int result = demo.countSubstrings(s);
        System.out.println(result);
    }

    /**
     * 1. dp[i]的定义为: 字符串s的前 i 个字符([0,i]) 中的回文子串数量
     *
     * 2. 递推公式:
     * dp[i] = dp[i-1] + cal(i)
     * cal(i) : 计算第必定包含第i个字符的回文子串个数,即: ([0,i],[1,i],...,[i,i]) 中回文子串个数
     *
     * 辅助数组 record[i][j],记录子字符串[i,j]是否是回文子串;
     * 如果 s[i] == s[j], 且 record[i+1][j-1] = true,则 [i,j]就是回文串
     *
     * 3. dp数组初始化: 数组长度为0时,设为dp[0] = 1,
     * 4. 遍历顺序: 正序遍历
     * 5. 举例推导dp数组:
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length()];
        boolean[][] record = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            record[i][i] = true;
        }
        dp[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            dp[i] = dp[i-1] + cal(s, record, i);
        }
        return dp[s.length()-1];
    }

    /**
     * 计算第必定包含第i个字符的回文子串个数,即: ([0,i],[1,i],...,[i,i]) 中回文子串个数
     * @param s
     * @param record
     * @param i
     * @return
     */
    private int cal(String s, boolean[][] record, int i) {
        int count = 0;
        for (int j = 0; j <= i; j++) {
            boolean isPalindromic = isPalindromic(s, record, j, i);
            if (isPalindromic) {
                record[j][i] = true;
                count++;
            }
        }
        return count;
    }

    /**
     * 利用 record 判断是回文串,就不用遍历了,空间换时间
     * 辅助数组 record[i][j],记录子字符串[i,j]是否是回文子串;
     * 如果 s[i] == s[j], 且 record[i+1][j-1] = true,则 [i,j]就是回文串
     * @param s
     * @param record
     * @param start
     * @param end
     * @return
     */
    private boolean isPalindromic(String s, boolean[][] record, int start, int end) {
        if (s.charAt(start) == s.charAt(end)) {
            if (start + 1 <= end - 1) {
                return record[start + 1][end - 1];
            }
            return true;
        }
        return false;
    }

}

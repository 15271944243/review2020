package review.dp.backpack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/word-break/ No.139 单词拆分
 * @author: xiaoxiaoxiang
 * @date: 2021/3/1 10:53
 */
public class WordBreak {

    /**
     * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
     * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
     *
     * Note:
     *
     * The same word in the dictionary may be reused multiple times in the segmentation.
     * You may assume the dictionary does not contain duplicate words.
     *
     * Input: s = "leetcode", wordDict = ["leet", "code"]
     * Output: true
     * Explanation: Return true because "leetcode" can be segmented as "leet code".
     *
     * Input: s = "applepenapple", wordDict = ["apple", "pen"]
     * Output: true
     * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
     *              Note that you are allowed to reuse a dictionary word.
     *
     * nput: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * Output: false
     */

    /**
     * 题目意思:
     * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     * 说明：
     * 拆分时可以重复使用字典中的单词。
     * 你可以假设字典中没有重复的单词。
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
//        String s = "catsandog";
//        List<String> wordDict = new ArrayList<>(Arrays.asList(new String[]{"cats", "dog", "sand", "and", "cat"}));
        String s = "applepenapple";
        List<String> wordDict = new ArrayList<>(Arrays.asList(new String[]{"apple", "pen"}));
        WordBreak demo = new WordBreak();
        boolean result = demo.wordBreak(s, wordDict);
    }

    /**
     * 该问题可以看作一个完全背包问题
     * 1. dp[j]: 字符串 s 长度为j的话，dp[j]为true，表示可以拆分为一个或多个在 wordDict 中出现的单词。
     * 2. 确定递推公式: 如果 dp[i] = true (i < j),则如果 s.substring(i, j) 在 wordDict 中出现,则 dp[j] = true
     * 3. dp数组如何初始化: dp[0]就是推导的根基,所以dp[0] = true
     * 4. 确定遍历顺序:  因为要计算 s.substring(i, j) 在 wordDict 中出现,所以先遍历背包容量(正序遍历)比较方便, 再遍历物品
     * 5. 举例推导dp数组:
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        // 遍历背包
        for (int i = 1; i <= s.length(); i++) {
            // 遍历物品
            for (int j = 0; j < i; j++) {
                String str = s.substring(j, i);
                if (wordDict.contains(str) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        // 先遍历物品,再遍历背包的解法有缺陷,后续研究
        /*for (int i = 0; i <= s.length(); i++) {

            for (int j = 1; j <= s.length(); j++) {
                if (j <= i) {
                    continue;
                }
                String str = s.substring(i, j);
                if (wordDict.contains(str) && dp[j]) {
                    dp[j] = true;
                    break;
                }
            }
        }*/
        return dp[s.length()];
    }
}


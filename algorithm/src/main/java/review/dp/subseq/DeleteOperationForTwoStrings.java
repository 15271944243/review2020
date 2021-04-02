package review.dp.subseq;

/**
 * https://leetcode.com/problems/delete-operation-for-two-strings/ No.583 两个字符串的删除操作
 * @author: xiaoxiaoxiang
 * @date: 2021/4/1 10:45
 */
public class DeleteOperationForTwoStrings {


    /**
     * Given two strings word1 and word2, return the minimum number of steps
     * required to make word1 and word2 the same.
     * In one step, you can delete exactly one character in either string.
     *
     * Input: word1 = "sea", word2 = "eat"
     * Output: 2
     * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
     *
     * Input: word1 = "leetcode", word2 = "etco"
     * Output: 4
     *
     * 1 <= word1.length, word2.length <= 500
     * word1 and word2 consist of only lowercase English letters.
     */

    /**
     * 题目意思: 给定两个单词 word1 和 word2,找到使得 word1 和 word2 相同所需的最小步数,每步可以删除任意一个字符串中的一个字符
     *
     * 0 <= word1.length, word2.length <= 500
     * word1 和 word2 由小写英文字母组成
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
        DeleteOperationForTwoStrings demo = new DeleteOperationForTwoStrings();
//        String word1 = "sea", word2 = "eat";
        String word1 = "leetcode", word2 = "etco";
        int result = demo.minDistance(word1, word2);
        System.out.println(result);
    }

    /**
     *
     * 1. dp[i][j]的定义为: word1的前i个字符和word2前j个字符相同所需要的最小步数
     *
     * 2. 递推公式:
     * if (word1[i-1] == word2[j-1]) {
     *     dp[i][j] = dp[i-1][j-1]
     * } else {
     *      情况一：删word1[i - 1]，最少操作次数为dp[i - 1][j] + 1
     *      情况二：删word2[i - 1]，最少操作次数为dp[i][j - 1] + 1
     *      情况三：同时删word1[i - 1]和word2[i - 1]，操作的最少次数为dp[i - 1][j - 1] + 2
     *      个人理解情况三应该不用考虑
     *      dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + 1
     * }
     *
     * 3. dp数组初始化:
     * 4. 遍历顺序:
     * 5. 举例推导dp数组:
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int l1 = word1 == null ? 0 : word1.length() + 1;
        int l2 = word2 == null ? 0 : word2.length() + 1;
        int[][] dp = new int[l1][l2];
        // 初始化
        for (int i = 0; i < l1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < l2; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < l1; i++) {
            for (int j = 1; j < l2; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                     dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1;
                }
            }
        }
        return dp[l1-1][l2-1];
    }
}

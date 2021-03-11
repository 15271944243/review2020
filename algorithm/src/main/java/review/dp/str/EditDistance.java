package review.dp.str;

/**
 * https://leetcode.com/problems/edit-distance/ No. 72 编辑距离
 * @author: xiaoxiaoxiang
 * @date: 2021/3/10 11:24
 */
public class EditDistance {

    /**
     * Given two strings word1 and word2,
     * return the minimum number of operations required to convert word1 to word2.
     *
     * You have the following three operations permitted on a word:
     * - Insert a character
     * - Delete a character
     * - Replace a character
     *
     * Input: word1 = "horse", word2 = "ros"
     * Output: 3
     * Explanation:
     * horse -> rorse (replace 'h' with 'r')
     * rorse -> rose (remove 'r')
     * rose -> ros (remove 'e')
     *
     * Input: word1 = "intention", word2 = "execution"
     *
     * Output: 5
     * Explanation:
     * intention -> inention (remove 't')
     * inention -> enention (replace 'i' with 'e')
     * enention -> exention (replace 'n' with 'x')
     * exention -> exection (replace 'n' with 'c')
     * exection -> execution (insert 'u')
     *
     * Constraints:
     *
     * 0 <= word1.length, word2.length <= 500
     * word1 and word2 consist of lowercase English letters.
     */

    /**
     * 题目意思: 给你两个单词 word1 和 word2,请你计算出将 word1 转换成 word2 所使用的最少操作数
     * 你可以对一个单词进行如下三种操作：
     * - 插入一个字符
     * - 删除一个字符
     * - 替换一个字符
     *
     * 思路: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */
    public static void main(String[] args) {
        EditDistance demo = new EditDistance();
//        String word1 = "horse";
//        String word2 = "ros";
        String word1 = "intention";
        String word2 = "execution";
//        String word1 = "sea";
//        String word2 = "eat";
        int result = demo.minDistance(word1, word2);
        System.out.println(result);
    }


    /**
     * 1. dp[i][j]的定义为: word1 的前i个字符转化为 word2 的前j个字符所需要的最小操作
     * dp[i][j] 可以由 dp[i-1][j-1]、dp[i-1][j]、dp[i][j-1] 推导
     *
     * 如果 word1[i] == word1[j] 那么 dp[i][j] = dp[i-1][j-1]
     * 如果 word1[i] != word1[j] 那么
     * dp[i][j] = min(dp[i-1][j-1] + 1, dp[i][j-1], dp[i-1][j])
     *
     * 2. 递推公式:
     * if (word1[i] == word1[j]) {
     *     dp[i][j] = dp[i-1][j-1]
     * } else {
     *     dp[i][j] = min(dp[i-1][j-1] + 1, dp[i][j-1] + 1, dp[i-1][j] + 1)
     * }
     *
     * 3. dp数组初始化: dp[0][0] = 0, dp[i][0] = i, dp[0][j] = j
     * 4. 遍历顺序:
     * 5. 举例推导dp数组:
     *      | null | s | e | a |
     * null |   0  | 1 | 2 | 3 |
     *   e  |   1  | 1 | 1 | 2 |
     *   a  |   2  | 2 | 2 | 1 |
     *   t  |   3  | 3 | 3 | 2 |
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int l1 = word1 == null ? 0 : word1.length();
        int l2 = word2 == null ? 0 : word2.length();
        l1++;
        l2++;
        int[][] dp = new int[l1][l2];
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
                    dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]) + 1;
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1] + 1);
                }
            }
        }
        return dp[l1-1][l2-1];
    }

}

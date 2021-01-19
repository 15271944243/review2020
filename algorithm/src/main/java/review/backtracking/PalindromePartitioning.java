package review.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-partitioning/ No.131
 * 切割回文串
 * @author: xiaoxiaoxiang
 * @date: 2021/1/19 10:34
 */
public class PalindromePartitioning {

    /**
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * Return all possible palindrome partitioning of s.
     *
     * A palindrome string is a string that reads the same backward as forward.
     *
     * Example 1:
     *
     * Input: s = "aab"
     * Output: [["a","a","b"],["aa","b"]]
     * Example 2:
     *
     * Input: s = "a"
     * Output: [["a"]]
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 16
     * s contains only lowercase English letters.
     */

    /**
     * 题目意思: 将给定字符串s分割,分割后的所有字串都是回文串
     * 约束条件:
     * 1 <= 字符串s长度 <= 16
     * 字符串s仅包含小写英文字母
     *
     * 思路: 采用递归-回溯-剪枝的思想
     */

    public static void main(String[] args) {
        String s = "abbab";
        PalindromePartitioning demo = new PalindromePartitioning();
        List<List<String>> result = demo.partition(s);
    }

    /**
     * 采用递归-回溯-剪枝的思想
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backTracking(0, s, result, new ArrayList<>());
        return result;
    }

    /**
     *
     * @param start      需要使用start
     * @param s
     * @param result
     * @param tmpResult
     */
    private void backTracking(int start, String s, List<List<String>> result, List<String> tmpResult) {
        // 终止条件 如果起始位置已经大于s的大小,说明已经找到了一组分割方案了
        if (start == s.length()) {
            result.add(tmpResult);
            return;
        }
        for (int end = start; end < s.length(); end++) {
            String tmpStr = s.substring(start, end + 1);
            // 双指针法判断是否是回文串
            if (isPalindrome(tmpStr)) {
                // 是回文串
                List<String> tmpResult2 = new ArrayList<>(tmpResult);
                tmpResult2.add(tmpStr);
                backTracking(end + 1, s, result, tmpResult2);
            }
            // 不是回文串,返回
        }
    }

    private boolean isPalindrome(String tmpStr) {
        // 单个字符,肯定是回文串
        if (tmpStr.length() == 1) {
            return true;
        }
        // 多个字符,采用双指针法是否是回文串
        int head = 0;
        int tail = tmpStr.length() - 1;
        boolean flag = true;
        for (int i = 0; i < tmpStr.length(); i++) {
            if (head >= tail) {
                break;
            }
            if (tmpStr.charAt(head) != tmpStr.charAt(tail)) {
                flag = false;
                break;
            }
            head++;
            tail--;
        }
        return flag;
    }
}

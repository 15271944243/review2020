package review.slidingwindow;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/ No.3
 * @author: xiaoxiaoxiang
 * @date: 2022/5/21 19:35
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     *
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * 提示：
     * 0 <= s.length <= 5 * 104
     * s 由英文字母、数字、符号和空格组成
     */

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters demo = new LongestSubstringWithoutRepeatingCharacters();
        String s = "pwawkec";
//        String s = "dvdf";
//        int result = demo.lengthOfLongestSubstring(s);
        int result = demo.lengthOfLongestSubstring2(s);
        System.out.println(result);
    }

    /**
     * 思路1: 最直观的思路,即遍历
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        int maxLength = 1;
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i+1; j < s.length(); j++) {
                String str = s.substring(i, j);
                String c = s.substring(j, j+1);
                if (str.contains(c)) {
                    break;
                }
                int l = j - i + 1;
                maxLength = Math.max(maxLength, l);
            }
        }
        return maxLength;
    }

    /**
     * 思路2: 滑动窗口
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        // 滑动窗口起始位置
        int begin = 0;
        int maxLength = 1;
        for (int i = 1; i < s.length(); i++) {
            String str = s.substring(begin, i);
            String c = s.substring(i, i+1);
            if (str.contains(c)) {
                // 发现有重复元素时,窗口起始位置右移一位,窗口结束位置不能移动
                begin++;
                i--;
                continue;
            }
            int l = i - begin + 1;
            maxLength = Math.max(maxLength, l);
        }
        return maxLength;
    }
}

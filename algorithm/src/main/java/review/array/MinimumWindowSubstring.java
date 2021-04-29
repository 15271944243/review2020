package review.array;

/**
 * https://leetcode.com/problems/minimum-window-substring/ No.76 最小覆盖子串
 * @author: xiaoxiaoxiang
 * @date: 2021/4/28 10:23
 */
public class MinimumWindowSubstring {

    /**
     * Given two strings s and t, return the minimum window in s which will contain all the characters in t.
     * If there is no such window in s that covers all characters in t, return the empty string "".
     *
     * Note that If there is such a window, it is guaranteed that there will always be only one unique
     * minimum window in s.
     *
     * Input: s = "ADOBECODEBANC", t = "ABC"
     * Output: "BANC"
     *
     * Input: s = "a", t = "a"
     * Output: "a"
     *
     * 1 <= s.length, t.length <= 10^5
     * s and t consist of English letters.
     *
     * Follow up: Could you find an algorithm that runs in O(n) time?
     */

    /**
     * 题目意思: 给你一个字符串 s 、一个字符串 t
     * 返回 s 中涵盖 t 所有字符的最小子串.如果 s 中不存在涵盖 t 所有字符的子串,则返回空字符串 ""
     *
     * 注意: 如果 s 中存在这样的子串,我们保证它是唯一的答案
     *
     * 进阶: 你能设计一个在 o(n) 时间内解决此问题的算法吗？
     *
     * 思路一: 两层for循环遍历,时间复杂度 O(n^2)
     * 思路二: 滑动窗口,时间复杂度 O(n)
     */

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,1,1,1,1,1};
        int target = 11;
        MinimumSizeSubarraySum demo = new MinimumSizeSubarraySum();
        int result = demo.minSubArrayLen(target, nums);
        System.out.println(result);
    }

    public String minWindow(String s, String t) {
        return null;
    }
}

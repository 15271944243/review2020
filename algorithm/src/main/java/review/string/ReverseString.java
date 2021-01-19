package review.string;

import review.utils.StrUtils;

/**
 * https://leetcode.com/problems/reverse-string/ No.344
 * 反转字符串
 * @author: xiaoxiaoxiang
 * @date: 2021/1/19 10:57
 */
public class ReverseString {

    /**
     * Write a function that reverses a string. The input string is given as an array of characters char[].
     *
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     *
     * You may assume all the characters consist of printable ascii characters.
     *
     * Example 1:
     *
     * Input: ["h","e","l","l","o"]
     * Output: ["o","l","l","e","h"]
     * Example 2:
     *
     * Input: ["H","a","n","n","a","h"]
     * Output: ["h","a","n","n","a","H"]
     */

    /**
     * 题目意思: 反转字符串
     * 约束条件:
     * 不要分配额外内存空间
     *
     * 思路: 使用双指针,一个指向头,一个指向尾,两两交换,当头指针小于等于尾指针时停止循环
     */

    public static void main(String[] args) {
        char[] s = new char[] {'h', 'e', 'l', 'l', 'o'};
        ReverseString reverseString = new ReverseString();
        reverseString.reverseString(s);
    }


    public void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        int head = 0;
        int tail = s.length - 1;
        for (int i=0; i < s.length; i++) {
            // 当头指针小于等于尾指针时停止循环
            if (head >= tail) {
                return;
            }
            StrUtils.swap(s, head, tail);
            head++;
            tail--;
        }
    }
}

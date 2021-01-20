package review.backtracking;

import java.util.List;

/**
 * https://leetcode.com/problems/restore-ip-addresses/ No.93
 *
 * 复原IP地址
 * @author: xiaoxiaoxiang
 * @date: 2021/1/20 17:53
 */
public class RestoreIPAddresses {

    /**
     * Given a string s containing only digits, return all possible valid IP addresses that can be obtained from s.
     * You can return them in any order.
     *
     * A valid IP address consists of exactly four integers, each integer is between 0 and 255,
     * separated by single dots and cannot have leading zeros.
     * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses and "0.011.255.245",
     * "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
     *
     * Example 1:
     *
     * Input: s = "25525511135"
     * Output: ["255.255.11.135","255.255.111.35"]
     *
     * Example 2:
     *
     * Input: s = "0000"
     * Output: ["0.0.0.0"]
     *
     * Example 3:
     *
     * Input: s = "1111"
     * Output: ["1.1.1.1"]
     *
     * Example 4:
     *
     * Input: s = "010010"
     * Output: ["0.10.0.10","0.100.1.0"]
     *
     * Example 5:
     *
     * Input: s = "101023"
     * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
     *
     * Constraints:
     *
     * 0 <= s.length <= 3000
     * s consists of digits only.
     */

    /**
     * 题目意思: 将给定字符串s仅包含数字,返回该字符串所有合法的ip地址
     * 一个合法的ip地址仅包含4个 0 ~ 255 范围内的数字, 这4个数字被'.'分割且不能以0开头
     * 约束条件:
     * 0 <= 字符串s长度 <= 3000
     * 字符串s仅包含数字
     *
     * 思路: 采用递归-回溯-剪枝的思想
     */

    public List<String> restoreIpAddresses(String s) {

        // 一开始position为0
        int position = 0;
        return null;
    }

    /**
     * 每次切割长度 subLength: 1 - 3
     * 每次切割后位置 position = position + subLength
     * 递归过程中的中间结果 tmpResult
     */
    private void backtracking(int position, String s, String tmpResult, List<String> result) {
        // 结束条件
        if (position == s.length() - 1) {
            // 放入结果
            result.add(tmpResult);
            return;
        }

        for (int i = 1; i < 3; i++) {

        }
    }
}

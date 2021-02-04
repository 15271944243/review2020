package review.backtracking;

import java.util.List;

/**
 * https://leetcode.com/problems/increasing-subsequences/ No.491
 * 递增子序列
 * @author: xiaoxiaoxiang
 * @date: 2021/2/4 15:16
 */
public class IncreasingSubsequences {

    /**
     * Given an integer array, your task is to find all the different possible increasing subsequences
     * of the given array, and the length of an increasing subsequence should be at least 2.
     *
     * Example:
     *
     * Input: [4, 6, 7, 7]
     * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
     *
     * Constraints:
     *
     * The length of the given array will not exceed 15.
     * The range of integer in the given array is [-100,100].
     * The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
     */

    /**
     * 题目意思: 给定一个整型数组,你的任务是找到所有该数组的递增子序列,递增子序列的长度至少是2
     *
     * 约束条件:
     * 给定数组的长度不会超过15
     * 数组中的整数范围是 [-100,100]
     * 给定数组中可能包含重复数字,相等的数字应该被视为递增的一种情况
     *
     * 思路一: 采用递归-回溯-剪枝的思想
     */
    public static void main(String[] args) {
        IncreasingSubsequences demo = new IncreasingSubsequences();
        int[] nums = new int[]{4, 6, 7, 7};
        List<List<Integer>> result = demo.findSubsequences(nums);
    }

    public List<List<Integer>> findSubsequences(int[] nums) {

        return null;
    }
}

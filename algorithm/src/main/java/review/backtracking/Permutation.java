package review.backtracking;

import java.util.List;

/**
 * https://leetcode.com/problems/permutations/ No.46
 * 全排列
 * @author: xiaoxiaoxiang
 * @date: 2021/2/6 17:40
 */
public class Permutation {

    /**
     * Given an array nums of distinct integers, return all the possible permutations.
     * You can return the answer in any order.
     *
     * Example 1:
     *
     * Input: nums = [1,2,3]
     * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * Example 2:
     *
     * Input: nums = [0,1]
     * Output: [[0,1],[1,0]]
     * Example 3:
     *
     * Input: nums = [1]
     * Output: [[1]]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 6
     * -10 <= nums[i] <= 10
     * All the integers of nums are unique.
     */

    /**
     * 给定一个没有重复数字的序列,返回其所有可能的全排列
     * 约束条件:
     * 1 <= nums.length <= 6
     * -10 <= nums[i] <= 10
     * nums内的无重复元素
     *
     * 思路: 采用递归-回溯-剪枝的思想
     *
     * https://mp.weixin.qq.com/s?__biz=MzUxNjY5NTYxNA==&mid=2247485493&idx=1&sn=2b5a4e977fb2a2635859bd0cc831db64&scene=21#wechat_redirect
     */

    public static void main(String[] args) {
        Permutation demo = new Permutation();
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> result = demo.permute(nums);
    }

    public List<List<Integer>> permute(int[] nums) {
        return null;
    }
}
package review.backtracking;

import java.util.ArrayList;
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
     * 题目意思: 给定一个没有重复数字的序列,返回其所有可能的全排列
     * 约束条件:
     * 1 <= nums.length <= 6
     * -10 <= nums[i] <= 10
     * nums内的无重复元素
     *
     * 思路: 采用递归-回溯-剪枝的思想
     */

    public static void main(String[] args) {
        Permutation demo = new Permutation();
//        int[] nums = new int[]{0, 1};
        int[] nums = new int[]{1};
//        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> result = demo.permute(nums);
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums != null && nums.length > 0) {
            boolean[] used = new  boolean[nums.length];
            List<Integer> tmpResult = new ArrayList<>();
            backtracking(nums, used, tmpResult, result);
        }
        return result;
    }

    /**
     * 排列是有序的，也就是说[1,2] 和[2,1] 是两个集合
     * 每层都是从0开始搜索
     * @param nums
     * @param used            记录已使用的元素索引
     * @param tmpResult
     * @param result
     */
    private void backtracking(int[] nums, boolean[] used, List<Integer> tmpResult, List<List<Integer>> result) {
        // 推出条件
        if (tmpResult.size() == nums.length) {
            result.add(new ArrayList<>(tmpResult));
            return;
        }
        // 因为是排列,所以每次都重头开始循环
        // 并且记录已使用的元素索引
        for (int i = 0; i < nums.length; i++) {
            // 已使用的元素不能再使用
            if (used[i]) {
                continue;
            }
            used[i] = true;
            tmpResult.add(nums[i]);
            backtracking(nums, used, tmpResult, result);
            used[i] = false;
            tmpResult.remove(tmpResult.size() - 1);
        }
    }
}
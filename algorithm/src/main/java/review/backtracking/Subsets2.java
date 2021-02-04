package review.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets-ii/ No.90
 * 求子集2
 * @author: xiaoxiaoxiang
 * @date: 2021/2/4 14:03
 */
public class Subsets2 {

    /**
     * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
     *
     * The solution set must not contain duplicate subsets. Return the solution in any order.
     *
     * Example 1:
     *
     * Input: nums = [1,2,2]
     * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
     *
     * Example 2:
     *
     * Input: nums = [0]
     * Output: [[],[0]]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     */

    /**
     * 题目意思: 给定一个可能包含重复元素的整数数组 nums,返回该数组所有可能的子集（幂集）
     * 解集不能包含重复的子集
     *
     * 约束条件:
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     *
     * 思路一: 采用递归-回溯-剪枝的思想
     * 注意去重,去重的思路其实跟 CombinationSum2 一样
     */

    public static void main(String[] args) {
        Subsets2 demo = new Subsets2();
        int[] nums = new int[]{1, 2, 2};
        List<List<Integer>> result = demo.subsetsWithDup(nums);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 空子集
        result.add(new ArrayList<>());
        if (nums != null && nums.length > 0) {
            // 先排序
            countingSort(nums);
            backtracking(nums, 0, new boolean[nums.length], new ArrayList<>(), result);
        }
        return result;
    }

    private void backtracking(int[] nums, int start, boolean[] used, List<Integer> tmpResult, List<List<Integer>> result) {
        // 循环结束即为递归的退出条件
        for (int i = start; i < nums.length; i++) {
            // 去重
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            tmpResult.add(nums[i]);
            used[i] = true;
            result.add(new ArrayList<>(tmpResult));
            backtracking(nums, i + 1, used, tmpResult, result);
            tmpResult.remove(tmpResult.size() - 1);
            used[i] = false;
        }
    }

    private void countingSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        // 1. 求最大值和最小值
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
            if (min > nums[i]) {
                min = nums[i];
            }
        }
        // 2. 创建计数数组
        int[] arr = new int[max - min + 1];
        for (int i = 0; i < nums.length; i++) {
            int index = nums[i] - min;
            arr[index]++;
        }
        // 3. 将计数数组的值有序放回原数组
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i]; j++) {
                nums[index] = i + min;
                index++;
            }
        }
    }
}

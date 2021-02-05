package review.backtracking;

import java.util.ArrayList;
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
     * 思路一: 采用递归-回溯-剪枝的思想;
     * 难点: 在不排序的情况下如何去重
     * 注: CombinationSum2、Subsets2 的去重是需要先排序的
     * 
     */
    public static void main(String[] args) {
        IncreasingSubsequences demo = new IncreasingSubsequences();
//        int[] nums = new int[]{100,90,80,70,60,50,60,70,80,90,100};
//        int[] nums = new int[]{4, 6, 7, 7};
//        int[] nums = new int[]{4, 3, 2, 1};
        int[] nums = new int[]{1,2,1,1,1,1,1};
        List<List<Integer>> result = demo.findSubsequences(nums);
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums != null && nums.length > 1) {
            backtracking(nums, 0, nums[0], new ArrayList<>(), result);
        }
        return result;
    }

    /**
     * 每层递归的循环里元素不能重复
     * @param nums
     * @param start
     * @param prevNum
     * @param tmpResult
     * @param result
     */
    private void backtracking(int[] nums, int start, int prevNum,
                              List<Integer> tmpResult, List<List<Integer>> result) {
        List<Integer> usedList = new ArrayList<>();
        // 终止条件就是循环结束
        for (int i = start; i < nums.length; i++) {
            // tmpResult.isEmpty() 是当第一个元素时,不使用prevNum判断
            if (tmpResult.isEmpty() || nums[i] >= prevNum) {
                // 去重, 每层递归的循环里元素不能重复
                if (usedList.contains(nums[i])) {
                    continue;
                }
                tmpResult.add(nums[i]);
                usedList.add(nums[i]);
                if (tmpResult.size() > 1) {
                    result.add(new ArrayList<>(tmpResult));
                }
                backtracking(nums, i + 1, nums[i], tmpResult, result);
                tmpResult.remove(tmpResult.size() - 1);
            }
        }
    }

    /**
     * 快速排序
     * @param nums
     * @param left
     * @param right
     */
    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivotIndex = partition(nums, left, right);
        quickSort(nums, 0, pivotIndex - 1);
        quickSort(nums, pivotIndex + 1, right);
    }

    /**
     * 思路: 找到比基准数大的数,把index记录下来,后面找到比基准数小的数,与之交换位置
     * @param nums
     * @param left
     * @param right
     */
    private int partition(int[] nums, int left, int right) {
        // 基准数
        int pivot = nums[left];
        int firstBiggerIndex = left + 1;
        for (int i = firstBiggerIndex; i <= right; i++) {
            // 思路: 找到比基准数大的数,把index记录下来,后面找到比基准数小的数,与之交换位置
            if (nums[i] < pivot) {
                // 不用原地交换
                if (firstBiggerIndex != i) {
                    swap(nums, firstBiggerIndex, i);
                }
                firstBiggerIndex++;
            }
        }
        // 将索引为left的基准数与 firstBiggerIndex - 1 交换位置
        swap(nums, firstBiggerIndex - 1, left);
        return firstBiggerIndex - 1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

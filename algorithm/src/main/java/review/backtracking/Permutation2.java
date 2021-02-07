package review.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations-ii/ No.47
 * 全排列2
 * @author: xiaoxiaoxiang
 * @date: 2021/2/6 17:40
 */
public class Permutation2 {

    /**
     * Given a collection of numbers, nums, that might contain duplicates,
     * return all possible unique permutations in any order.
     *
     * Example 1:
     *
     * Input: nums = [1,1,2]
     * Output:
     * [[1,1,2],
     *  [1,2,1],
     *  [2,1,1]]
     *
     * Example 2:
     *
     * Input: nums = [1,2,3]
     * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 8
     * -10 <= nums[i] <= 10
     */

    /**
     * 题目意思: 给定一个包含重复数字的集合 nums,按任意顺序,返回所有不重复的全排列
     * 约束条件:
     * 1 <= nums.length <= 6
     * -10 <= nums[i] <= 10
     * nums内的无重复元素
     *
     * 思路: 采用递归-回溯-剪枝的思想
     * 相对于 Permutation.java 的区别就是初始集合中含有重复元素,所以我们要注意去掉重复的排列
     */

    public static void main(String[] args) {
        Permutation2 demo = new Permutation2();
        int[] nums = new int[]{1,1,2};
//        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> result = demo.permuteUnique(nums);
        List<List<Integer>> result2 = demo.permuteUnique2(nums);
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
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
     *
     * 这里去除重复排列有两种思路
     * 1. 与IncreasingSubsequences.java 一致,即: 每层递归的循环里不允许使用重复元素
     * 2. 与 CombinationSum2.java 一致,即先排列,然后树层去重
     * 方法2的性能比方法1优秀
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
        // 用来去重
        List<Integer> usedList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 已使用的元素不能再使用
            if (used[i]) {
                continue;
            }
            // 不能使用重复元素
            if (usedList.contains(nums[i])) {
                continue;
            }
            used[i] = true;
            tmpResult.add(nums[i]);
            usedList.add(nums[i]);
            backtracking(nums, used, tmpResult, result);
            used[i] = false;
            tmpResult.remove(tmpResult.size() - 1);
        }
    }

    /**
     * 采用与 CombinationSum2.java 一致的去重思想,即先排列,然后树层去重
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums != null && nums.length > 0) {
            boolean[] used = new  boolean[nums.length];
            List<Integer> tmpResult = new ArrayList<>();
            quickSort(nums, 0, nums.length - 1);
            backtracking2(nums, used, tmpResult, result);
        }
        return result;
    }

    /**
     * 采用与 CombinationSum2.java 一致的去重思想,即先排列,然后树层去重
     * @param nums
     * @param used            记录已使用的元素索引
     * @param tmpResult
     * @param result
     */
    private void backtracking2(int[] nums, boolean[] used, List<Integer> tmpResult, List<List<Integer>> result) {
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
            // 重复元素不能使用
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            used[i] = true;
            tmpResult.add(nums[i]);
            backtracking2(nums, used, tmpResult, result);
            used[i] = false;
            tmpResult.remove(tmpResult.size() - 1);
        }
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = partition(nums, left, right);
        quickSort(nums, left , pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        int firstBiggerIndex = left + 1;
        int pivot = nums[left];
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                // 比基准数小的数,与第一个比基准数大的数交换
                // 不用自我交换
                if (i != firstBiggerIndex) {
                    swap(nums, i ,firstBiggerIndex);
                }
                firstBiggerIndex++;
            }
        }
        // 将基准数与最后一个比基准数小的数交换位置,达到分区的效果
        swap(nums, left,firstBiggerIndex - 1);
        return firstBiggerIndex - 1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
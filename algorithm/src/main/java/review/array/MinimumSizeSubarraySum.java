package review.array;

/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/ No.209 长度最小的子数组
 * @author: xiaoxiaoxiang
 * @date: 2021/4/26 11:32
 */
public class MinimumSizeSubarraySum {

    /**
     * Given an array of positive integers nums and a positive integer target, return the minimal length
     * of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to
     * target. If there is no such subarray, return 0 instead.
     *
     * Input: target = 7, nums = [2,3,1,2,4,3]
     * Output: 2
     * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
     *
     * Input: target = 4, nums = [1,4,4]
     * Output: 1
     *
     * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
     * Output: 0
     *
     * 1 <= target <= 10^9
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     *
     * Follow up: If you have figured out the O(n) solution, try coding another solution of which
     * the time complexity is O(n log(n)).
     */

    /**
     * 题目意思: 给定一个含有 n 个正整数的数组和一个正整数 target
     *
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ,
     * 并返回其长度.如果不存在符合条件的子数组,返回 0
     *
     * 进阶: 如果你已经实现 O(n) 时间复杂度的解法,请尝试设计一个 O(n log(n)) 时间复杂度的解法
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

    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int leftIndex = 0;
        int rightIndex = 0;
        int sum = 0;
        int result = 0;
        while (leftIndex < nums.length) {
            if (sum >= target) {
                // 记录 result
                if (result == 0) {
                    result = rightIndex - leftIndex;
                } else {
                    result = Math.min(rightIndex - leftIndex, result);
                }
                // 删除left指针的值
                sum -= nums[leftIndex];
                // 移动指针
                leftIndex++;
            } else {
                if (rightIndex == nums.length) {
                    break;
                }
                sum += nums[rightIndex];
                rightIndex++;
            }
        }
        return result;
    }
}

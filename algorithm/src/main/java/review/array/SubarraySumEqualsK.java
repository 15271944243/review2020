package review.array;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/ No.560 和为K的子数组
 * @author: xiaoxiaoxiang
 * @date: 2021/5/26 15:25
 */
public class SubarraySumEqualsK {

    /**
     * Given an array of integers nums and an integer k,
     * return the total number of continuous subarrays whose sum equals to k.
     *
     * Input: nums = [1,1,1], k = 2
     * Output: 2
     *
     * Input: nums = [1,2,3], k = 3
     * Output: 2
     *
     * 1 <= nums.length <= 2 * 10^4
     * -1000 <= nums[i] <= 1000
     * -10^7 <= k <= 10^7
     */

    /**
     * 题目意思:
     * 给定一个整数数组和一个整数 k,你需要找到该数组中和为 k 的连续的子数组的个数
     *
     * 思路: 递归 + 回溯
     */
    public static void main(String[] args) {
        SubarraySumEqualsK demo = new SubarraySumEqualsK();
        int[] nums = new int[]{1,-1,0};
        int k = 0;
        int result = demo.subarraySum2(nums, k);
    }

    public int subarraySum(int[] nums, int k) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int tmpResult = 0;
            if (nums[i] == k) {
                result++;
            }
            tmpResult += nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                tmpResult += nums[j];
                if (tmpResult == k) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * sum[j] 是 0 ~ j 的和
     * 所以 sum[j] - sum[i-1] == k,则 sum[i ~ j] == k
     * 要求连续
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // 处理第一个元素正好等于 k
        map.put(0, 1);
        int sum = 0;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // 要求连续子序列,且for循环正好是从左向右的顺序
            // 所以 i 应该视为子序列的最后一个元素
            // 所以这里应该是 sum - k
            if (map.containsKey(sum - k)) {
                result += map.get(sum - k);
            }
            // 放入
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return result;
    }
}

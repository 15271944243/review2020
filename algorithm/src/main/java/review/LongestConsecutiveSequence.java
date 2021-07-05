package review;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/ No.128 最长连续序列
 * @author: xiaoxiaoxiang
 * @date: 2021/5/26 09:39
 */
public class LongestConsecutiveSequence {

    /**
     * Given an unsorted array of integers nums, return the length of the longest consecutive
     * elements sequence.
     *
     * You must write an algorithm that runs in O(n) time.
     *
     * Input: nums = [100,4,200,1,3,2]
     * Output: 4
     * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
     *
     * Input: nums = [0,3,7,2,5,8,4,6,0,1]
     * Output: 9
     *
     * 0 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     */

    /**
     * 题目意思:
     * 给定一个未排序的整数数组 nums,找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度
     * 要求算法的时间复杂度为 O(n)
     *
     * 思路: 要求时间复杂度为 O(n),所有排序算法肯定不行
     * 利用 hash 表
     */

    public static void main(String[] args) {
        LongestConsecutiveSequence demo = new LongestConsecutiveSequence();
        int[] nums = new int[]{0,3,7,2,5,8,4,6,0,1};
        int result = demo.longestConsecutive(nums);
        System.out.println(result);
    }

    /**
     * 假设数组是 [99,100,101,102]
     * 只针对连续序列的最小值处理,即如果存在 99,100,101,102,则只处理 key = 99 这个
     * 因为对于 99,100,101,102 这些元素来说,在外层循环会被跳过,会在内层循环中处理
     * 所有总的时间复杂度是 O(n)
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], 1);
        }
        int result = 1;
        for (Map.Entry<Integer, Integer> entries : map.entrySet()) {
            Integer key = entries.getKey();
            if (map.containsKey(key - 1)) {
                continue;
            }
            // 连续序列的最小值,例如 99
            int k = key + 1;
            // 处理连续值,例如 100,101,102
            while (map.containsKey(k)) {
                map.put(k, map.get(k-1) + 1);
                if (result < map.get(k)) {
                    result = map.get(k);
                }
                k++;
            }
        }
        return result;
    }
}

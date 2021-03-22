package review.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/ No.347 前 K 个高频元素
 * @author: xiaoxiaoxiang
 * @date: 2021/3/22 08:57
 */
public class TopKFrequentElements {

    /**
     * Given an integer array nums and an integer k, return the k most frequent elements.
     * You may return the answer in any order.
     *
     * Input: nums = [1,1,1,2,2,3], k = 2
     * Output: [1,2]
     *
     * Input: nums = [1], k = 1
     * Output: [1]
     *
     * Constraints:
     * 1 <= nums.length <= 105
     * k is in the range [1, the number of unique elements in the array].
     * It is guaranteed that the answer is unique.
     *
     * Follow up: Your algorithm's time complexity must be better than O(n * log n), where n is the array's size.
     */

    /**
     * 题目意思: 给定一个非空的整数数组,返回其中出现频率前 k 高的元素
     *
     * 思路一: 使用排序算法对元素按照频率由高到低进行排序,然后再取前 k 个元素,要求时间复杂度不能超过O(n * log n),可以选择使用快排
     * 思路二: 遍历数组,计算每个元素出现的次数,然后再对次数排序(根据题目要求,要采取O(log n)时间复杂度的算法)
     */

    public static void main(String[] args) {
//        int[] nums = new int[]{1,1,1,2,2,3};
//        int k = 2;
        int[] nums = new int[]{4,1,-1,2,-1,2,3};
        int k = 2;
        TopKFrequentElements demo = new TopKFrequentElements();
        int[] result = demo.topKFrequent(nums, k);
    }

    /**
     * 思路二
     * 1. 先计算每个元素出现的次数,放入map中
     * 2. 然后将次数放入小顶堆中,弹出n-k个,剩下的就是频率前 k 高的元素
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        // 构造小顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            if (map.get(o1) < map.get(o2)) {
                return -1;
            } else if (map.get(o1) > map.get(o2)) {
                return 1;
            } else {
                return 0;
            }
        });
        for (Integer key : map.keySet()) {
            if (queue.size() < k) {
                queue.add(key);
            } else if (map.get(queue.peek()) < map.get(key)) {
                // 堆顶元素的次数小于 key的次数,弹出堆顶元素
                queue.poll();
                queue.add(key);
            }
        }
        int[] result = new int[queue.size()];
        int c = 0;
        while (!queue.isEmpty()) {
            result[c] = queue.poll();
            c++;
        }
        return result;
    }
}

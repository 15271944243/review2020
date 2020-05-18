package review;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 * @author: xiaoxiaoxiang
 * @date: 2020/5/15 23:09
 */
public class KthLargestElementInStream {

    /**
     * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
     *
     * Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add,
     * return the element representing the kth largest element in the stream.
     */

    /**
     * 题目意思: 在一个流里找到第K大的元素
     * 思路: 使用优先队列(小堆顶),即最小的元素在队列头,队列里存放最大的K个元素,则队列头即为第K大的元素
     */

    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[]{1,3,5,6});
        System.out.println(kthLargest.add(7));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
    }

    static class KthLargest {
        PriorityQueue<Integer> queue = null;
        int k = 0;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            this.queue = new PriorityQueue<>(k);
            for (int i=0;i<nums.length;i++) {
                add(nums[i]);
            }
        }

        public int add(int val) {
            if (queue.size() < k) {
                queue.offer(val);
            } else if (queue.peek() < val) {
                queue.poll();
                queue.offer(val);
            }
            return queue.peek();
        }
    }

}

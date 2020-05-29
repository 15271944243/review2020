package review;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 * @author: xiaoxiaoxiang
 * @date: 2020/5/18 23:15
 */
public class SlidingWindowMaximum {
    /**
     * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
     * You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
     */

    /**
     * 题目意思: 有一个数字数组,有一个大小为K的滑动窗口,从左往右滑动,每次滑动1个位置,每次滑动后,返回这个滑动窗口里的每一步的最大值数组
     * 思路一: 使用优先队列(大堆顶)
     * 思路二: 使用双端队列,滑动窗口每次滑动就往队列顶部插入一个元素,这时候要把它之后的比它小的元素都剔除
     * 思路二的时间复杂度更优,采用方案二实现
     */

    public static void main(String[] args) {
//        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int[] nums = new int[]{1,3,1,2,0,5};
        int k = 3;
        SlidingWindowMaximum fun = new SlidingWindowMaximum();
        int[] result = fun.maxSlidingWindow(nums, 3);
        System.out.println(111);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        // 存下标
        LinkedList<Integer> dequeue = new LinkedList<>();
        int j = 0;
        for (int i=0;i<nums.length;i++) {
            if (dequeue.peekFirst() != null && dequeue.peekFirst() == i - k) {
                // 队列第一个元素正好是因为窗口滑动要被移除的元素
                dequeue.pollFirst();
            }
            // 将比当前下标对应的数值小的下标都剔除
            Iterator<Integer> iterator = dequeue.iterator();
            while(iterator.hasNext()) {
                Integer index = iterator.next();
                if (nums[index] < nums[i]) {
                    iterator.remove();
                }
            }
            // 将下标写入
            dequeue.offerLast(i);
            if (i + 1 >= k) {
                // 写入最大值
                result[j++] = nums[dequeue.peekFirst()];
            }
        }
        return result;
    }
}

package review.array;

/**
 * https://leetcode.com/problems/remove-element/ No.27 Remove Element
 * @author: xiaoxiaoxiang
 * @date: 2021/4/23 09:53
 */
public class RemoveElement {

    /**
     * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
     *
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     *
     * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
     *
     * Clarification:
     *
     * Confused why the returned value is an integer but your answer is an array?
     *
     * Note that the input array is passed in by reference, which means a modification to the
     * input array will be known to the caller as well.
     *
     * Internally you can think of this:
     * ```
     * // nums is passed in by reference. (i.e., without making a copy)
     * int len = removeElement(nums, val);
     *
     * // any modification to nums in your function would be known by the caller.
     * // using the length returned by your function, it prints the first len elements.
     * for (int i = 0; i < len; i++) {
     *     print(nums[i]);
     * }
     * ```
     *
     * Input: nums = [3,2,2,3], val = 3
     * Output: 2, nums = [2,2]
     * Explanation: Your function should return length = 2, with the first two elements of nums being 2.
     * It doesn't matter what you leave beyond the returned length. For example if you return 2 with
     * nums = [2,2,3,3] or nums = [2,2,0,0], your answer will be accepted.
     *
     * Input: nums = [0,1,2,2,3,0,4,2], val = 2
     * Output: 5, nums = [0,1,4,0,3]
     * Explanation: Your function should return length = 5, with the first five elements of nums
     * containing 0, 1, 3, 0, and 4. Note that the order of those five elements can be arbitrary.
     * It doesn't matter what values are set beyond the returned length.
     *
     * 0 <= nums.length <= 100
     * 0 <= nums[i] <= 50
     * 0 <= val <= 100
     */

    /**
     * 题目意思: 给你一个数组 nums 和一个值 val,你需要原地移除所有数值等于 val 的元素,并返回移除后数组的新长度
     * 不要使用额外的数组空间,你必须仅使用 O(1) 额外空间并原地修改输入数组
     * 元素的顺序可以改变.你不需要考虑数组中超出新长度后面的元素
     *
     *
     * 思路一: 双指针
     *
     */
    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,2,3,0,4,2};
        int val = 2;
        RemoveElement demo = new RemoveElement();
        int result = demo.removeElement(nums, val);
        System.out.println(result);
    }

    /**
     * 双指针
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        // 慢指针 slow pointer 快指针 fast pointer
        int sp = 0;
        for (int fp = 0; fp < nums.length; fp++) {
            if (nums[fp] != val) {
                nums[sp] = nums[fp];
                sp++;
            }
        }
        return sp;
    }

    /**
     * 暴力遍历
     * @param nums
     * @param val
     * @return
     */
    public int removeElement2(int[] nums, int val) {
        int newLength = nums.length;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == val) {
                newLength--;
            }
            if (nums[i] == val || nums[i] == -1) {
                nums[i] = -1;
                int j = i + 1;
                while (j < nums.length) {
                    if (nums[j] == val || nums[j] == -1) {
                        j++;
                        continue;
                    }
                    nums[i] = nums[j];
                    nums[j] = -1;
                    break;
                }
            }
            i++;
        }
        return newLength;
    }
}

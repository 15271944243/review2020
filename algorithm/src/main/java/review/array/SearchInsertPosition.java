package review.array;

/**
 * https://leetcode.com/problems/search-insert-position/ No.35 搜索插入位置
 * @author: xiaoxiaoxiang
 * @date: 2021/3/26 09:33
 */
public class SearchInsertPosition {

    /**
     * Given a sorted array of distinct integers and a target value, return the index if the target is found.
     * If not, return the index where it would be if it were inserted in order.
     *
     * Input: nums = [1,3,5,6], target = 5
     * Output: 2
     *
     * Input: nums = [1,3,5,6], target = 2
     * Output: 1
     *
     * Input: nums = [1,3,5,6], target = 7
     * Output: 4
     *
     * Input: nums = [1,3,5,6], target = 0
     * Output: 0
     *
     * Input: nums = [1], target = 0
     * Output: 0
     *
     * 1 <= nums.length <= 10^4
     * -10^4 <= nums[i] <= 10^4
     * nums contains distinct values sorted in ascending order.
     * -10^4 <= target <= 10^4
     */

    /**
     * 题目意思: 给定一个排序数组和一个目标值,在数组中找到目标值,并返回其索引
     * 如果目标值不存在于数组中,返回它将会被按顺序插入的位置
     *
     * 你可以假设数组中无重复元素,且是递增数组
     *
     * 思路: 二分查找
     * 二分查找是 while(left < right) 还是 while(left <= right)，
     * 到底是right = middle呢，还是要right = middle - 1呢?
     * 阅读 review2020/algorithm/src/main/java/review/search/readme.md
     */
    public static void main(String[] args) {
        SearchInsertPosition demo = new SearchInsertPosition();
        int[] nums = new int[]{1,3,5,6};
        int target = 7;
//        int[] nums = new int[]{3,5,7,9,10};
//        int target = 8;
        int result = demo.searchInsert(nums, target);
        System.out.println(result);
    }

    private int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int result = -1;
        while (left <= right) {
            // 防止溢出,不要 (left + right) / 2
            int middle = ((right - left) >> 1) + left;
            if (nums[middle] == target) {
                result = middle;
                break;
            }
            if (nums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        if (result == -1) {
            // 分别处理如下四种情况
            // 目标值在数组所有元素之前  [0, -1]
            // 目标值等于数组中某一个元素  return middle;
            // 目标值插入数组中的位置 [left, right]，return  right + 1
            // 目标值在数组所有元素之后的情况 [left, right]， return right + 1
            result = right + 1;
        }
        return result;
    }
}

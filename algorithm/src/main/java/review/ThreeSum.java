package review;

import java.util.List;

/**
 * https://leetcode.cn/problems/3sum/ No.15 三数之和
 * @author: xiaoxiaoxiang
 * @date: 2022/6/8 8:20
 */
public class ThreeSum {

    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
     * 使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     *
     * 输入：nums = []
     * 输出：[]
     *
     * 输入：nums = [0]
     * 输出：[]
     *
     * 提示：
     *
     * 0 <= nums.length <= 3000
     * -105 <= nums[i] <= 105
     */



    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        ThreeSum func = new ThreeSum();
        List<List<Integer>> result = func.threeSum(nums);
        System.out.println(111);
    }

    public List<List<Integer>> threeSum(int[] nums) {

    }
}

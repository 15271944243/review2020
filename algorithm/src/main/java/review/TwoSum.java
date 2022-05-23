package review;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/ NO.1 两数之和
 * @author: xiaoxiaoxiang
 * @date: 2020/5/21 22:53
 */
public class TwoSum {

    /**
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     *
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     */
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        TwoSum func = new TwoSum();
        int[] result = func.twoSum(nums, target);
        System.out.println(111);
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]),i};
            }
            map.put(nums[i],i);
        }
        return null;
    }
}

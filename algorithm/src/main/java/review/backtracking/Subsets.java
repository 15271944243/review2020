package review.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets/ No.78
 * 求子集
 * @author: xiaoxiaoxiang
 * @date: 2021/2/4 10:43
 */
public class Subsets {

    /**
     * Given an integer array nums of unique elements, return all possible subsets (the power set).
     *
     * The solution set must not contain duplicate subsets. Return the solution in any order.
     *
     * Example 1:
     *
     * Input: nums = [1,2,3]
     * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     *
     * Example 2:
     *
     * Input: nums = [0]
     * Output: [[],[0]]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     * All the numbers of nums are unique.
     */

    /**
     * 题目意思: 给你一个整数数组 nums,数组中的元素互不相同.返回该数组所有可能的子集(幂集)
     * 解集不能包含重复的子集. 你可以按任意顺序返回解集
     *
     * 约束条件:
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     * nums 数组里的所有元素都是唯一的
     *
     * 思路一: 采用递归-回溯-剪枝的思想
     * 思路二: 二进制映射 + 循环
     */

    public static void main(String[] args) {
        Subsets demo = new Subsets();
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> result = demo.subsets(nums);
        List<List<Integer>> result2 = demo.subsets2(nums);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 空子集
        result.add(new ArrayList<>());
        if (nums != null && nums.length > 0) {
            backtracking(nums, 0, new ArrayList<>(), result);
        }
        return result;
    }

    /**
     * 思路一: 采用递归-回溯-剪枝的思想
     * @param nums
     * @param start
     * @param tmpResult
     * @param result
     */
    private void backtracking(int[] nums, int start, List<Integer> tmpResult, List<List<Integer>> result) {
        // 循环结束即为递归的退出条件
        // 不需要任何剪枝！因为子集就是要遍历整棵树
        for (int i = start; i < nums.length; i++) {
            tmpResult.add(nums[i]);
            result.add(new ArrayList<>(tmpResult));
            backtracking(nums, i + 1, tmpResult, result);
            tmpResult.remove(tmpResult.size() - 1);
        }
    }

    /**
     * 思路二: 二进制映射 + 枚举
     * 记原序列中元素的总数为 n, 原序列中的每个数字 ai 的状态可能有两种,即「在子集中」和「不在子集中」
     * 我们用 1 表示「在子集中」,0 表示「不在子集中」,那么每一个子集可以对应一个长度为 n 的 二进制数,
     * 第 i 位表示 ai 是否在子集中. 例如, n=3, a={5,2,9} 时
     *
     * |二进制数|十进制数|子集|
     * | 000  | 0 | {} |
     * | 001  | 1 | {9} |
     * | 010  | 2 | {2} |
     * | 011  | 3 | {2,9} |
     * | 100  | 4 | {5} |
     * | 101  | 5 | {5,9} |
     * | 110  | 6 | {5,2} |
     * | 111  | 7 | {5,2,9} |
     *
     *  所以我们可以枚举 0 ~ 2的n次方-1 范围的十进制数, 其所对应的二进制数在集合中的映射即为全部子集
     * @param nums
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<Integer> tmpResult = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        // 求出 2 的 nums.length 次方
        int l = nums.length;
        int n = 1 << l;
        // 开始遍历全部子集
        for (int i = 0; i < n; i++) {
            tmpResult.clear();
            for (int j = 0; j < l; j++) {
                // 把i当作二进制数,求i的第j位是不是1,如果是1,则是子集到一部分
                int r = i & 1 << j;
                if (r > 0) {
                    // 这里其实nums[j] 与 nums[l - j - 1]是一样的
                    tmpResult.add(nums[j]);
                }
            }
            result.add(new ArrayList<>(tmpResult));
        }
        return result;
    }
}

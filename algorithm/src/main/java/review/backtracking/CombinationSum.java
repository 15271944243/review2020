package review.backtracking;

import java.util.List;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/1/22 11:41
 */
public class CombinationSum {

    /**
     * Given an array of distinct integers candidates and a target integer target,
     * return a list of all unique combinations of candidates where the chosen numbers sum to target.
     * You may return the combinations in any order.
     *
     * The same number may be chosen from candidates an unlimited number of times.
     * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
     *
     * It is guaranteed that the number of unique combinations that sum up to target
     * is less than 150 combinations for the given input.
     *
     * Example 1:
     *
     * Input: candidates = [2,3,6,7], target = 7
     * Output: [[2,2,3],[7]]
     * Explanation:
     * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
     * 7 is a candidate, and 7 = 7.
     * These are the only two combinations.
     *
     * Example 2:
     *
     * Input: candidates = [2,3,5], target = 8
     * Output: [[2,2,2,2],[2,3,3],[3,5]]
     * Example 3:
     *
     * Input: candidates = [2], target = 1
     * Output: []
     * Example 4:
     *
     * Input: candidates = [1], target = 1
     * Output: [[1]]
     * Example 5:
     *
     * Input: candidates = [1], target = 2
     * Output: [[1,1]]
     *
     *
     * Constraints:
     *
     * 1 <= candidates.length <= 30
     * 1 <= candidates[i] <= 200
     * All elements of candidates are distinct.
     * 1 <= target <= 500
     */

    /**
     * 题目意思: 给定一个无重复元素的数组 candidates 和一个目标数 target ,找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的数字可以无限制重复被选取
     * 所有数字（包括 target）都是正整数, 解集不能包含重复的组合
     * 约束条件:
     * 1 <= candidates.length <= 30
     * 1 <= candidates[i] <= 200
     * candidates内的无重复元素
     * 1 <= target <= 500
     *
     * 思路: 采用递归-回溯-剪枝的思想
     */


    public List<List<Integer>> combinationSum(int[] candidates, int target) {

    }


}

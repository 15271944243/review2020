package review.backtracking;

import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-ii/ No.40
 * 组合总和2
 * @author: xiaoxiaoxiang
 * @date: 2021/1/29 10:44
 */
public class CombinationSum2 {

    /**
     * Given a collection of candidate numbers (candidates) and a target number (target),
     * find all unique combinations in candidates where the candidate numbers sum to target.
     *
     * Each number in candidates may only be used once in the combination.
     *
     * Note: The solution set must not contain duplicate combinations.
     *
     * Example 1:
     *
     * Input: candidates = [10,1,2,7,6,1,5], target = 8
     * Output:
     * [
     * [1,1,6],
     * [1,2,5],
     * [1,7],
     * [2,6]
     * ]
     *
     * Example 2:
     *
     * Input: candidates = [2,5,2,1,2], target = 5
     * Output:
     * [
     * [1,2,2],
     * [5]
     * ]
     *
     * Constraints:
     *
     * 1 <= candidates.length <= 100
     * 1 <= candidates[i] <= 50
     * 1 <= target <= 30
     */
    public static void main(String[] args) {
        CombinationSum2 demo = new CombinationSum2();
        int[] candidates = new int[]{2, 5, 2, 1, 2};
        int target = 5;
        List<List<Integer>> result = demo.combinationSum2(candidates, target);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

    }
}

package review.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/ No.39
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
     *
     * candidates[i] * m >= target & candidates[i] * (m - 1) < target
     * 找到每个 candidates[i] 对应的 m, 每个 candidates[i] 使用 0 ~ m 次
     * 当 candidates[i] 使用了 n 次时(0 <= n <= m), target = target - (candidates[i] * n)
     * 对 candidates[i + 1] ~  candidates[length - 1] 进行递归, 求出全部解
     *
     * 剪枝思路:
     * 1. 对 candidates 按从小到大顺序排序, 如果 candidates[i] * n + candidates[i + 1] > target,
     * 则 candidates[i] * n + candidates[i + 2] ～ candidates[m] 都不符合条件
     */
    public static void main(String[] args) {
        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 7;
//        int[] candidates = new int[]{2, 3, 5};
//        int target = 8;
        CombinationSum demo = new CombinationSum();
        List<List<Integer>> result = demo.combinationSum(candidates, target);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> tmpResult = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        backtracking(0, candidates, target, 0, tmpResult, result);
        return result;
    }

    /**
     * 递归-回溯-剪枝
     * @param start       其实位置
     * @param candidates
     * @param target
     * @param tmpSum      零时sum
     * @param tmpResult
     * @param result
     */
    private void backtracking(int start, int[] candidates, int target, int tmpSum, List<Integer> tmpResult, List<List<Integer>> result) {
        if (target == tmpSum) {
            // 终止条件,返回结果
            result.add(new ArrayList<>(tmpResult));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i == 2) {
                System.out.println(1);
            }
            // 剩余sum值
            int left = target - tmpSum;
            if (left < 0) {
                // 终止条件,无结果返回
                break;
            }
            int m = left / candidates[i];
            for (int j = m; j > 0; j--) {
                int val = candidates[i] * j;
                tmpSum += val;
                addNum(candidates[i], j, tmpResult);
                backtracking(i + 1, candidates, target, tmpSum, tmpResult, result);
                tmpSum -= val;
                removeNum(j, tmpResult);
            }
        }
    }

    private void addNum(int num, int count, List<Integer> tmpResult) {
        for (int i = 0; i < count; i++) {
            tmpResult.add(num);
        }
    }

    private void removeNum(int count, List<Integer> tmpResult) {
        int index = tmpResult.size() - 1;
        for (int i = 0; i < count; i++) {
            tmpResult.remove(index);
            index--;
        }
    }

}

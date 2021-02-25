package review.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-iii/ No.216 组合总和3
 * @author: xiaoxiaoxiang
 * @date: 2021/1/28 17:21
 */
public class CombinationSum3 {

    /**
     *
     * 2 <= k <= 9
     * 1 <= n <= 60
     *
     * Example 1:
     *
     * Input: k = 3, n = 7
     * Output: [[1,2,4]]
     * Explanation:
     * 1 + 2 + 4 = 7
     * There are no other valid combinations.
     *
     * Example 2:
     *
     * Input: k = 3, n = 9
     * Output: [[1,2,6],[1,3,5],[2,3,4]]
     * Explanation:
     * 1 + 2 + 6 = 9
     * 1 + 3 + 5 = 9
     * 2 + 3 + 4 = 9
     * There are no other valid combinations.
     */

    /**
     * 题目意思: 找出所有相加之和为 n 的 k 个数的组合. 组合中只允许含有 1 - 9 的正整数,并且每种组合中不存在重复的数字
     * 约束条件:
     * 2 <= k <= 9
     * 1 <= n <= 60
     * 解集不能包含重复的组合。
     *
     * 思路: 采用递归-回溯-剪枝的思想
     *
     */
    public static void main(String[] args) {
        CombinationSum3 demo = new CombinationSum3();
        List<List<Integer>> result = demo.combinationSum3(2, 18);
//        List<List<Integer>> result = demo.combinationSum3(3, 7);
//        List<List<Integer>> result2 = demo.combinationSum3(3, 9);
//        List<List<Integer>> result3 = demo.combinationSum3(4, 1);
//        List<List<Integer>> result4 = demo.combinationSum3(3, 2);
//        List<List<Integer>> result5 = demo.combinationSum3(9, 45);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (k < 1 || n < 1 || k > n) {
            return result;
        }
        List<Integer> tmpResult = new ArrayList<>();
        backtracking(k, n, 0, 1, 0, tmpResult, result);
        return result;
    }

    /**
     *
     * @param k
     * @param n
     * @param loopCount    递归次数,即递归k次后停止
     * @param num          当前选取的数字,不能重复选取
     * @param tmpSum       当前递归次数的数字总和
     * @param tmpResult    当前递归次数的数字集合
     * @param result
     */
    private void backtracking(int k, int n, int loopCount, int num, int tmpSum, List<Integer> tmpResult, List<List<Integer>> result) {
        if (loopCount == k) {
            if (tmpSum == n) {
                result.add(new ArrayList<>(tmpResult));
            }
            return;
        }
        // [4, 1, 2] 与 [1, 2, 4] 是同一种组合,所以 num 递增就行了,因为 num 递增, 所以自然就满足了每个数字只能用一次的条件
        while (num + tmpSum <= n && num < 10) {
            tmpResult.add(num);
            backtracking(k, n, loopCount + 1, num + 1,tmpSum + num, tmpResult, result);
            tmpResult.remove(tmpResult.size() - 1);
            num++;
        }
    }
}

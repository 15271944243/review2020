package review.backtracking;

import java.util.ArrayList;
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

    /**
     *
     * 题目意思: 给定一个数组 candidates 和一个目标数 target,找出 candidates 中所有可以使数字和为 target 的组合
     * 约束条件:
     * candidates 中的每个数字在每个组合中只能使用一次
     * 所有数字（包括目标数）都是正整数
     * 解集不能包含重复的组合
     *
     * 1 <= candidates.length <= 100
     * 1 <= candidates[i] <= 50
     * 1 <= target <= 30
     *
     * 思路: 采用递归-回溯-剪枝的思想
     *
     * 注意去重要先排序
     *
     * 剪枝思路: 同 CombinationSum.java
     * 1. 对 candidates 按从小到大顺序排序, 如果 candidates[i] * n + candidates[i + 1] > target,
     * 则 candidates[i] * n + candidates[i + 2] ～ candidates[m] 都不符合条件
     */

    public static void main(String[] args) {
        CombinationSum2 demo = new CombinationSum2();
        int[] candidates = new int[]{2, 5, 2, 1, 2};
//        int[] candidates = new int[]{10,1,2,7,6,1,5};
        int target = 5;
//        int target = 8;
        List<List<Integer>> result = demo.combinationSum2(candidates, target);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> tmpResult = new ArrayList<>();
        boolean[] used = new boolean[candidates.length];
        List<List<Integer>> result = new ArrayList<>();
        // 先排序
        countingSort(candidates);
        backtracking(candidates, target, 0, 0, used, tmpResult, result);
        return result;
    }

    /**
     * 对于 used 参数, 参考 https://mp.weixin.qq.com/s?__biz=MzUxNjY5NTYxNA==&mid=2247485360&idx=1&sn=2256a0f01a304d82a2b59252327f3edb&scene=21#wechat_redirect
     * 个人理解: 对于排序后的 candidates: [1,2,2,2,5] target = 5
     * 显然: 选出 [1,2,2,,] 这个组合后, 对于index 为 2 的 {2}, 就不需要再选取了,因为是重复的组合
     * 又因为 candidates 是有序的, 所以我们只需要判断 candidates[i] == candidates[i - 1] 的值;
     * 显然我们这里选取了两个2, 即对于结果展开的树形结构, 我们是选取的数值是在每一层不能重复, 而不是整颗树不能重复
     * 所以要添加 used 数组, 如果 used[i - 1] == true, 表示这个数值在整颗树已经使用, 但是当前层未使用
     * 而 used[i - 1] == false, 表示这个数值在当前层已使用
     *
     * @param candidates
     * @param target
     * @param numIndex
     * @param tmpSum
     * @param used            用来去重的数组
     * @param tmpResult
     * @param result
     */
    private void backtracking(int[] candidates, int target, int numIndex, int tmpSum, boolean[] used,
                              List<Integer> tmpResult, List<List<Integer>> result) {
        if (tmpSum == target) {
            // 终止条件, 已找到符合条件的组合
            result.add(new ArrayList<>(tmpResult));
            return;
        }
        if (numIndex == candidates.length) {
            // 终止条件
            return;
        }
        for (int i = numIndex; i < candidates.length; i++) {
            if (candidates[i] + tmpSum <= target) {
                if (i > 0 && !used[i - 1] && candidates[i] == candidates[i - 1]) {
                    // 去掉重复数字
                    continue;
                }
                tmpResult.add(candidates[i]);
                used[i] = true;
                backtracking(candidates, target, i + 1,
                        candidates[i] + tmpSum,
                        used, tmpResult, result);
                tmpResult.remove(tmpResult.size() - 1);
                used[i] = false;
            }
        }
    }

    /**
     * 因为 1 <= candidates[i] <= 50, 所以采用计数排序
     * @param candidates
     */
    private void countingSort(int[] candidates) {
        int max = candidates[0];
        int min = candidates[0];
        // 1. 找到最大值最小值
        for (int i=1; i < candidates.length; i++) {
            if (candidates[i] > max) {
                max = candidates[i];
            }
            if (candidates[i] < min) {
                min = candidates[i];
            }
        }
        // 2. 创建计数数组
        int[] arr = new int[max - min + 1];
        // 3. 开始计数
        for (int i = 0; i< candidates.length; i++) {
            int index = candidates[i] - min;
            arr[index] = arr[index] + 1;
        }
        // 4. 排序
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j=0; j < arr[i]; j++) {
                candidates[index] = min + i;
                index++;
            }
        }
    }
}

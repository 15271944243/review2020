package review.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combinations/ No.77
 * @author: xiaoxiaoxiang
 * @date: 2021/1/22 10:08
 */
public class Combinations {

    /**
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
     *
     * You may return the answer in any order.
     *
     * Example 1:
     *
     * Input: n = 4, k = 2
     * Output:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     *
     * Example 2:
     *
     * Input: n = 1, k = 1
     * Output: [[1]]
     *
     * Constraints:
     *
     * 1 <= n <= 20
     * 1 <= k <= n
     */

    /**
     * 题目意思: 给定 n 和 k两个数字,返回1 ~ n 中所有 k 个 数组的组合
     * 约束条件:
     * 1 <= n <= 20
     * 1 <= k <= n
     *
     * 思路: 采用递归-回溯-剪枝的思想
     */
    public static void main(String[] args) {
        Combinations demo = new Combinations();
        demo.combine(4, 2);
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (k < 1 || k > n) {
            return result;
        }
        backTracking(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private void backTracking(int start, int n, int k, List<Integer> tmpResult, List<List<Integer>> result) {
        // 结束条件
        if (tmpResult.size() == k) {
            // 放入结果
            result.add(new ArrayList<>(tmpResult));
            return;
        }
        for (int i = start; i <= n; i++) {
            // 剪枝
            if (i + k - 1 - tmpResult.size() > n) {
                // 已经选择的元素个数: tmpResult.size()
                // 还需要的元素个数为: k - tmpResult.size()
                // 如果从当前的数字到最后的数字n的个数不满足所需要的个数,就不用再查找了
                break;
            }
            tmpResult.add(i);
            backTracking(i + 1, n, k, tmpResult, result);
            tmpResult.remove(tmpResult.size() - 1);
        }
    }
}

package review.array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/fruit-into-baskets/ No.904 水果成篮
 * @author: xiaoxiaoxiang
 * @date: 2021/4/28 11:19
 */
public class FruitIntoBaskets {

    /**
     * In a row of trees, the i-th tree produces fruit with type tree[i].
     *
     * You start at any tree of your choice, then repeatedly perform the following steps:
     *
     * Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
     * Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
     * Note that you do not have any choice after the initial choice of starting tree: you must perform step 1,
     * then step 2, then back to step 1, then step 2, and so on until you stop.
     *
     * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only
     * carry one type of fruit each.
     *
     * What is the total amount of fruit you can collect with this procedure?
     *
     * Input: [1,2,1]
     * Output: 3
     * Explanation: We can collect [1,2,1].
     *
     * Input: [0,1,2,2]
     * Output: 3
     * Explanation: We can collect [1,2,2].
     * If we started at the first tree, we would only collect [0, 1].
     *
     * Input: [1,2,3,2,2]
     * Output: 4
     * Explanation: We can collect [2,3,2,2].
     * If we started at the first tree, we would only collect [1, 2].
     *
     * Input: [3,3,3,1,2,1,1,2,3,3,4]
     * Output: 5
     * Explanation: We can collect [1,2,1,1,2].
     * If we started at the first tree or the eighth tree, we would only collect 4 fruits.
     *
     * 1 <= tree.length <= 40000
     * 0 <= tree[i] < tree.length
     */

    /**
     * 题目意思: 在一排树中,第 i 棵树产生 tree[i] 型的水果
     * 你可以从你选择的任何树开始，然后重复执行以下步骤:
     *   1. 把这棵树上的水果放进你的篮子里.如果你做不到,就停下来
     *   2. 移动到当前树右侧的下一棵树.如果右边没有树,就停下来
     * 请注意,在选择一颗树后,你没有任何选择: 你必须执行步骤 1,然后执行步骤 2,然后返回步骤 1,然后执行步骤 2,依此类推,直至停止
     *
     * 你有两个篮子,每个篮子可以携带任何数量的水果,但你希望每个篮子只携带一种类型的水果
     *
     * 用这个程序你能收集的水果树的最大总量是多少?
     *
     * 思路一: 两层for循环遍历,时间复杂度O(n^2)
     * 思路二: 滑动窗口
     */

    public static void main(String[] args) {
        int[] tree = new int[]{4,7,7,0,8,3,8,2,5};
//        int[] tree = new int[]{3,3,3,1,2,1,1,2,3,3,4};
        FruitIntoBaskets demo = new FruitIntoBaskets();
//        int result = demo.totalFruit(tree);
        int result2 = demo.totalFruit2(tree);
        System.out.println(result2);
    }

    /**
     * 滑动窗口:
     * @param tree
     * @return
     */
    public int totalFruit2(int[] tree) {
        if (tree == null) {
            return 0;
        }
        if (tree.length < 2) {
            return tree.length;
        }
        // 两个篮子只能装两种水果
        Map<Integer, Integer> countMap = new HashMap<>();
        int result = 0;
        int i = 0;
        int j = -1;
        while (i < tree.length) {
            if (countMap.size() > 2) {
                countMap.put(tree[i], countMap.get(tree[i]) - 1);
                if (countMap.get(tree[i]) == 0) {
                    countMap.remove(tree[i]);
                }
                i++;
                continue;
            }
            j++;
            for (; j < tree.length; j++) {
                if (countMap.containsKey(tree[j])) {
                    countMap.put(tree[j], countMap.get(tree[j]) + 1);
                } else {
                    countMap.put(tree[j], 1);
                }
                if (countMap.size() < 3) {
                    result = Math.max(result, j- i + 1);
                } else {
                    break;
                }
            }
            if (j == tree.length) {
                break;
            }
        }
        return result;
    }

    /**
     * 两层for循环遍历,时间复杂度O(n^2)
     * @param tree
     * @return
     */
    public int totalFruit(int[] tree) {
        if (tree == null) {
            return 0;
        }
        if (tree.length < 2) {
            return tree.length;
        }
        // 两个篮子只能装两种水果
        int[] treeType = new int[2];
        treeType[0] = -1;
        int result = 0;
        for (int i = 0; i < tree.length; i++) {
            if (treeType[0] == tree[i]) {
                continue;
            }
            // 第一个篮子
            treeType[0] = tree[i];
            treeType[1] = -1;
            for (int j = i + 1; j < tree.length; j++) {
                if (treeType[0] == tree[j] || treeType[1] == tree[j]) {
                    result = Math.max(result, j - i + 1);
                } else if (treeType[0] != tree[j] && treeType[1] == -1) {
                    // 第二个篮子
                    treeType[1] = tree[j];
                    result = Math.max(result, j - i + 1);
                } else {
                    break;
                }
            }
        }
        return result;
    }
}

package review.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/triangle/ No. 120 三角形最小路径和
 * @author: xiaoxiaoxiang
 * @date: 2020/8/21 14:41
 */
public class Triangle {

    /**
     * Given a triangle, find the minimum path sum from top to bottom.
     * Each step you may move to adjacent numbers on the row below.
     *
     * For example, given the following triangle
     *
     * [
     *      [2],
     *     [3,4],
     *    [6,5,7],
     *   [4,1,8,3]
     * ]
     *
     * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
     *
     * Note:
     *
     * Bonus point if you are able to do this using only O(n) extra space,
     * where n is the total number of rows in the triangle.
     */

    /**
     * 题目意思: 求三角形的最小路径和,从上到下,每次只能跳到下一行相邻到数字,三角形的高度是n,使用O(n)额外空间
     *
     * 思路: 采用动态规划思想
     */

    public static void main(String[] args) {
        List<Integer> row1 = Arrays.asList(2);
        List<Integer> row2 = Arrays.asList(3, 4);
        List<Integer> row3 = Arrays.asList(6, 5, 7);
        List<Integer> row4 = Arrays.asList(4, 1, 8, 3);

        List<List<Integer>> triangleList = new ArrayList<>();
        triangleList.add(row1);
        triangleList.add(row2);
        triangleList.add(row3);
        triangleList.add(row4);

        Triangle triangle = new Triangle();
        int result = triangle.minimumTotal(triangleList);
        System.out.println(result);
    }

    /**
     * 最基础的递推思想,从下往上不断找每个节点的最小路径
     * 第n行第i个元素节点的最小路径: f(n, i) = min (f(n + 1, i), f(n + 1, i + 1)) + pos(n, i)
     * pos(n, i) 表示第n行第i个节点的值
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) {
            return 0;
        }
        // 三角形的高度
        int n = triangle.size();
        // 存储每一行的每个元素的最小路径,从下往上,上面一行的结果会覆盖下面一行的结果
        int[] result = new int[n];
        // 最底一行的节点最小路径即为它的值
        int k = 0;
        for (Integer num : triangle.get(n - 1)) {
            result[k] = num;
            k++;
        }
        // 从倒数第二行开始循环
        for (int i = n - 2; i > -1; i--) {
            List<Integer> row = triangle.get(i);
            int j = 0;
            for (Integer num : row) {
                // 第n行第j个元素节点的最小路径: f(n, i) = min (f(n + 1, i), f(n + 1, i + 1)) + pos(n, i)
                int min = Math.min(result[j], result[j+1]) + num;
                result[j] = min;
                j++;
            }
        }
        return result[0];
    }
}

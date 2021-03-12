package review.dp.path;

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
     * 思路一: 递归找出所有的路径和,比较大小
     * 思路二: 采用动态规划思想,递推找到最小路径和
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
        int result2 = triangle.minimumTotal2(triangleList);
        System.out.println(result);
        System.out.println(result2);
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

    /**
     * 1. dp[i][j]的定义为: 三角形第i行第j列元素的最小路径
     *
     * 2. 递推公式: dp[i][j] = min(dp[i-1][j-1], dp[i-1][j]) + nums[i][j] 注意边界
     *
     * 3. dp数组初始化: 初始化整个边界, 即dp[0][0] = nums[0][0]
     * dp[i][0] = dp[i-1][0] + nums[i][0],
     * dp[i][nums[i].length-1] = dp[i-1][nums[i-1].length-1] + nums[i][nums[i].length-1]
     * 4. 遍历顺序: 自顶向下遍历就行
     * 5. 举例推导dp数组:
     * @param triangle
     * @return
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) {
            return 0;
        }
        int l1 = triangle.size();
        int l2 = triangle.get(l1-1).size();
        int[][] dp = new int[l1][l2];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < l1; i++) {
            List<Integer> row = triangle.get(i);
            int l3 = row.size();
            for (int j = 0; j < l3; j++) {
                if (j == 0) {
                    // 左边界处理
                    dp[i][j] = dp[i-1][j] + row.get(j);
                } else if (j == l3 - 1) {
                    // 右边界处理
                    dp[i][j] = dp[i-1][j-1] + row.get(j);
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + row.get(j);
                }
            }
        }
        int[] bottom = dp[l1-1];
        int min = bottom[0];
        for (int i = 1; i < bottom.length; i++) {
            min = Math.min(min, bottom[i]);
        }
        return min;
    }

}

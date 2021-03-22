package review.dp.subseq;

/**
 * https://leetcode.com/problems/uncrossed-lines/ No.1035 不相交的线
 * @author: xiaoxiaoxiang
 * @date: 2021/3/22 09:53
 */
public class UncrossedLines {

    /**
     * We write the integers of A and B (in the order they are given) on two separate horizontal lines.
     *
     * Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:
     *
     * A[i] == B[j];
     * The line we draw does not intersect any other connecting (non-horizontal) line.
     * Note that a connecting lines cannot intersect even at the endpoints:
     * each number can only belong to one connecting line.
     *
     * Return the maximum number of connecting lines we can draw in this way.
     *
     * Input: A = [1,4,2], B = [1,2,4]
     * Output: 2
     * Explanation: We can draw 2 uncrossed lines as in the diagram.
     * We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line from A[2]=2 to B[1]=2.
     *
     * Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
     * Output: 3
     *
     * Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
     * Output: 2
     *
     * 1 <= A.length <= 500
     * 1 <= B.length <= 500
     * 1 <= A[i], B[i] <= 2000
     */

    /**
     * 题目意思:
     * 我们在两条独立的水平线上按给定的顺序写下 A 和 B 中的整数
     * 现在,我们可以绘制一些连接两个数字 A[i] 和 B[j] 的直线,只要 A[i] == B[j],且我们绘制的直线不与任何其他连线（非水平线）相交
     * 以这种方法绘制线条,并返回我们可以绘制的最大连线数
     *
     * 1 <= A.length <= 500
     * 1 <= B.length <= 500
     * 1 <= A[i], B[i] <= 2000
     *
     * 思路一: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */
    public static void main(String[] args) {
        UncrossedLines demo = new UncrossedLines();
//        int[] A = new int[]{1,4,2}, B = new int[]{1,2,4};
//        int[] A = new int[]{2,5,1,2,5}, B = new int[]{10,5,2,1,5,2};
        int[] A = new int[]{1,3,7,1,7,5}, B = new int[]{1,9,2,5,1};
        int result = demo.maxUncrossedLines(A, B);
    }

    /**
     * 根据题目意思:
     * 1. 每个数字上只能有一条连线
     * 2. 如果 A[i] == B[j] 已经有连线连, A[i + x] == B[j - y] 就不能再有连线,不然就相交了
     *
     * dp 公式:
     * 1. dp[i][j]的定义为: A 的 0~i个元素与 B 的 0~j 个元素的最大连线数
     *
     * 2. 递推公式:
     * if (A[i] == B[j]) {
     *     dp[i][j] = dp[i-1][j-1] + 1
     * } else {
     *     dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     * }
     * 3. dp数组初始化: 数组长度为0时,设为dp[0][0] = 0,
     * 4. 遍历顺序: 正序遍历
     * 5. 举例推导dp数组:
     * @param A
     * @param B
     * @return
     */
    public int maxUncrossedLines(int[] A, int[] B) {
        int m = A == null || A.length == 0 ? 0 : A.length+1;
        int n = B == null || B.length == 0 ? 0 : B.length+1;
        int[][] dp = new int[m][n];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (A[i-1] == B[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m-1][n-1];
    }
}

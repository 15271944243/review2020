package review.nqueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/n-queens/
 * @author: xiaoxiaoxiang
 * @date: 2020/6/21 16:02
 */
public class NQueens {
    /**
     * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
     *
     * Given an integer n, return all distinct solutions to the n-queens puzzle.
     *
     * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
     */

    /**
     * Example:
     *
     * Input: 4
     * Output: [
     *  [".Q..",  // Solution 1
     *   "...Q",
     *   "Q...",
     *   "..Q."],
     *
     *  ["..Q.",  // Solution 2
     *   "Q...",
     *   "...Q",
     *   ".Q.."]
     * ]
     * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
     */

    /**
     * 题目意思: 给出n皇后问题所有不同解决方案
     * 思路一: 使用DFS搜索所有解决方案;
     * 每有一个皇后被放置,则它的行col[i],列row[j],
     * 左斜线leftDiagonal[i+j],右斜线rightDiagonal[i-j]的值被置为1(由于数组下标不能为负数,所以可以加个n),即这个位置不能再放皇后;
     * 进行DFS搜索,查找可以放置皇后位置的所有情况
     */
    public static void main(String[] args) {
        NQueens q = new NQueens();
        List<List<String>> allSolution = q.solveNQueens(4);
        System.out.println(allSolution.size());
    }


    public List<List<String>> solveNQueens(int n) {
        List<List<String>> allSolution = new ArrayList<>();
        List<String> solutions = new ArrayList<>(n);
        // 行
        int[] col = new int[n];
        // 列
        int[] row = new int[n];
        // 左斜线
        int[] leftDiagonal = new int[n + n];
        // 右斜线(由于i-j可能为负数,所以可以加个n)
        int[] rightDiagonal = new int[n + n];
        resursion(n, 0, allSolution, solutions, col, row, leftDiagonal, rightDiagonal);
        return allSolution;
    }

    private void resursion(int n, int i, List<List<String>> allSolution, List<String> solutions,
                                         int[] col, int[] row, int[] leftDiagonal, int[] rightDiagonal) {
        if (i >= n) {
            allSolution.add(solutions);
            return;
        }

        for (int j=0;j<n;j++) {
            if (col[i] == 1 || row[j] == 1 || leftDiagonal[i+j] == 1 || rightDiagonal[i-j+n] == 1) {
                continue;
            } else {
                char[] chars =  new char[n];
                Arrays.fill(chars, '.');
                chars[j] = 'Q';
                List<String> tmp = new ArrayList<>(solutions);
                tmp.add(new String(chars));

                col[i] = 1;
                row[j] = 1;
                leftDiagonal[i+j] = 1;
                rightDiagonal[i-j+n] = 1;

                resursion(n, i + 1, allSolution, tmp,
                        col, row, leftDiagonal, rightDiagonal);

                // 恢复现场,方便后续使用
                col[i] = 0;
                row[j] = 0;
                leftDiagonal[i+j] = 0;
                rightDiagonal[i-j+n] = 0;
            }
        }
    }
}

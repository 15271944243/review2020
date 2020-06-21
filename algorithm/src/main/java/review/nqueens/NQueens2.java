package review.nqueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/n-queens-ii/
 * @author: xiaoxiaoxiang
 * @date: 2020/6/21 16:02
 */
public class NQueens2 {

    /**
     * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
     *
     * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
     */

    /**
     * Example:
     *
     * Input: 4
     * Output: 2
     */

    /**
     * 题目意思: 给出n皇后问题所有不同解决方案的数量
     * 思路一: 与NQueens.java相同
     */

    public static void main(String[] args) {
        NQueens2 q = new NQueens2();
        int totalNQueens = q.totalNQueens(4);
        System.out.println(totalNQueens);
    }


    public int totalNQueens(int n) {
        // 行
        int[] col = new int[n];
        // 列
        int[] row = new int[n];
        // 左斜线
        int[] leftDiagonal = new int[n + n];
        // 右斜线(由于i-j可能为负数,所以可以加个n)
        int[] rightDiagonal = new int[n + n];
        int allSolutionCount = resursion(n, 0, 0, col, row, leftDiagonal, rightDiagonal);
        return allSolutionCount;
    }

    private int resursion(int n, int i, int allSolutionCount,
                           int[] col, int[] row, int[] leftDiagonal, int[] rightDiagonal) {
        if (i >= n) {
            allSolutionCount++;
            return allSolutionCount;
        }

        for (int j=0;j<n;j++) {
            if (col[i] == 1 || row[j] == 1 || leftDiagonal[i+j] == 1 || rightDiagonal[i-j+n] == 1) {
                continue;
            } else {
                col[i] = 1;
                row[j] = 1;
                leftDiagonal[i+j] = 1;
                rightDiagonal[i-j+n] = 1;

                allSolutionCount = resursion(n, i + 1, allSolutionCount,
                        col, row, leftDiagonal, rightDiagonal);

                // 恢复现场,方便后续使用
                col[i] = 0;
                row[j] = 0;
                leftDiagonal[i+j] = 0;
                rightDiagonal[i-j+n] = 0;
            }

        }
        return allSolutionCount;
    }
}

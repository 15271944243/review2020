package review.sudoku;

/**
 * https://leetcode.com/problems/sudoku-solver/
 * @author: xiaoxiaoxiang
 * @date: 2020/6/26 23:02
 */
public class SudokuSolver {

    /**
     * Write a program to solve a Sudoku puzzle by filling the empty cells.
     *
     * A sudoku solution must satisfy all of the following rules:
     *
     * Each of the digits 1-9 must occur exactly once in each row.
     * Each of the digits 1-9 must occur exactly once in each column.
     * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
     * Empty cells are indicated by the character '.'.
     *
     * Note:
     *
     * The given board contain only digits 1-9 and the character '.'.
     * You may assume that the given Sudoku puzzle will have a single unique solution.
     * The given board size is always 9x9.
     */

    /**
     * 题目意思: 给定一个9x9的数独表格(空单元格用'.'表示),解决数独问题;
     *
     * 思路一: 使用递DFS针对每个单元格进行搜索,如果遇到不符合条件的单元格,则进行回溯
     * 思路二: 在思路一的基础上增加一些预处理条件,比如每个单元格可以填写的数字的范围,
     * 这样就能减少单元格的值是否合法的校验次数;
     * 使用位运算的思想,判断单元格的数字是否有效;
     * 思路三: 使用dancing links
     */


    public void solveSudoku(char[][] board) {

    }
}

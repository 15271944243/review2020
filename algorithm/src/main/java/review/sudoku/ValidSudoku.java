package review.sudoku;

/**
 * https://leetcode.com/problems/valid-sudoku/
 * @author: xiaoxiaoxiang
 * @date: 2020/6/26 23:02
 */
public class ValidSudoku {

    /**
     * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
     *
     * Each row must contain the digits 1-9 without repetition.
     * Each column must contain the digits 1-9 without repetition.
     * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
     *
     * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
     */

    /**
     * 题目意思: 给定一个9x9的数独表格(空单元格用'.'表示),判断它已填入数值的单元格是否合法;
     *
     * 思路一: 循环判断数值是否合法
     */

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'},
        };

        ValidSudoku validSudoku = new ValidSudoku();
        boolean result = validSudoku.isValidSudoku(board);
        System.out.println(result);
    }

    public boolean isValidSudoku(char[][] board) {
        int n = 9;
        // 每行的标记值,用来判断行内的单元格数字是否合法
        int[] colMark = new int[n];
        // 每列的标记值,用来判断列内的单元格数字是否合法
        int[] rowMark = new int[n];
        // 每个小九宫格的标记值,用来判断小九宫格内的单元格数字是否合法,小九宫格的索引顺序是从上倒下,从左到右
        int[] gridMark = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i][j];
                if (c != '.') {
                    // 单元格有数值
                    int cell = Character.getNumericValue(c);
                    // 小九宫格的索引
                    int k = calculateGridIndex(i, j);
                    boolean isValid = isValid(i, j, k, colMark, rowMark, gridMark, cell);
                    if (!isValid) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isValid(int i, int j, int k, int[] colMark, int[] rowMark, int[] gridMark, int value) {
        int tmp = 1 << (value - 1);
        int col = colMark[i];
        int row = rowMark[j];
        int grid = gridMark[k];
        if ((tmp & col) != 0) {
            return false;
        }

        if ((tmp & row) != 0) {
            return false;
        }

        if ((tmp & grid) != 0) {
            return false;
        }

        colMark[i] = tmp | col;
        rowMark[j] = tmp | row;
        gridMark[k] = tmp | grid;

        return true;
    }

    /**
     * 计算小九宫格的索引顺序:从上倒下,从左到右 (0~8)
     * @param i
     * @param j
     * @return
     */
    private int calculateGridIndex(int i, int j) {
        return  (i/3)*3 + j/3;
    }
}

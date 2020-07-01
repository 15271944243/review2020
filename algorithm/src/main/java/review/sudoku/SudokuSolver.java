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
        SudokuSolver sudokuSolver = new SudokuSolver();
        sudokuSolver.solveSudoku(board);
        for (int i = 0; i < 9; i++) {
            System.out.println();
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                System.out.print(Character.getNumericValue(c) + ",");
            }
        }
        System.out.println();
    }

//    public static void main(String[] args) {
//        int a = 0b011110111;
//        System.out.println("a=" + get32BitBinString(a));
//        int b = a ^ 0b111111111;
//
//        while (b != 0) {
//            int x = ((b - 1) & b) ^ b;
//            System.out.println(get32BitBinString(x));
//            b = b ^ x;
//            System.out.println(get32BitBinString(b));
//        }
//    }

    private static String get32BitBinString(int number) {
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < 32; i++){
            sBuilder.append(number & 1);
            number = number >>> 1;
        }
        return sBuilder.reverse().toString();
    }

    private void test(int[] marks) {
        for (int i = 0; i < marks.length; i++) {
            System.out.println(get32BitBinString(marks[i]));
        }
        System.out.println("--------------");
    }

    public void solveSudoku(char[][] board) {
        int n = 9;
        // 设置标记数据,每行、列、小九宫格都进行标记,把标记数据当作一个二进制数,如果第i-1位的数为1,表示i已经被占用

        // 每行的标记值,用来判断行内的单元格数字是否合法
        int[] colMark = new int[n];
        // 每列的标记值,用来判断列内的单元格数字是否合法
        int[] rowMark = new int[n];
        // 每个小九宫格的标记值,用来判断小九宫格内的单元格数字是否合法,小九宫格的索引顺序是从上倒下,从左到右
        int[] gridMark = new int[n];
        // 初始化
        init(board, n, colMark, rowMark, gridMark);

//        test(colMark);
//        test(rowMark);
//        test(gridMark);
        // TODO 找出当前所以空格可填数字个数最少的位置

        dfs(board, n, colMark, rowMark, gridMark, 0);
    }

    private void init(char[][] board, int n, int[] colMark, int[] rowMark, int[] gridMark) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i][j];
                if (c != '.') {
                    // 单元格有数值
                    int cell = Character.getNumericValue(c);
                    // 小九宫格的索引
                    int k = calculateGridIndex(i, j);
                    // 更新标记值
                    updateMark(colMark, rowMark, gridMark, cell, i ,j ,k);
                }
            }
        }
    }

    private boolean dfs(char[][] board, int n, int[] colMark, int[] rowMark, int[] gridMark, int i) {
        for (; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    System.out.println("i=" + i + ",j=" + j);

                    if (i==0 && j==5) {
                        System.out.println();
                    }

                    int col = colMark[i];
                    int row = rowMark[j];
                    int k = calculateGridIndex(i, j);
                    int grid = gridMark[k];

                    // 合并行、列、九宫格后的标记
                    int mark = col | row | grid;
                    System.out.println(get32BitBinString(mark));
                    // 因为是9x9的数独,只查找低9位里0的位置
                    int tmpMark = mark ^ 0b111111111;

                    if (tmpMark == 0) {
                        // 低9位里已经没有0了
                        return false;
                    }

                    // 循环查找低9位里0的位置,即当前单元格的可用数字
                    while (tmpMark != 0) {
                        int num = ((tmpMark - 1) & tmpMark) ^ tmpMark;
                        board[i][j] = (char)(num + '0');
                        updateMark(colMark, rowMark, gridMark, num, i, j ,k);

                        boolean result = dfs(board, n, colMark, rowMark, gridMark, i);

                        if (!result) {
                            // 恢复现场
                            board[i][j] = '.';
                            colMark[i] = col;
                            rowMark[j] = row;
                            gridMark[k] = grid;
                        } else {
                            return true;
                        }
                    }
                    // 因为一定有一种解,所以不用异常处理
                }
            }
        }
        return true;
    }


//    private int calculateAvaliableNum(int mark, int avaliableNum) {
//
////        while (b != 0) {
////            int x = ((b - 1) & b) ^ b;
////            System.out.println(get32BitBinString(x));
////            b = b ^ x;
////            System.out.println(get32BitBinString(b));
////        }
//
//    }


    /**
     * 采用位运算的方式,判断单元格的值是否合法
     * 比用for循环判断的方式效率更高
     * @param colMark
     * @param rowMark
     * @param gridMark
     * @param cell
     * @param i       cell的行索引
     * @param j       cell的列索引
     * @param k       cell的小九宫格的索引
     * @return
     */
    private boolean isValid(int[] colMark, int[] rowMark, int[] gridMark, int cell,
                            int i, int j, int k) {
        int col = colMark[i];
        int row = rowMark[j];
        int grid = gridMark[k];
        // 判断单元格的值在当前行内是否合法
        // cell在当前行和列的位置是 1 << (cell - 1)
        int cellPoition = 1 << (cell - 1);
        if ((col & cellPoition) != 0) {
            // 该单元格的值在行内已存在
            return false;
        }
        if ((row & cellPoition) != 0) {
            // 该单元格的值在列内已存在
            return false;
        }
        if ((grid & cellPoition) != 0) {
            // 该单元格的值在列内已存在
            return false;
        }
        return true;
    }

    /**
     * 采用位运算的方式,更新标记值
     * 比用for更新的方式效率更高
     * @param colMark
     * @param rowMark
     * @param gridMark
     * @param cell
     * @param i              cell的行位置
     * @param j              cell的列位置
     * @param k              cell的小九宫格的位置
     * @return
     */
    private void updateMark(int[] colMark, int[] rowMark, int[] gridMark, int cell,
                            int i, int j, int k) {
        int col = colMark[i];
        int row = rowMark[j];
        int grid = gridMark[k];

        // cell在当前行和列的位置是 1 << (cell - 1)
        int cellPoition = 1 << (cell - 1);

        colMark[i] = col | cellPoition;
        rowMark[j] = row | cellPoition;
        gridMark[k] = grid | cellPoition;
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

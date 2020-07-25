package review.nqueens;

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
     * 思路二: 在思路一的基础上,使用位运算记录会发生冲突的位置
     * 思路二: 位运算全面升级
     */

    public static void main(String[] args) {
        NQueens2 q = new NQueens2();
//        int totalNQueens = q.totalNQueens(8);
        int totalNQueens2 = q.totalNQueens2(8);
        int totalNQueens3 = q.totalNQueens3(8);
//        System.out.println("q1=" + totalNQueens + ",q2=" + totalNQueens2);
        System.out.println("q2=" + totalNQueens2 + ",q3=" + totalNQueens3);
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

    public int totalNQueens2(int n) {
        int allSolutionCount = resursion2(n, 0, 0, 0, 0, 0);
        return allSolutionCount;
    }

    /**
     * 因为每次都行都加1(即i+1),所以不需要对行做处理
     * @return
     */
    private int resursion2(int n, int i, int allSolutionCount,
                           int row, int leftDiagonal, int rightDiagonal) {
        if (i >= n) {
            allSolutionCount++;
            return allSolutionCount;
        }

        for (int j=0;j<n;j++) {
            if (((row >> j) & 1) == 1 ||
                    ((leftDiagonal >> (i + j)) & 1) == 1 ||
                    ((rightDiagonal >> (i - j + n)) & 1) == 1) {
                continue;
            } else {
                allSolutionCount = resursion2(n, i + 1, allSolutionCount,
                        (row | (1 << j)),
                        (leftDiagonal | (1 << (i + j))),
                        (rightDiagonal | (1 << (i - j + n))));
            }
        }
        return allSolutionCount;
    }

    public int totalNQueens3(int n) {
        int allSolutionCount = resursion3(n, 0, 0, 0, 0, 0);
        return allSolutionCount;
    }

    /**
     * 因为每次都行都加1(即i+1),所以不需要对行做处理
     * @return
     */
    private int resursion3(int n, int i, int allSolutionCount,
                           int row, int leftDiagonal, int rightDiagonal) {
        if (i >= n) {
            allSolutionCount++;
            return allSolutionCount;
        }

        // 1. `(row | leftDiagonal | rightDiagonal)` 表示当前行上可以填皇后对位置,即二进制数上,位上为值0,表示可填皇后,位上值为1,表示不可填皇后
        // 2. `(~ (row | leftDiagonal | rightDiagonal))` 表示在步骤1上取反,即位上值为1,表示可填皇后
        // 3. 因为步骤2取反后,高位都变成1了,所以要将高位变成0 即 `& ((1 << n) - 1)`
        int bits = (~ (row | leftDiagonal | rightDiagonal)) & ((1 << n) - 1);
        // 4. 当前行不能没有1时(即不能填皇后了),结束循环
        while (bits > 0) {
            // 5. 取出bits里都最低位的1
            int p = bits & -bits;
            // 6. 将最低位的1填上皇后,即将最低位的1改为0,并递归到下一行
            // 注意这里的`(leftDiagonal | p) << 1`,`(rightDiagonal | p) >> 1`,
            // 即当前填皇后的位置对应第`i+1`行的左斜和右斜的位置是不能填皇后的
            // 同理,随着i不断+1,当前填皇后的位置对应每行位置就要在上一行的基础上不断左移即右移,
            // 以保持当前填皇后的位置在整个棋盘上对应的左斜和右斜线上都不能填皇后
            allSolutionCount = resursion3(n, i + 1, allSolutionCount,
                    row | p,
                    (leftDiagonal | p) << 1,
                    (rightDiagonal | p) >> 1);
            // 7. 将最低位的1改为0,进行下次循环
            bits = bits & (bits - 1);
        }
        return allSolutionCount;
    }
}

package review.dp;

/**
 * 动态规划
 * @author: xiaoxiaoxiang
 * @date: 2020/8/21 09:55
 */
public class DynamicProgramming01 {

    /**
     * 有一个 n*n的棋盘,用二维数组表示,将一颗棋子从(0,0)位置走到(m,n)位置,
     * 每一步只能向右或者向下走,棋盘上有一些障碍物(禁止通行),请问共有多少中走法?
     *
     * 思路一: 递归求解 时间复杂度O(n*m) m=n时,即为O(n^2)
     * 思路二: 动态规划-递推 时间复杂度O(n)
     * (0,0)的走法=(0,1) + (1,0) 以此递推,自底向上,将每一个位置的走法都记录下来
     * 状态转移方程 f(m, n) = f(m-1, n) + f(m, n-1)
     * 要注意障碍物的走法是0
     */

    /**
     * 如果没有石头的情况: 是一个组合问题. 那么从右上角走到左下角一定要经过M个右移和N个下移
     * 这个题目可以转化为从M+N个球中挑出M个球有多少种方法
     * 就是C(M+N, M), 或者C(M+N, N)
     * 所以2 * 2的格子有C(2+2, 2)=6中走法, 2* 3 的格子有 C(5, 2)=10种走法
     */

    public static void main(String[] args) {
        // 值为1时,表示为障碍物
        int[][] board = new int[8][8];
        board[1][2] = 1;
        board[1][6] = 1;
        board[2][4] = 1;
        board[3][0] = 1;
        board[3][2] = 1;
        board[3][5] = 1;
        board[4][2] = 1;
        board[5][3] = 1;
        board[5][4] = 1;
        board[5][6] = 1;
        board[6][1] = 1;
        board[6][5] = 1;
        int result = dynamicProgramming(board);
        System.out.println(result);
    }

    /**
     * 采用动态规划-递推的思路,反向递推,自底向上
     */
    private static int dynamicProgramming(int[][] board) {
        int[][] result = new int[board.length][board[0].length];
        // 最右边这一层,因为只能往下走,所以走法都是1,障碍物走法是0,终点位置就不用计算走法了,默认是0
        for (int i = 0; i < board.length - 2; i++) {
            result[i][board[0].length - 1] = board[i][board.length - 1] == 1 ? 0 : 1;
        }
        // 最底下这一层,因为只能往右走,所以走法都是1,障碍物走法是0,终点位置就不用计算走法了,默认是0
        for (int j = 0; j < board[0].length - 2; j++) {
            result[board.length - 1][j] = board[board.length - 1][j] == 1 ? 0 : 1;
        }
        // 从倒数第二层(board.length - 2)开始,从右往左开始计算 f(m, n) = f(m+1, n) + f(m, n+1)
        for (int i = board.length - 2; i > -1; i--) {
            for (int j = board[0].length -2; j > -1; j--) {
                result[i][j] = board[i][j] == 1 ? 0 : result[i+1][j] + result[i][j+1];
            }
        }
        // 最终返回
        return result[0][0];
    }
}

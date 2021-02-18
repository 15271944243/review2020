package review.dp.stairs;

/**
 * https://leetcode.com/problems/min-cost-climbing-stairs/ No.746
 * 使用最小花费爬楼梯
 * @author: xiaoxiaoxiang
 * @date: 2021/2/10 15:10
 */
public class MinCostClimbingStairs {

    /**
     * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
     * Once you pay the cost, you can either climb one or two steps.
     * You need to find minimum cost to reach the top of the floor,
     * and you can either start from the step with index 0, or the step with index 1.
     *
     * Input: cost = [10, 15, 20]
     * Output: 15
     * Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
     *
     * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
     * Output: 6
     * Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
     *
     * Constraints:
     *
     * cost will have a length in the range [2, 1000].
     * Every cost[i] will be an integer in the range [0, 999].
     */

    /**
     * 题目意思: 使用最小花费爬楼梯
     *
     * 数组的每个下标作为一个阶梯,第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）
     * 每当你爬上一个阶梯你都要花费对应的体力值,一旦支付了相应的体力值,你就可以选择向上爬一个阶梯或者爬两个阶梯
     * 请你找出达到楼层顶部的最低花费.在开始时,你可以选择从下标为 0 或 1 的元素作为初始阶梯。
     *
     * 思路: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */

    public static void main(String[] args) {
        MinCostClimbingStairs demo = new MinCostClimbingStairs();
        int[] cost = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
//        int[] cost = new int[]{10, 15, 20};
        int result = demo.minCostClimbingStairs(cost);
    }


    /**
     * 1. dp[i]的定义为: 爬到第i层的最小花费
     * 2. 递推公式 : dp[i] = Min(dp[i-1], dp[i-2]) + cost[i]
     * 3. dp数组初始化: dp[0] = cost[0] dp[1] = cost[1]
     * 4. 遍历顺序: dp[i]是依赖 dp[i-1]、dp[i-2],那么遍历的顺序一定是从前到后遍历的
     * 5. cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1] 时, 输出 6
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length == 2) {
            return Math.min(cost[0], cost[1]);
        }
        int[] sumCost = new int[cost.length];
        sumCost[0] = cost[0];
        sumCost[1] = cost[1];
        for (int i= 2; i < cost.length; i++) {
            int cost1= sumCost[i - 2] + cost[i];
            int cost2= sumCost[i - 1] + cost[i];
            sumCost[i] = Math.min(cost1, cost2);
        }
        // 根据题目意思: 楼层顶部的索引应该是 cost.length,
        // 所以 cost.length - 1 和 cost.length -2 的索引位置可以通过跨1步或者2步直达楼层顶部
        // 所以最终最小花费应该是 Math.min(sumCost[sumCost.length - 1], sumCost[sumCost.length - 2])
        return Math.min(sumCost[sumCost.length - 1], sumCost[sumCost.length - 2]);
    }
}

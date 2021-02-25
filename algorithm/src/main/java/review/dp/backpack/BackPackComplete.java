package review.dp.backpack;

/**
 * 完全背包问题
 * leetcode上没有纯完全背包的问题,都是完全背包应用方面的题目,也就是需要转化为完全背包问题
 * acwing 上有: https://www.acwing.com/problem/content/3/
 * @author: xiaoxiaoxiang
 * @date: 2021/2/21 10:04
 */
public class BackPackComplete {

    /**
     * 有N件物品和一个最多能背重量为W的背包.第i件物品的重量是weight[i],得到的价值是value[i].
     * 每件物品都有无限个(也就是可以放入背包多次),求解将哪些物品装入背包里物品价值总和最大
     *
     * 思路: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */
    public static void main(String[] args) {
        int[] weight = new int[]{1, 3, 4};
        int[] value = new int[]{15, 20, 30};
        int bagWeight = 4;
        BackPackComplete demo = new BackPackComplete();
        int result = demo.maxValue(bagWeight, weight, value);
    }

    /**
     * 完全背包的状态转移方程与01背包一样
     * dp[j] = max(dp[j], dp[j - weight[i]] + value[i])
     * 01背包针对容量的循环是从大到小遍历,是为了保证每个物品仅被添加一次;
     * 而完全背包的物品是可以添加多次的,所以要从小到大去遍历
     * @param bagWeight
     * @param weight
     * @param value
     * @return
     */
    public int maxValue(int bagWeight, int[] weight, int[] value) {
        int[] dp = new int[bagWeight+1];
        // 初始化 dp[0]
        dp[0] = 0;
        // 先循环物品
        for (int i = 0; i < weight.length; i++) {
            // 01背包针对容量的循环是从大到小遍历,是为了保证每个物品仅被添加一次;
            // 而完全背包的物品是可以添加多次的,所以要从小到大去遍历
            for (int j = 1; j < bagWeight + 1; j++) {
                if (weight[i] <= j) {
                    dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
                }
            }
        }
        return dp[bagWeight];
    }

    /**
     * 二维dp数组解法
     * dp[i][j]表示从下标为[0-i]的物品里任意取,放进容量为j的背包,价值总和的最大值
     *
     * dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i])
     * 递推公式详见 readme.md
     * @param bagWeight
     * @param weight
     * @param value
     * @return
     */
    /*public int maxValue(int bagWeight, int[] weight, int[] value) {
        int[][] dp = new int[weight.length][bagWeight+1];
        // 初始化 dp[0][j]
        for (int j = 1; j < bagWeight+1; j++) {
            dp[0][j] = weight[0] <= bagWeight ?  value[0] : 0;
        }

        // 初始化 dp[i][0]
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }

        // 先循环物品
        for (int i = 1; i < weight.length; i++) {
            // 在循环背包容量
            for (int j = 1; j < bagWeight + 1; j++) {
                if (weight[i] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - weight[i]] + value[i]);
                }
            }
        }
        return dp[weight.length-1][bagWeight];
    }*/
}

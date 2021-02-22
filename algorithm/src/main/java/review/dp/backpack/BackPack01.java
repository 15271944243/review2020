package review.dp.backpack;

/**
 * 01背包问题
 * leetcode上没有纯01背包的问题,都是01背包应用方面的题目,也就是需要转化为01背包问题
 * @author: xiaoxiaoxiang
 * @date: 2021/2/21 10:04
 */
public class BackPack01 {

    /**
     * 有N件物品和一个最多能被重量为W的背包,第i件物品的重量是weight[i],得到的价值是value[i]
     * 每件物品只能用一次，求解将哪些物品装入背包里物品价值总和最大
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
        BackPack01 demo = new BackPack01();
        int result = demo.maxValue(bagWeight, weight, value);
        int result2 = demo.maxValue2(bagWeight, weight, value);
    }

    /**
     * 二维dp数组解法
     * dp[i][j]表示从下标为[0-i]的物品里任意取,放进容量为j的背包,价值总和最大是多少大
     *
     * dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i])
     * 递推公式详见 readme.md
     * @param bagWeight
     * @param weight
     * @param value
     * @return
     */
    public int maxValue(int bagWeight, int[] weight, int[] value) {
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
    }

    /**
     * 一维dp数组解法
     * dp[j]表示: 容量为j的背包,所背的物品价值可以最大为dp[j]
     *
     * dp[j] = max(dp[j], dp[j - weight[i]] + value[i])
     * 递推公式详见 readme.md
     * @param bagWeight
     * @param weight
     * @param value
     * @return
     */
    public int maxValue2(int bagWeight, int[] weight, int[] value) {
        int[] dp = new int[bagWeight+1];
        // 初始化 dp[0]
        dp[0] = 0;
        // 先循环物品
        for (int i = 0; i < weight.length; i++) {
            // 背包容量一定是要倒序遍历,才能保证物品只放入一次
            for (int j = bagWeight; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
            // 正序遍历会导致物品放入多次，例如：
            // dp[1] = dp[1 - weight[0]] + value[0] = 15
            // dp[2] = dp[2 - weight[0]] + value[0] = 30
            // 此时物品0被放入了两次
            /*for (int j = 1; j < bagWeight + 1; j++) {
                if (weight[i] <= j) {
                    dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
                }
            }*/
        }
        return dp[bagWeight];
    }
}

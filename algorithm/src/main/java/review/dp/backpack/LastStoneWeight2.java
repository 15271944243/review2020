package review.dp.backpack;

/**
 * https://leetcode.com/problems/last-stone-weight-ii/ No.1049
 * 最后一块石头的重量 2
 * @author: xiaoxiaoxiang
 * @date: 2021/2/22 21:43
 */
public class LastStoneWeight2 {

    /**
     * We have a collection of rocks, each rock has a positive integer weight.
     *
     * Each turn, we choose any two rocks and smash them together.
     * Suppose the stones have weights x and y with x <= y.  The result of this smash is:
     *
     * If x == y, both stones are totally destroyed;
     * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
     * At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone (the weight is 0 if there are no stones left.)
     *
     * Input: [2,7,4,1,8,1]
     * Output: 1
     * Explanation:
     * We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
     * we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
     * we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
     * we can combine 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.
     *
     * 1 <= stones.length <= 30
     * 1 <= stones[i] <= 100
     */

    /**
     * 题目意思: 有一堆石头，每块石头的重量都是正整数。
     * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
     *    如果 x == y，那么两块石头都会被完全粉碎；
     *    如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * 最后，最多只会剩下一块石头。返回此石头最小的可能重量。如果没有石头剩下，就返回 0。
     *
     * 1 <= stones.length <= 30
     * 1 <= stones[i] <= 1000
     *
     * 思路: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */
    public static void main(String[] args) {
        LastStoneWeight2 demo = new LastStoneWeight2();
        int[] nums = new int[]{2,7,4,1,8,1};
        int result = demo.lastStoneWeightII(nums);
    }

    /**
     * 石头重量为 sum[i],价值为 sum[i]
     * 这堆石头的总重量是 sum,将石头分为重量最相近的两堆:
     *     如果 sum 为偶数,则分为 sum / 2
     *     如果 sum 为奇数,则分为 (sum - 1)/2 和 (sum + 1)/2 两堆
     * 那此时只需要在容量为 sum / 2 或 (sum - 1)/2 （换成(sum + 1)/2也行）里找价值最高的情况m,此时重量也是m,
     * 最终剩下的石头重量即为 sum - 2m
     *
     * 由于每个元素只能使用一次,此题目可以转化为 01背包问题,
     * 背包容量为 sum / 2 或 (sum - 1)/2 ,从 stones[] 数组中找到价值最大的石头组合
     *
     * 1. 确定dp数组(dp table)以及下标的含义: dp[j] 表示在容量为j的背包中,放入石头的最大价值
     * 2. 确定递推公式: dp[j] = max(dp[j], dp[j-stones[i]] + stones[i])
     * 3. dp数组如何初始化: dp[0] = 0
     * 4. 确定遍历顺序: 先遍历 stones[], 再遍历背包容量(倒序遍历)
     * 5. 举例推导dp数组,输入 [2,7,4,1,8,1]
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        if (stones.length == 1) {
            return stones[0];
        }
        int sum = 0;
        for (int i = 0; i < stones.length; i++) {
            sum += stones[i];
        }
        int m = 0;
        if ((sum & 1) == 1) {
            // sum 为奇数,
            m = (sum - 1) / 2;
        } else {
            m = sum / 2;
        }

        int[] dp = new int[m + 1];
        dp[0] = 0;
        for (int i = 0; i < stones.length; i++) {
            for (int j = m; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        int result = sum - dp[m] - dp[m];
        return result;
    }
}

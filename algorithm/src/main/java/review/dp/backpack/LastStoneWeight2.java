package review.dp.backpack;

/**
 * https://leetcode.com/problems/last-stone-weight-ii/ No.1049
 * 最后一块石头的重量 2
 * @author: xiaoxiaoxiang
 * @date: 2021/2/22 21:43
 */
public class LastStoneWeight2 {

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
}

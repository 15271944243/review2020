package review.dp.houserobber;

import review.tree.TreeNode;

/**
 * https://leetcode.com/problems/house-robber/ No.337 打家劫舍3
 * @author: xiaoxiaoxiang
 * @date: 2021/3/2 10:10
 */
public class HouseRobber3 {

    /**
     * The thief has found himself a new place for his thievery again.
     * There is only one entrance to this area, called the "root."
     * Besides the root, each house has one and only one parent house.
     * After a tour, the smart thief realized that "all houses in this place forms a binary tree".
     * It will automatically contact the police if two directly-linked houses were broken into on the same night.
     *
     * Determine the maximum amount of money the thief can rob tonight without alerting the police.
     *
     * Input: [3,2,3,null,3,null,1]
     *
     *      3
     *     / \
     *    2   3
     *     \   \
     *      3   1
     *
     * Output: 7
     * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
     *
     * Input: [3,4,5,1,3,null,1]
     *
     *      3
     *     / \
     *    4   5
     *   / \   \
     *  1   3   1
     *
     * Output: 9
     * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 1000
     */

    /**
     * 题目意思:
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
     * 这个地区只有一个入口，我们称之为“root”。 除了“root”之外，每栋房子有且只有一个“父“房子与之相连。
     * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”
     * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     *
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     *
     * 思路一: 递归求解
     *
     * 思路二: 动态规划
     * 1. 确定dp数组(dp table)以及下标的含义
     * 2. 确定递推公式
     * 3. dp数组如何初始化
     * 4. 确定遍历顺序
     * 5. 举例推导dp数组
     */
    public static void main(String[] args) {
        HouseRobber3 demo = new HouseRobber3();
        // [3,2,3,null,3,null,1

        int result = demo.rob(null);
    }

    /**
     *
     * 1. dp[i]的定义为: 偷盗第i家的最高金额;
     * 2. 递推公式: dp[i] = max(dp[i-1], dp[i-2] + nums[i])
     * 因为不能偷相邻的房屋,[偷盗第i家的最高金额] 等于 [偷盗第i-2家的最高金额] + [偷第i家的金额]
     * 或 [偷盗第i家的最高金额] 等于 [偷盗第i-家的最高金额] （即不偷第i家）
     * 3. dp数组初始化: 0 ~ n - 1 的场景,初始化 dp[0] = nums[0] 和 dp[1] = max(nums[0], nums[1])
     * 1 ~ n 的场景,初始化 dp[0] = nums[1] 和 dp[1] = max(nums[1], nums[2])
     * 4. 遍历顺序: j = 0,1 .... n
     * 5. 举例推导dp数组:
     * @param treeNode
     * @return
     */
    public int rob(TreeNode treeNode) {

        return 0;
    }
}

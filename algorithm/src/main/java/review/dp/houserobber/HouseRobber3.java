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
        // [3,2,3,null,3,null,1]
        TreeNode root = demo.getTreeNode();
        int result = demo.rob(root);
    }

    /**
     * 树形结构dp入门题: https://mp.weixin.qq.com/s/BOJ1lHsxbQxUZffXlgglEQ
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] dp = robTree(root);
        return Math.max(dp[0], dp[1]);
    }

    /**
     * 1. dp[i]的定义为: 当前节点被抢/不被抢的最大金额,i = 0 表示不被抢, i = 1 表示被抢
     * 2. 递推公式: dp[1] = node.val + leftDp[0] + rightDp[0]
     *    dp[0] = Math.max(leftDp[0], leftDp[1]) + Math.max(rightDp[0], rightDp[1]);
     * 3. dp数组初始化: 节点为 null 的时候, dp[0] = 0, dp[1] = 0
     * 4. 遍历顺序:  采用递归,从叶子节点开始计算
     * 5. 举例推导dp数组:
     * @param node
     * @return
     */
    private int[] robTree(TreeNode node) {
        // 左子节点被抢/不被抢的最大金额
        int[] leftDp = null;
        if (node.left != null) {
            leftDp = robTree(node.left);
        } else {
            leftDp = new int[2];
        }
        // 右子节点被抢/不被抢的最大金额
        int[] rightDp = null;
        if (node.right != null) {
            rightDp = robTree(node.right);
        } else {
            rightDp = new int[2];
        }
        // 抢当前节点
        int robNode = node.val + leftDp[0] + rightDp[0];
        // 不抢当前节点
        int unRobNode = Math.max(leftDp[0], leftDp[1]) +
                Math.max(rightDp[0], rightDp[1]);
        return new int[]{unRobNode, robNode};
    }

    private TreeNode getTreeNode() {
        // [3,2,3,null,3,null,1
//        TreeNode root = new TreeNode(3);
//        TreeNode left1 = new TreeNode(2);
//        TreeNode right1 = new TreeNode(3);
//        TreeNode right2 = new TreeNode(3);
//        TreeNode right3 = new TreeNode(1);
//        left1.setRight(right2);
//        right1.setRight(right3);
//        root.setLeft(left1);
//        root.setRight(right1);

        // [4,1,null,2,null,3]
        TreeNode root = new TreeNode(4);
        TreeNode left1 = new TreeNode(1);
        TreeNode left2 = new TreeNode(2);
        TreeNode left3 = new TreeNode(3);
        root.setLeft(left1);
        left1.setLeft(left2);
        left2.setLeft(left3);
        return root;
    }
}

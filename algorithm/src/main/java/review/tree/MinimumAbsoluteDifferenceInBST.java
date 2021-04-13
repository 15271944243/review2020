package review.tree;

import java.util.Stack;

/**
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/ No.530 二叉搜索树的最小绝对差
 * @author: xiaoxiaoxiang
 * @date: 2021/4/13 09:18
 */
public class MinimumAbsoluteDifferenceInBST {

    /**
     * Given a binary search tree with non-negative values,
     * find the minimum absolute difference between values of any two nodes.
     *
     * Input:
     *
     *    1
     *     \
     *      3
     *     /
     *    2
     *
     * Output:
     * 1
     *
     * Explanation:
     * The minimum absolute difference is 1, which is the difference
     * between 2 and 1 (or between 2 and 3).
     *
     * Note:
     * - There are at least two nodes in this BST.
     */

    /**
     * 题目意思: 给你一棵所有节点为非负值的二叉搜索树,请你计算树中任意两节点的差的绝对值的最小值。
     *
     * 思路: 中序遍历的结果是有序数组 nums[],所以比较
     * nums[1] - nums[0], nums[2] - nums[1] ... nums[n] - nums[n-1]
     * 取最小的那个就可以了,时间复杂度O(n)
     */

    public static void main(String[] args) {
        MinimumAbsoluteDifferenceInBST demo = new MinimumAbsoluteDifferenceInBST();
        TreeNode root = demo.getTreeNode();
        int result = demo.getMinimumDifference(root);
    }

    public int getMinimumDifference(TreeNode root) {
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        int minDiff = 0;
        Integer preVal = null;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                TreeNode cur = stack.pop();
                if (preVal != null) {
                    minDiff = minDiff == 0 ? cur.val - preVal
                            : Math.min(minDiff, (cur.val - preVal));
                }
                preVal = cur.val;
                node = cur.right;
            }
        }
        return minDiff;
    }

    private TreeNode getTreeNode() {
        // [5,2,7,1,3,6,8]
        TreeNode root = new TreeNode(5);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(7);
        TreeNode left2 = new TreeNode(1);
        TreeNode right2 = new TreeNode(3);
        TreeNode left3 = new TreeNode(6);
        TreeNode right3 = new TreeNode(8);
        root.setLeft(left1);
        root.setRight(right1);
        left1.setLeft(left2);
        left1.setRight(right2);
        right1.setLeft(left3);
        right1.setRight(right3);
        return root;
    }
}

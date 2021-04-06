package review.tree;

/**
 * https://leetcode.com/problems/balanced-binary-tree/  No.110 平衡二叉树
 * @author: xiaoxiaoxiang
 * @date: 2021/4/6 10:44
 */
public class BalancedBinaryTree {

    /**
     * Given a binary tree, determine if it is height-balanced.
     * For this problem, a height-balanced binary tree is defined as:
     * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
     *
     * Input: root = [3,9,20,null,null,15,7]
     * Output: true
     *
     * Input: root = [1,2,2,3,3,null,null,4,4]
     * Output: false
     *
     * Input: root = []
     * Output: true
     *
     * The number of nodes in the tree is in the range [0, 5000].
     * -10^4 <= Node.val <= 10^4
     */


    /**
     * 题目意思: 给定一个二叉树,判断它是否是高度平衡的二叉树
     * 本题中,一棵高度平衡二叉树定义为: 一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1
     *
     * 思路: 求二叉树根节点的最大高度和最小高度,如果差值不超过1,则平衡
     * 使用 DFS
     *
     * 平不平衡看高度,注意不是深度
     * 二叉树节点的深度：指从根节点到该节点的最长简单路径边的条数。
     * 二叉树节点的高度：指从该节点到叶子节点的最长简单路径边的条数。
     */

    public static void main(String[] args) {
        TreeNode root = TreeNodeHelper.getTreeNode4();
        BalancedBinaryTree demo = new BalancedBinaryTree();
        boolean result = demo.isBalanced(root);
    }

    public boolean isBalanced(TreeNode root) {

        return false;
    }
}

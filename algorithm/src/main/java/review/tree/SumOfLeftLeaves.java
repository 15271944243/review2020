package review.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/sum-of-left-leaves/ No.404 左叶子之和
 * @author: xiaoxiaoxiang
 * @date: 2021/4/7 11:18
 */
public class SumOfLeftLeaves {

    /**
     * Find the sum of all left leaves in a given binary tree.
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
     */

    /**
     * 题目意思: 计算给定二叉树的所有左叶子之和
     *
     * 思路: BFS 遍历找出所有左叶子节点
     *
     */
    public static void main(String[] args) {
        SumOfLeftLeaves demo = new SumOfLeftLeaves();
        TreeNode root = demo.getTreeNode();
        int result = demo.sumOfLeftLeaves(root);
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int result = 0;
        List<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(root);
        while (!treeNodes.isEmpty()) {
            List<TreeNode> tmp = new ArrayList<>();
            for (TreeNode node : treeNodes) {
                if (node.left != null) {
                    // 要通过节点的父节点来判断其左孩子是不是左叶子了
                    if (node.left.left == null && node.left.right == null) {
                        result += node.left.val;
                    } else {
                        tmp.add(node.left);
                    }
                }
                if (node.right != null) {
                    tmp.add(node.right);
                }
            }
            treeNodes = tmp;
        }
        return result;
    }

    private TreeNode getTreeNode() {
//        TreeNode root = new TreeNode(3);
//        TreeNode left1 = new TreeNode(9);
//        TreeNode right1 = new TreeNode(20);
//        TreeNode left3 = new TreeNode(15);
//        TreeNode right3 = new TreeNode(7);
//        root.setLeft(left1);
//        root.setRight(right1);
//        right1.setLeft(left3);
//        right1.setRight(right3);

        // [-8,-6,7,6,null,null,null,null,5]
//        TreeNode root = new TreeNode(-8);
//        TreeNode left1 = new TreeNode(-6);
//        TreeNode right1 = new TreeNode(7);
//        TreeNode left2 = new TreeNode(6);
//        TreeNode right4 = new TreeNode(5);
//        root.setLeft(left1);
//        root.setRight(right1);
//        left1.setLeft(left2);
//        left2.setRight(right4);

        // [-9,-3,2,null,4,4,0,-6,null,-5]
        TreeNode root = new TreeNode(-9);
        TreeNode left1 = new TreeNode(-3);
        TreeNode right1 = new TreeNode(2);
        TreeNode right2 = new TreeNode(4);
        TreeNode left3 = new TreeNode(4);
        TreeNode right3 = new TreeNode(0);
        TreeNode left5 = new TreeNode(-6);
        TreeNode left6 = new TreeNode(-5);
        root.setLeft(left1);
        root.setRight(right1);
        left1.setRight(right2);
        right1.setLeft(left3);
        right1.setRight(right3);
        right2.setLeft(left5);
        left3.setLeft(left6);
        return root;
    }
}

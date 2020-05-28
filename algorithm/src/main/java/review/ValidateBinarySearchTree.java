package review;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/5/25 23:10
 */
public class ValidateBinarySearchTree {

    /**
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     *
     * Assume a BST is defined as follows:
     *     The left subtree of a node contains only nodes with keys less than the node's key.
     *     The right subtree of a node contains only nodes with keys greater than the node's key.
     *     Both the left and right subtrees must also be binary search trees.
     */

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */

    /**
     * 题目意思: 给定一个二叉树,判断它是否是一个二叉搜索树
     *
     * 思路一: 中序遍历整棵树,将遍历后的节点放入数组中,如果数组是一个升序数组,则是二叉搜索树
     * 思路二: 递归判断
     */

    public static void main(String[] args) {
        ValidateBinarySearchTree tree = new ValidateBinarySearchTree();
        TreeNode root = new TreeNode();
        tree.isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, Integer max, Integer min) {
        if (root == null) {
            return true;
        }
        if (max != null && root.val >= max) {
            return false;
        }
        if (min != null && root.val <= min) {
            return false;
        }
        return isValidBST(root.left, root.val, min) && isValidBST(root.right, max, root.val);
    }



    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

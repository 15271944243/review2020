package review.tree;

import java.util.Stack;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/ No.98 验证二叉搜索树
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
     *
     * Input: root = [2,1,3]
     * Output: true
     *
     * Input: root = [5,1,4,null,null,3,6]
     * Output: false
     * Explanation: The root node's value is 5 but its right child's value is 4.
     *
     * The number of nodes in the tree is in the range [1, 10^4].
     * -2^31 <= Node.val <= 2^31 - 1
     */

    /**
     * 题目意思: 给定一个二叉树,判断它是否是一个二叉搜索树
     *
     * 思路一: 中序遍历整棵树,如果是升序,则是二叉搜索树
     * 中序遍历算法可以参考BinaryTreeInorderTraversal.java
     * 思路二: 递归判断
     */

    public static void main(String[] args) {
        ValidateBinarySearchTree demo = new ValidateBinarySearchTree();
        TreeNode root = demo.getTreeNode();
        boolean result = demo.isValidBST(root);
        boolean result2 = demo.isValidBST2(root);
    }

    public boolean isValidBST(TreeNode root) {
        return recursion(root, null, null);
    }

    /**
     * 递归
     * @param root
     * @param max
     * @param min
     * @return
     */
    private boolean recursion(TreeNode root, Integer max, Integer min) {
        if (root == null) {
            return true;
        }
        if (max != null && root.val >= max) {
            return false;
        }
        if (min != null && root.val <= min) {
            return false;
        }
        return recursion(root.left, root.val, min) && recursion(root.right, max, root.val);
    }

    /**
     * 中序遍历整棵树,如果是升序,则是二叉搜索树
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        Integer preVal = null;
        boolean result = true;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                TreeNode tmpNode = stack.pop();
                if (preVal == null || preVal < tmpNode.val) {
                    preVal = tmpNode.val;
                } else {
                    result = false;
                    break;
                }
                node = tmpNode.right;
            }
        }
        return result;
    }

    private TreeNode getTreeNode() {
        // [5,1,4,null,null,3,6]
        TreeNode root = new TreeNode(5);
        TreeNode left1 = new TreeNode(1);
        TreeNode right1 = new TreeNode(4);
        TreeNode left3 = new TreeNode(3);
        TreeNode right3 = new TreeNode(6);
        root.setLeft(left1);
        root.setRight(right1);
        right1.setLeft(left3);
        right1.setRight(right3);
        return root;
    }
}

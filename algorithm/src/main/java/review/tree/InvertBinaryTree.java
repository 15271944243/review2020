package review.tree;

import java.util.List;

/**
 * https://leetcode.com/problems/invert-binary-tree/ No.226 翻转二叉树
 * @author: xiaoxiaoxiang
 * @date: 2021/3/19 11:04
 */
public class InvertBinaryTree {

    /**
     * Given the root of a binary tree, invert the tree, and return its root.
     *
     * Input: root = [4,2,7,1,3,6,9]
     * Output: [4,7,2,9,6,3,1]
     *
     * Input: root = [2,1,3]
     * Output: [2,3,1]
     *
     * Input: root = []
     * Output: []
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [0, 100].
     * -100 <= Node.val <= 100
     */

    /**
     * 题目意思: 翻转二叉树(以root为中轴线翻转)
     *
     */
    public static void main(String[] args) {
        InvertBinaryTree demo = new InvertBinaryTree();
        TreeNode root = TreeNodeHelper.getTreeNode2();
        TreeNode result = demo.invertTree(root);
    }

    public TreeNode invertTree(TreeNode root) {

        return null;
    }
}

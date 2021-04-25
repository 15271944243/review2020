package review.tree;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/ No.236 二叉树的最近公共祖先
 * @author: xiaoxiaoxiang
 * @date: 2020/5/30 22:33
 */
public class LowestCommonAncestor {

    /**
     * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
     *
     * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q
     * as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
     */

    /**
     * 题目意思: 给定一个二叉树,查询给定两个节点的最小公共祖先
     * 与LowestCommonAncestor2.java的区别是一个是二叉树,另一个是二叉搜索树
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 两个都不为null,则最小公共祖先就是他们的父节点
        if (left != null && right != null) {
            return root;
        }
        // 其中任意一个为null,就继续递归,直到找到两个都不为null的情况
        return left == null ? right : left;
    }
}

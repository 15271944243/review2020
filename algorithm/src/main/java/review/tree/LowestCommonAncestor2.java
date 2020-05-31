package review.tree;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * @author: xiaoxiaoxiang
 * @date: 2020/5/30 22:33
 */
public class LowestCommonAncestor2 {

    /**
     * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
     *
     * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q
     * as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
     */

    /**
     * 题目意思: 给定一个二叉搜索树,查询给定两个节点的最小公共祖先
     * 与LowestCommonAncestor.java的区别是一个是二叉树,另一个是二叉搜索树
     * 注: 本题也可以直接用LowestCommonAncestor.java的解题方法,但是由于BST的特性,
     * 所以可以在LowestCommonAncestor.java的解题方法上进行优化
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        // 只要一个比root大,另一个比root小,那root就是最小公共祖先
        if ((p.val > root.val && q.val < root.val) || (p.val < root.val && q.val > root.val)) {
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

    /**
     * 非递归算法
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        while(root != null) {
            if (p.val > root.val && q.val > root.val) {
                // 如果都比root大,则去root的右子树搜索
                root = root.right;
            } else if (p.val < root.val && q.val < root.val) {
                // 如果都比root小,则去root的左子树搜索
                root = root.left;
            } else {
                // 只要一个比root大,另一个比root小,那root就是最小公共祖先
                return root;
            }
        }
        return root;
    }
}

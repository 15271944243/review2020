package review.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/symmetric-tree/ No.101 对称二叉树
 * @author: xiaoxiaoxiang
 * @date: 2021/4/2 11:16
 */
public class SymmetricTree {

    /**
     * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
     *
     * Input: root = [1,2,2,3,4,4,3]
     * Output: true
     *
     * Input: root = [1,2,2,null,3,null,3]
     * Output: false
     *
     * The number of nodes in the tree is in the range [1, 1000]
     * -100 <= Node.val <= 100
     */

    /**
     * 题目意思: 给定一个二叉树,检查它是否是镜像对称的
     *
     * 思路一: 递归
     * 思路二: 使用栈/队列,来判断根节点的左子树和右子树的内侧和外侧是否相等
     */

    public static void main(String[] args) {
        TreeNode root = TreeNodeHelper.getTreeNode3();
        SymmetricTree demo = new SymmetricTree();
        boolean result = demo.isSymmetric(root);
    }

    /**
     * 递归判断
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return recursion(root.left, root.right);
    }

    private boolean recursion(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if ((left == null || right == null)
                || (left != null && right != null && left.val != right.val) ) {
            return false;
        }

        boolean result = recursion(left.left, right.right);
        if (!result) {
            return result;
        }
        result = recursion(left.right, right.left);
        return result;
    }
}

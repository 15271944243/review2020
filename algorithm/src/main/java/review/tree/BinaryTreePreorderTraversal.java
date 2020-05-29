package review.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 * @author: xiaoxiaoxiang
 * @date: 2020/5/29 15:43
 */
public class BinaryTreePreorderTraversal {

    /**
     * Given a binary tree, return the preorder traversal of its nodes' values.
     */

    /**
     * 题目意思: 给定一个二叉树,返回它的前序遍历后的节点值
     *
     */

    /**
     * 前序遍历(DLR)
     * D=Degree
     * L=Left
     * R=Right
     * 结点拥有的子树数称为结点的度（Degree）
     * 1) 访问根结点
     * 2) 前序遍历左子树
     * 3) 前序遍历右子树
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> leftList = preorderTraversal(root.left);
        List<Integer> rightList = preorderTraversal(root.right);

        List<Integer> list = new ArrayList<>();

        list.add(root.val);

        if (leftList != null) {
            list.addAll(leftList);
        }

        if (rightList != null) {
            list.addAll(rightList);
        }
        return list;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

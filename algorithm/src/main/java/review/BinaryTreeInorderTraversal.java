package review;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * @author: xiaoxiaoxiang
 * @date: 2020/5/29 13:50
 */
public class BinaryTreeInorderTraversal {

    /**
     * Given a binary tree, return the inorder traversal of its nodes' values.
     */

    /**
     * 题目意思: 给定一个二叉树,返回它的中序遍历后的节点值
     *
     */
    public static void main(String[] args) {
        TreeNode rightLeft = new TreeNode(3);
        TreeNode right = new TreeNode(2, rightLeft, null);
        TreeNode root = new TreeNode(1, null, right);
        BinaryTreeInorderTraversal test = new BinaryTreeInorderTraversal();
        List<Integer> list = test.inorderTraversal(root);
    }

    /**
     * 中序遍历(LDR)
     * D=Degree
     * L=Left
     * R=Right
     * 结点拥有的子树数称为结点的度（Degree）
     * 1) 中序遍历左子树
     * 2) 访问根结点
     * 3) 中序遍历右子树
     * @param root
     * @return
     */
    private List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> leftList = inorderTraversal(root.left);
        List<Integer> rightList = inorderTraversal(root.right);

        List<Integer> list = new ArrayList<>();

        if (leftList != null) {
            list.addAll(leftList);
        }

        list.add(root.val);

        if (rightList != null) {
            list.addAll(rightList);
        }
        return list;
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

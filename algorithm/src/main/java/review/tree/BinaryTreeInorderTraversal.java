package review.tree;

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
        BinaryTreeInorderTraversal demo = new BinaryTreeInorderTraversal();
        TreeNode root = TreeNodeHelper.getTreeNode();
        List<Integer> list = demo.inorderTraversal2(root);
//        List<Integer> list = demo.inorderTraversal(root);
    }

    private List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }


        return null;
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
}

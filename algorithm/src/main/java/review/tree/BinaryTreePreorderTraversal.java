package review.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-preorder-traversal/ No.144 二叉树前序遍历
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
    public static void main(String[] args) {
        BinaryTreePreorderTraversal demo = new BinaryTreePreorderTraversal();
        TreeNode root = TreeNodeHelper.getTreeNode();
        List<Integer> list = demo.preorderTraversal2(root);
//        List<Integer> list = demo.preorderTraversal(root);
    }

    /**
     * 前序遍历(DLR)  非递归
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
    public List<Integer> preorderTraversal2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            // 先push 右节点,再push 左节点
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }

    /**
     * 前序遍历(DLR)  递归
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
            return new ArrayList<>();
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
}

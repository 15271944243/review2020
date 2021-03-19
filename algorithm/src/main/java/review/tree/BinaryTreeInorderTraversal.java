package review.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/ No.94 二叉树中序遍历
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

    /**
     * 中序遍历(LDR) 非递归  要访问的元素和要处理的元素顺序不一致的,无法复用前序遍历(非递归)的思想
     * 需要借用指针的遍历来帮助访问节点,栈则用来处理节点上的元素
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
    private List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }
        return result;
    }
    /**
     * 中序遍历(LDR) 递归
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

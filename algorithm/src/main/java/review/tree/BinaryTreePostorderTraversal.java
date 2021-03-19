package review.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-postorder-traversal/ No.145 二叉树后续遍历
 * @author: xiaoxiaoxiang
 * @date: 2020/5/29 16:40
 */
public class BinaryTreePostorderTraversal {

    /**
     * Given a binary tree, return the postorder traversal of its nodes' values.
     */

    /**
     * 题目意思: 给定一个二叉树,返回它的后序遍历后的节点值
     *
     */
    public static void main(String[] args) {
        BinaryTreePostorderTraversal demo = new BinaryTreePostorderTraversal();
        TreeNode root = TreeNodeHelper.getTreeNode();
        List<Integer> list = demo.postorderTraversal2(root);
//        List<Integer> list = demo.postorderTraversal(root);
    }

    /**
     * 后序遍历(LRD) 非递归 思路: 前序遍历的顺序是DLR,如果我们实现了DRL顺序,然后将结果反转,即为LRD
     * D=Degree
     * L=Left
     * R=Right
     * 结点拥有的子树数称为结点的度（Degree）
     * 1) 后序遍历左子树
     * 2) 后序遍历右子树
     * 3) 访问根结点
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            // 前序遍历是DLR,我们这里需要DRL,所以先放左子节点,再放右子节点
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        // 对结果反转,获得LRD顺序的结果
        List<Integer> result2 = new ArrayList<>(result.size());
        for (int i = result.size() - 1; i >= 0 ; i--) {
            result2.add(result.get(i));
        }
        return result2;
    }

    /**
     * 后序遍历(LRD) 递归
     * D=Degree
     * L=Left
     * R=Right
     * 结点拥有的子树数称为结点的度（Degree）
     * 1) 后序遍历左子树
     * 2) 后序遍历右子树
     * 3) 访问根结点
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> leftList = postorderTraversal(root.left);
        List<Integer> rightList = postorderTraversal(root.right);

        List<Integer> list = new ArrayList<>();

        if (leftList != null) {
            list.addAll(leftList);
        }

        if (rightList != null) {
            list.addAll(rightList);
        }

        list.add(root.val);
        return list;
    }
}

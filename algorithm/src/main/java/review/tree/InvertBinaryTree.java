package review.tree;

import java.util.LinkedList;

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
     */
    public static void main(String[] args) {
        InvertBinaryTree demo = new InvertBinaryTree();
        TreeNode root = TreeNodeHelper.getTreeNode2();
        TreeNode result = demo.invertTree(root);
    }

    /**
     * 思路: 可以采用BFS逐层遍历节点,然后每个节点的子节点交换位置,即左子节点变成右子节点,右子节点变成左子节点
     * 注意: 这里可以使用前序遍历、后续遍历,但是不能使用中序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.push(root);
        while (!list.isEmpty()) {
            LinkedList<TreeNode> list2 = new LinkedList<>();
            while (!list.isEmpty()) {
                TreeNode node = list.pop();
                TreeNode left = node.left;
                TreeNode right = node.right;
                // 交换
                node.left = right;
                node.right = left;
                if (left != null) {
                    list2.push(left);
                }
                if (right != null) {
                    list2.push(right);
                }
            }
            list = list2;
        }
        return root;
    }
}

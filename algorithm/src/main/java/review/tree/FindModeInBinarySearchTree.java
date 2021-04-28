package review.tree;

/**
 * https://leetcode.com/problems/find-mode-in-binary-search-tree/ No.501 二叉搜索树中的众数
 * @author: xiaoxiaoxiang
 * @date: 2021/4/13 09:35
 */
public class FindModeInBinarySearchTree {

    /**
     * Given the root of a binary search tree (BST) with duplicates,
     * return all the mode(s) (i.e., the most frequently occurred element) in it.
     *
     * If the tree has more than one mode, return them in any order.
     *
     * Assume a BST is defined as follows:
     *    - The left subtree of a node contains only nodes with keys less than or equal to the node's key.
     *    - The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
     *    - Both the left and right subtrees must also be binary search trees.
     *
     * Input: root = [1,null,2,2]
     * Output: [2]
     *
     * Input: root = [0]
     * Output: [0]
     *
     * The number of nodes in the tree is in the range [1, 10^4].
     * -10^5 <= Node.val <= 10^5
     *
     * Follow up: Could you do that without using any extra space? (Assume that
     * the implicit stack space incurred due to recursion does not count).
     */

    /**
     * 题目意思: 给定一个有相同值的二叉搜索树（BST）,找出 BST 中的所有众数（出现频率最高的元素）
     *
     * 假定 BST 有如下定义:
     * - 结点左子树中所含结点的值小于等于当前结点的值
     * - 结点右子树中所含结点的值大于等于当前结点的值
     * - 左子树和右子树都是二叉搜索树
     *
     * 进阶: 你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
     *
     * 思路:
     */

    public static void main(String[] args) {
        FindModeInBinarySearchTree demo = new FindModeInBinarySearchTree();
        TreeNode root = demo.getTreeNode();
        int[] result = demo.findMode(root);
    }

    public int[] findMode(TreeNode root) {
        // TODO
        return null;
    }

    private TreeNode getTreeNode() {
        // [5,1,4,null,null,3,6]
        TreeNode root = new TreeNode(5);
        TreeNode left1 = new TreeNode(1);
        TreeNode right1 = new TreeNode(4);
        TreeNode left3 = new TreeNode(3);
        TreeNode right3 = new TreeNode(6);
        root.setLeft(left1);
        root.setRight(right1);
        right1.setLeft(left3);
        right1.setRight(right3);
        return root;
    }
}

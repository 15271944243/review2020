package review.tree;

/**
 * https://leetcode.com/problems/insert-into-a-binary-search-tree/ No.701 二叉搜索树中的插入操作
 * @author: xiaoxiaoxiang
 * @date: 2021/4/25 10:15
 */
public class InsertIntoBinarySearchTree {

    /**
     * You are given the root node of a binary search tree (BST) and a value to insert into the tree.
     * Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist
     * in the original BST.
     *
     * Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST
     * after insertion. You can return any of them.
     *
     * Input: root = [4,2,7,1,3], val = 5
     * Output: [4,2,7,1,3,5]
     *
     * Input: root = [40,20,60,10,30,50,70], val = 25
     * Output: [40,20,60,10,30,50,70,null,null,25]
     *
     * Input: root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
     * Output: [4,2,7,1,3,5]
     *
     * The number of nodes in the tree will be in the range [0, 10^4].
     * -10^8 <= Node.val <= 10^8
     * All the values Node.val are unique.
     * -10^8 <= val <= 10^8
     * It's guaranteed that val does not exist in the original BST.
     */

    /**
     * 题目意思: 给定二叉搜索树（BST）的根节点和要插入树中的值,将值插入二叉搜索树.返回插入后二叉搜索树的根节点.
     * 输入数据保证,新值和原始二叉搜索树中的任意节点值都不同
     *
     * 注意,可能存在多种有效的插入方式,只要树在插入后仍保持为二叉搜索树即可. 你可以返回任意有效的结果
     *
     * 思路: 不考虑多种有效的插入方式,只关注BST最基础的规则,左子树小于根节点,右子树大于根节点,然后从根节点开始遍历,进行插入
     */

    public static void main(String[] args) {
        InsertIntoBinarySearchTree demo = new InsertIntoBinarySearchTree();
        TreeNode root = demo.getTreeNode();
        int val = 5;
        TreeNode result = demo.insertIntoBST(root, val);
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode cur = root;
        TreeNode node = null;
        while (cur != null) {
            if (cur.val > val) {
                if (cur.left == null) {
                    node = new TreeNode(val);
                    cur.left = node;
                    break;
                } else {
                    cur = cur.left;
                }
            } else {
                if (cur.right == null) {
                    node = new TreeNode(val);
                    cur.right = node;
                    break;
                } else {
                    cur = cur.right;
                }
            }
        }
        return root;
    }

    private TreeNode getTreeNode() {
        // [4,2,7,1,3]
        TreeNode root = new TreeNode(4);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(7);
        TreeNode left2 = new TreeNode(1);
        TreeNode right2 = new TreeNode(3);
        root.setLeft(left1);
        root.setRight(right1);
        left1.setLeft(left2);
        left1.setRight(right2);
        return root;
    }
}

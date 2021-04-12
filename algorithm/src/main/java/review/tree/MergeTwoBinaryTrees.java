package review.tree;

/**
 * https://leetcode.com/problems/merge-two-binary-trees/ No.617 合并两个二叉树
 * @author: xiaoxiaoxiang
 * @date: 2021/4/9 11:23
 */
public class MergeTwoBinaryTrees {

    /**
     * You are given two binary trees root1 and root2.
     *
     * Imagine that when you put one of them to cover the other, some nodes of the two trees are
     * overlapped while the others are not.
     * You need to merge the two trees into a new binary tree. The merge rule is that if two nodes overlap,
     * then sum node values up as the new value of the merged node.
     * Otherwise, the NOT null node will be used as the node of the new tree.
     *
     * Return the merged tree.
     *
     * Note: The merging process must start from the root nodes of both trees.
     *
     * Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
     * Output: [3,4,5,5,4,null,7]
     *
     * Input: root1 = [1], root2 = [1,2]
     * Output: [2,2]
     *
     * The number of nodes in both trees is in the range [0, 2000].
     * -10^4 <= Node.val <= 10^4
     */

    /**
     * 题目意思: 给定两个二叉树,想象当你将它们中的一个覆盖到另一个上时,两个二叉树的一些节点便会重叠。
     * 你需要将他们合并为一个新的二叉树.合并的规则是如果两个节点重叠,那么将他们的值相加作为节点合并后的新值,
     * 否则不为 NULL 的节点将直接作为新二叉树的节点
     *
     * 输入:
     * 	Tree 1                     Tree 2
     *           1                         2
     *          / \                       / \
     *         3   2                     1   3
     *        /                           \   \
     *       5                             4   7
     * 输出:
     * 合并后的树:
     * 	     3
     * 	    / \
     * 	   4   5
     * 	  / \   \
     * 	 5   4   7
     *
     * 注意: 合并必须从两个树的根节点开始
     *
     * 思路: DFS 递归遍历
     *
     */

    public static void main(String[] args) {
        MergeTwoBinaryTrees demo = new MergeTwoBinaryTrees();
        TreeNode root1 = demo.getRoot1();
        TreeNode root2 = demo.getRoot2();
        TreeNode root = demo.mergeTrees(root1, root2);
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }
        int val1 = root1 == null ? 0 : root1.val;
        int val2 = root2 == null ? 0 : root2.val;
        TreeNode node = new TreeNode(val1 + val2);
        TreeNode leftNode = mergeTrees(root1 == null ? null : root1.left,
                root2 == null ? null : root2.left);
        TreeNode rightNode = mergeTrees(root1 == null ? null : root1.right,
                root2 == null ? null : root2.right);
        node.left = leftNode;
        node.right = rightNode;
        return node;
    }

    private TreeNode getRoot1() {
        TreeNode root = new TreeNode(1);
        TreeNode left1 = new TreeNode(3);
        TreeNode right1 = new TreeNode(2);
        TreeNode left2 = new TreeNode(5);
        root.setLeft(left1);
        root.setRight(right1);
        left1.setLeft(left2);
        return root;
    }

    private TreeNode getRoot2() {
        TreeNode root = new TreeNode(2);
        TreeNode left1 = new TreeNode(1);
        TreeNode right1 = new TreeNode(3);
        TreeNode right2 = new TreeNode(4);
        TreeNode right3 = new TreeNode(7);
        root.setLeft(left1);
        root.setRight(right1);
        left1.setRight(right2);
        right1.setRight(right3);
        return root;
    }
}

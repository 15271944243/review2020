package review.tree;

import java.util.Stack;

/**
 * https://leetcode.com/problems/convert-bst-to-greater-tree/ No.538 把二叉搜索树转换为累加树
 * @author: xiaoxiaoxiang
 * @date: 2021/4/25 11:44
 */
public class ConvertBSTToGreaterTree {

    /**
     * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the
     * original BST is changed to the original key plus sum of all keys greater than the original key in BST.
     *
     * As a reminder, a binary search tree is a tree that satisfies these constraints:
     *
     * - The left subtree of a node contains only nodes with keys less than the node's key.
     * - The right subtree of a node contains only nodes with keys greater than the node's key.
     * - Both the left and right subtrees must also be binary search trees.
     *
     * Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
     * Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
     *
     * Input: root = [0,null,1]
     * Output: [1,null,1]
     *
     * Input: root = [1,0,2]
     * Output: [3,3,2]
     *
     * Input: root = [3,2,4,1]
     * Output: [7,9,4,10]
     *
     * The number of nodes in the tree is in the range [0, 10^4].
     * -10^4 <= Node.val <= 10^4
     * All the values in the tree are unique.
     * root is guaranteed to be a valid binary search tree.
     */

    /**
     * 题目意思: 给出二叉搜索树的根节点,该树的节点值各不相同,请你将其转换为累加树（Greater Sum Tree）,
     * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和
     *
     * 思路: 将二叉搜索树节点以从右向左的顺序排序为 nodes[],则 nodes[i+1] = nodes[i+1].val + nodes[i].val
     */

    public static void main(String[] args) {
        ConvertBSTToGreaterTree demo = new ConvertBSTToGreaterTree();
        TreeNode root = demo.getTreeNode();
        TreeNode result = demo.convertBST(root);
        TreeNode result2 = demo.convertBST2(root);
    }

    /**
     * 将二叉搜索树节点以从右向左的顺序排序为 nodes[],则 nodes[i+1] = nodes[i+1].val + nodes[i].val
     *
     * 中序遍历二叉搜索树,将节点放入 stack 中,依次 pop, pop 的顺序即为树节点以从右向左的顺序
     *
     * @param root
     * @return
     */

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 用于遍历的 stack
        Stack<TreeNode> stack1 = new Stack<>();
        // 用于存储结果的 stack
        Stack<TreeNode> stack2 = new Stack<>();
        TreeNode cur = root;

        while (!stack1.isEmpty() || cur != null) {
            if (cur != null) {
                stack1.push(cur);
                cur = cur.left;
            } else {
                TreeNode parent = stack1.pop();
                stack2.push(parent);
                cur = parent.right;
            }
        }
        int prevVal = 0;
        while (!stack2.isEmpty()) {
            TreeNode node = stack2.pop();
            prevVal = node.val + prevVal;
            node.val = prevVal;
        }
        return root;
    }

    /**
     * 也可以用反中序遍历,即 RDL 的顺序,即把上面代码的 cur.left 改成 cur.right, parent.right 改成 parent.left
     * @param root
     * @return
     */
    public TreeNode convertBST2(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 用于遍历的 stack
        Stack<TreeNode> stack1 = new Stack<>();
        TreeNode cur = root;
        int prevVal = 0;
        while (!stack1.isEmpty() || cur != null) {
            if (cur != null) {
                stack1.push(cur);
                cur = cur.right;
            } else {
                TreeNode parent = stack1.pop();
                prevVal += parent.val;
                parent.val = prevVal;
                cur = parent.left;
            }
        }
        return root;
    }

    private TreeNode getTreeNode() {
        // [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
        TreeNode root = new TreeNode(4);
        TreeNode left1 = new TreeNode(1);
        TreeNode right1 = new TreeNode(6);
        TreeNode left2 = new TreeNode(0);
        TreeNode right2 = new TreeNode(2);
        TreeNode left3 = new TreeNode(5);
        TreeNode right3 = new TreeNode(7);
        root.setLeft(left1);
        root.setRight(right1);
        left1.setLeft(left2);
        left1.setRight(right2);
        right1.setLeft(left3);
        right1.setRight(right3);

        TreeNode right5 = new TreeNode(3);
        TreeNode right7 = new TreeNode(8);
        right2.setRight(right5);
        right3.setRight(right7);
        return root;
    }
}

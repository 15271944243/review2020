package review.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-paths/ No.257 二叉树的所有路径
 * @author: xiaoxiaoxiang
 * @date: 2021/4/6 11:17
 */
public class BinaryTreePaths {

    /**
     * Given the root of a binary tree, return all root-to-leaf paths in any order.
     * A leaf is a node with no children.
     *
     * Input: root = [1,2,3,null,5]
     * Output: ["1->2->5","1->3"]
     *
     * Input: root = [1]
     * Output: ["1"]
     *
     * Input: root = []
     * Output: true
     *
     * The number of nodes in the tree is in the range [1, 100].
     * -100 <= Node.val <= 100
     */

    /**
     * 题目意思: 给定一个二叉树,返回所有从根节点到叶子节点的路径
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 思路: 递归+回溯
     *
     * 二叉树节点的深度：指从根节点到该节点的最长简单路径边的条数。
     * 二叉树节点的高度：指从该节点到叶子节点的最长简单路径边的条数。
     */
    public static void main(String[] args) {
        BinaryTreePaths demo = new BinaryTreePaths();
        TreeNode root = demo.getTreeNode();
        List<String> result = demo.binaryTreePaths(root);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root != null) {
            recursion(root, result, "");
        }
        return result;
    }

    private void recursion(TreeNode node, List<String> result, String tmpResult) {
        if (node.left == null && node.right == null) {
            tmpResult = addVal(node, tmpResult);
            result.add(tmpResult);
            return;
        }
        tmpResult = addVal(node, tmpResult);
        if (node.left != null) {
            recursion(node.left, result, tmpResult);
        }
        if (node.right != null) {
            recursion(node.right, result, tmpResult);
        }
    }

    private String addVal(TreeNode node, String tmpResult) {
        if (tmpResult.length() == 0) {
            tmpResult += node.val;
        } else {
            tmpResult += "->" + node.val;
        }
        return tmpResult;
    }

    public TreeNode getTreeNode() {
//       [1,2,3,null,5]
        TreeNode root = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);
        TreeNode right2 = new TreeNode(5);
        root.setRight(right1);
        root.setLeft(left1);
        left1.setRight(right2);
        return root;
    }
}

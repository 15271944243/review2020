package review.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/ No.104 二叉树的最大深度
 * @author: xiaoxiaoxiang
 * @date: 2020/6/11 23:22
 */
public class MaximumDepthOfBinaryTree {
    /**
     * Given a binary tree, find its maximum depth.
     *
     * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     *
     * Note: A leaf is a node with no children.
     */

    /**
     * Example:
     *
     * Given binary tree [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * return its depth = 3.
     */

    /**
     * 题目意思: 给定一个二叉树,找到它的最大深度
     * 思路一: 深度优先搜索(Depth Fitst Search)
     * 思路二: 广度优先搜索(Breadth First Search)
     */


    /**
     * 广度优先搜索(Breadth First Search)
     * @param root
     * @return
     */
    public int maxDepthByBFS(TreeNode root) {
        int maxDepth = 0;
        List<TreeNode> nodeList = new ArrayList<>(1);
        if (root != null) {
            nodeList.add(root);
        }
        while (!nodeList.isEmpty()) {
            maxDepth++;
            List<TreeNode> tmpList = new ArrayList<>();
            for (TreeNode treeNode : nodeList) {
                if (treeNode.left != null) {
                    tmpList.add(treeNode.left);
                }
                if (treeNode.right != null){
                    tmpList.add(treeNode.right);
                }
            }
            nodeList = tmpList;
        }
        return maxDepth;
    }

    /**
     * 深度优先搜索(Depth Fitst Search)
     * @param root
     * @return
     */
    public int maxDepthByDFS(TreeNode root) {
        if (root==null) {
            return 0;
        }
        return 1 + Math.max(maxDepthByDFS(root.left), maxDepthByDFS(root.right));
    }
}

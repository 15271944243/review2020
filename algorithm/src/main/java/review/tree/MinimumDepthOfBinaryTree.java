package review.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/ No.111 二叉树的最小深度
 * @author: xiaoxiaoxiang
 * @date: 2020/6/11 23:22
 */
public class MinimumDepthOfBinaryTree {

    /**
     * Given a binary tree, find its minimum depth.
     *
     * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
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
     * return its minimum depth = 2.
     */

    /**
     * 题目意思: 给定一个二叉树,找到它的最小深度
     * 思路一: 深度优先搜索(Depth Fitst Search)
     * 思路二: 广度优先搜索(Breadth First Search)
     */

    /**
     * 广度优先搜索(Breadth First Search)
     * @param root
     * @return
     */
    public int minDepthByBFS(TreeNode root) {
        int minDepth = 0;
        List<TreeNode> nodeList = new ArrayList<>(1);
        if (root != null) {
            nodeList.add(root);
        }
        while (!nodeList.isEmpty()) {
            minDepth++;
            List<TreeNode> tmpList = new ArrayList<>();
            for (TreeNode treeNode : nodeList) {
                if (treeNode.right == null && treeNode.left == null) {
                    tmpList.clear();
                    break;
                } else {
                    if (treeNode.left != null) {
                        tmpList.add(treeNode.left);
                    }
                    if (treeNode.right != null){
                        tmpList.add(treeNode.right);
                    }
                }
            }
            nodeList = tmpList;
        }
        return minDepth;
    }
}

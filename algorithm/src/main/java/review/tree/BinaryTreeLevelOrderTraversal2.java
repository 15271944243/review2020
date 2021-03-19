package review.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/ No.107 二叉树层级遍历2
 * @author: xiaoxiaoxiang
 * @date: 2021/3/19 10:53
 */
public class BinaryTreeLevelOrderTraversal2 {

    /**
     * Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values.
     * (i.e., from left to right, level by level from leaf to root).
     *
     * Input: root = [3,9,20,null,null,15,7]
     * Output: [[15,7],[9,20],[3]]
     *
     * Input: root = [1]
     * Output: [[1]]
     *
     * Input: root = []
     * Output: []
     */

    /**
     * 题目意思: 给定一个二叉树,返回其节点值自底向上的层次遍历.(即按从叶子节点所在层到根节点所在的层,逐层从左向右遍历)
     * 思路一: 广度优先搜索(Breadth First Search)
     * 思路二: 深度优先搜索(Depth Fitst Search)
     */
    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal2 demo = new BinaryTreeLevelOrderTraversal2();
        TreeNode root = TreeNodeHelper.getTreeNode2();
        List<List<Integer>> result = demo.levelOrderBottom(root);
    }

    /**
     * 直接将 BinaryTreeLevelOrderTraversal 的结果反转就实现了
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> resultList = new ArrayList<>();
        List<TreeNode> treeNodeList = new ArrayList<>(1);
        treeNodeList.add(root);
        while (treeNodeList.size() != 0) {
            // 存储当前层级的节点的值
            List<Integer> currentHierarchyValues = new ArrayList<>();
            // 存储下一层级的节点
            List<TreeNode> nextHierarchyNodes = new ArrayList<>();
            treeNodeList.forEach(treeNode -> {
                currentHierarchyValues.add(treeNode.val);
                if (treeNode.left != null) {
                    nextHierarchyNodes.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    nextHierarchyNodes.add(treeNode.right);
                }
            });
            resultList.add(currentHierarchyValues);
            treeNodeList = nextHierarchyNodes;
        }
        // 上面的代码与BinaryTreeLevelOrderTraversal一样
        // 将结果反转就可以了
        List<List<Integer>> resultList2 = new ArrayList<>(resultList.size());
        for (int i = resultList.size() - 1; i >= 0; i--) {
            resultList2.add(resultList.get(i));
        }
        return resultList2;
    }
}

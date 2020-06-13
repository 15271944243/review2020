package review.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 * @author: xiaoxiaoxiang
 * @date: 2020/6/8 23:10
 */
public class BinaryTreeLevelOrderTraversal {

    /**
     * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
     */

    /**
     * 题目意思: 给定一个二叉树,返回他的层级顺序遍历的节点值;(从左到右,一层一层地遍历)
     * 思路一: 广度优先搜索(Breadth First Search)
     * 思路二: 深度优先搜索(Depth Fitst Search)
     */

//    For example:
//    Given binary tree [3,9,20,null,null,15,7],
//             3
//            / \
//           9  20
//             /  \
//            15   7
//    return its level order traversal as:
//            [
//            [3],
//            [9,20],
//            [15,7]
//            ]

    /**
     * 广度优先搜索BFS
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderByBFS(TreeNode root) {
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
        return resultList;
    }

}

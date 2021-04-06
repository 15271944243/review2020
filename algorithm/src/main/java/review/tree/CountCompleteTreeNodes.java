package review.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/count-complete-tree-nodes/ No.222 完全二叉树的节点个数
 * @author: xiaoxiaoxiang
 * @date: 2021/4/2 11:20
 */
public class CountCompleteTreeNodes {

    /**
     * Given the root of a complete binary tree, return the number of the nodes in the tree.
     *
     * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree,
     * and all nodes in the last level are as far left as possible.
     * It can have between 1 and 2h nodes inclusive at the last level h.
     *
     * Input: root = [1,2,3,4,5,6]
     * Output: 6
     *
     * Input: root = []
     * Output: 0
     *
     * Input: root = [1]
     * Output: 1
     *
     * The number of nodes in the tree is in the range [0, 5 * 10^4].
     * 0 <= Node.val <= 5 * 10^4
     * The tree is guaranteed to be complete.
     */


    /**
     * 题目意思: 给你一棵完全二叉树 的根节点 root,求出该树的节点个数。
     *
     * 完全二叉树的定义如下:在完全二叉树中,除了最底层节点可能没填满外,其余每层节点数都达到最大值,
     * 并且最下面一层的节点都集中在该层最左边的若干位置.若最底层为第 h 层,则该层包含 1~ 2h 个节点
     *
     * 思路: 广度优先搜索(BFS)
     */

    public static void main(String[] args) {
        TreeNode root = TreeNodeHelper.getTreeNode4();
        CountCompleteTreeNodes demo = new CountCompleteTreeNodes();
        int result = demo.countNodes(root);
    }

    /**
     * 思路: 广度优先搜索(BFS)
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        int count = 0;
        if (root == null) {
            return 0;
        }
        List<TreeNode> treeNodeList = new ArrayList<>();

        treeNodeList.add(root);
        while (!treeNodeList.isEmpty()) {
            List<TreeNode> tmpList = new ArrayList<>();
            for (TreeNode node : treeNodeList) {
                count++;
                if (node.left != null) {
                    tmpList.add(node.left);
                }
                if (node.right != null) {
                    tmpList.add(node.right);
                }
            }
            treeNodeList = tmpList;
        }
        return count;
    }
}

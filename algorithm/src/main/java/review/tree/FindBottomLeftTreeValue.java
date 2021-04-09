package review.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-bottom-left-tree-value/ No.513 找树左下角的值
 * @author: xiaoxiaoxiang
 * @date: 2021/4/7 11:24
 */
public class FindBottomLeftTreeValue {

    /**
     * Given the root of a binary tree, return the leftmost value in the last row of the tree.
     *
     * Input: root = [2,1,3]
     * Output: 1
     *
     * Input: root = [1,2,3,4,null,5,6,null,null,7]
     * Output: 7
     *
     * The number of nodes in the tree is in the range [1, 10^4].
     * -2^31 <= Node.val <= 2^31 - 1
     *
     */

    /**
     * 题目意思: 给定一个二叉树,在树的最后一行找到最左边的值
     *
     * 思路: BFS 遍历到最后一行,找最左边的节点的值
     */
    public static void main(String[] args) {
        FindBottomLeftTreeValue demo = new FindBottomLeftTreeValue();
        TreeNode root = demo.getTreeNode();
        int result = demo.findBottomLeftValue(root);
    }

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(root);
        while (!treeNodes.isEmpty()) {
            List<TreeNode> tmp = new ArrayList<>();
            for (TreeNode node : treeNodes) {
                if (node.left != null) {
                    tmp.add(node.left);
                }
                if (node.right != null) {
                    tmp.add(node.right);
                }
            }
            if (tmp.isEmpty()) {
                break;
            }
            treeNodes = tmp;
        }
        TreeNode left = treeNodes.get(0);
        return left.val;
    }

    private TreeNode getTreeNode() {
        // [2,1,3]
        TreeNode root = new TreeNode(2);
        TreeNode left1 = new TreeNode(1);
        TreeNode right1 = new TreeNode(3);
        root.setLeft(left1);
        root.setRight(right1);
        return root;
    }
}

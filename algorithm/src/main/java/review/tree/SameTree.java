package review.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/same-tree/ No.100 相同的树
 * @author: xiaoxiaoxiang
 * @date: 2021/4/7 11:14
 */
public class SameTree {

    /**
     * Given the roots of two binary trees p and q, write a function to check if
     * they are the same or not.
     *
     * Two binary trees are considered the same if they are structurally identical,
     * and the nodes have the same value.
     *
     * Input: p = [1,2,3], q = [1,2,3]
     * Output: true
     *
     * Input: p = [1,2], q = [1,null,2]
     * Output: false
     *
     * Input: p = [1,2,1], q = [1,1,2]
     * Output: false
     *
     * The number of nodes in both trees is in the range [0, 100].
     * -10^4 <= Node.val <= 10^4
     */

    /**
     * 题目意思: 给你两棵二叉树的根节点 p 和 q,编写一个函数来检验这两棵树是否相同
     * 如果两个树在结构上相同,并且节点具有相同的值，则认为它们是相同的。
     *
     * 思路一: BFS 遍历树节点进行判断
     * 思路二: 递归
     */
    public static void main(String[] args) {
        SameTree demo = new SameTree();
        TreeNode p = demo.getTreeNode();
        TreeNode q = demo.getTreeNode();
        boolean result = demo.isSameTree(p, q);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if ((p == null && q != null) || (p != null && q == null)
                || (p.val != q.val)) {
            return false;
        }
        List<TreeNode> pNodes = new ArrayList<>();
        List<TreeNode> qNodes = new ArrayList<>();
        pNodes.add(p);
        qNodes.add(q);
        boolean result = true;
        out: while (!pNodes.isEmpty() && !qNodes.isEmpty()) {
            if (pNodes.size() != qNodes.size()) {
                result = false;
                break;
            }
            List<TreeNode> pNodesTmp = new ArrayList<>();
            List<TreeNode> qNodesTmp = new ArrayList<>();
            for (int i = 0; i < pNodes.size(); i++) {
                TreeNode pNode = pNodes.get(i);
                TreeNode qNode = qNodes.get(i);
                if (pNode.left != null && qNode.left != null
                        && pNode.left.val == qNode.left.val) {
                    // 左子子节点相同
                    pNodesTmp.add(pNode.left);
                    qNodesTmp.add(qNode.left);
                } else if (!(pNode.left == null && qNode.left == null)) {
                    // 左子子节点不相同
                    result = false;
                    break out;
                }

                if (pNode.right != null && qNode.right != null
                        && pNode.right.val == qNode.right.val) {
                    // 右子子节点相同
                    pNodesTmp.add(pNode.right);
                    qNodesTmp.add(qNode.right);
                } else if (!(pNode.right == null && qNode.right == null)) {
                    // 右子子节点不相同
                    result = false;
                    break out;
                }
            }
            pNodes = pNodesTmp;
            qNodes = qNodesTmp;
        }
        return result;
    }

    private TreeNode getTreeNode() {
        // p = [1,2,3], q = [1,2,3]
        TreeNode root = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);
        root.setLeft(left1);
        root.setRight(right1);
        return root;
    }

}

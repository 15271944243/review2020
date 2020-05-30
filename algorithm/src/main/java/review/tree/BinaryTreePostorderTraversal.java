package review.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 * @author: xiaoxiaoxiang
 * @date: 2020/5/29 16:40
 */
public class BinaryTreePostorderTraversal {

    /**
     * Given a binary tree, return the postorder traversal of its nodes' values.
     */

    /**
     * 题目意思: 给定一个二叉树,返回它的后序遍历后的节点值
     *
     */

    // TODO 非递归算法

    /**
     * 后序遍历(LRD)
     * D=Degree
     * L=Left
     * R=Right
     * 结点拥有的子树数称为结点的度（Degree）
     * 1) 后序遍历左子树
     * 2) 后序遍历右子树
     * 3) 访问根结点
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> leftList = postorderTraversal(root.left);
        List<Integer> rightList = postorderTraversal(root.right);

        List<Integer> list = new ArrayList<>();

        if (leftList != null) {
            list.addAll(leftList);
        }

        if (rightList != null) {
            list.addAll(rightList);
        }

        list.add(root.val);
        return list;
    }
}

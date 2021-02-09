package review.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/2/9 14:38
 */
public class PathSum2 {

    /**
     * Given the root of a binary tree and an integer targetSum,
     * return all root-to-leaf paths where each path's sum equals targetSum.
     *
     * A leaf is a node with no children.
     *
     * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
     * Output: [[5,4,11,2],[5,8,4,5]]
     *
     * Input: root = [1,2,3], targetSum = 5
     * Output: []
     *
     * Input: root = [1,2], targetSum = 0
     * Output: []
     *
     * The number of nodes in the tree is in the range [0, 5000].
     * -1000 <= Node.val <= 1000
     * -1000 <= targetSum <= 1000
     */

    /**
     * 题目意思: 给定二叉树的根节点 root 和一个整数 targetSum, 找到所有从根节点到叶子节点路径总和等于 targetSum 的路径
     *
     * 思路: 采用递归-回溯-剪枝的思想
     */

    public static void main(String[] args) {
        PathSum2 demo = new PathSum2();
        TreeNode root = demo.treeNode2();
        int targetSum = -5;
        List<List<Integer>> result = demo.pathSum(root, targetSum);
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        recursion(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void recursion(TreeNode node, int targetSum, List<Integer> tmpResult, List<List<Integer>> result) {
        tmpResult.add(node.val);
        targetSum -= node.val;
        // 终止条件,记得要加上叶子节点的判断
        if (targetSum == 0 && node.left == null && node.right == null) {
            result.add(new ArrayList<>(tmpResult));
            tmpResult.remove(tmpResult.size() - 1);
            targetSum += node.val;
            return;
        }
        if (node.left != null) {
            recursion(node.left, targetSum, tmpResult, result);
        }
        if (node.right != null) {
            recursion(node.right, targetSum, tmpResult, result);
        }
        tmpResult.remove(tmpResult.size() - 1);
        targetSum += node.val;
    }

    private void recursion2(TreeNode node, int targetSum, List<Integer> tmpResult, List<List<Integer>> result) {
        // 终止条件,记得要加上叶子节点的判断
        if (targetSum - node.val == 0 && node.left == null && node.right == null) {
            result.add(new ArrayList<>(tmpResult));
            return;
        }
        if (node.left != null) {
            tmpResult.add(node.left.val);
            targetSum -= node.val;
            recursion(node.left, targetSum, tmpResult, result);
            tmpResult.remove(tmpResult.size() - 1);
            targetSum += node.val;
        }
        if (node.right != null) {
            tmpResult.add(node.right.val);
            targetSum -= node.val;
            recursion(node.right, targetSum, tmpResult, result);
            tmpResult.remove(tmpResult.size() - 1);
            targetSum += node.val;
        }
    }

    private TreeNode treeNode() {
        // [5,4,8,11,null,13,4,7,2,null,null,5,1]
        // targetSum = 22
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(4);
        TreeNode right = new TreeNode(8);
        root.setLeft(left);
        root.setRight(right);
        TreeNode left1 = new TreeNode(11);
        left.setLeft(left1);
        TreeNode left2 = new TreeNode(13);
        TreeNode right2 = new TreeNode(4);
        right.setLeft(left2);
        right.setRight(right2);
        TreeNode left3 = new TreeNode(7);
        TreeNode right3 = new TreeNode(2);
        left1.setLeft(left3);
        left1.setRight(right3);
        TreeNode left4 = new TreeNode(5);
        TreeNode right4 = new TreeNode(1);
        right2.setLeft(left4);
        right2.setRight(right4);
        return root;
    }

    private TreeNode treeNode2() {
        // [-2, null, -3]
        // targetSum = -5
        TreeNode root = new TreeNode(-2);
        TreeNode right = new TreeNode(-3);
        root.setRight(right);
        return root;
    }
}

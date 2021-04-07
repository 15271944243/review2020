package review.tree;

/**
 * https://leetcode.com/problems/path-sum/ No.112 路径总和
 * @author: xiaoxiaoxiang
 * @date: 2021/2/9 09:46
 */
public class PathSum {

    /**
     * Given the root of a binary tree and an integer targetSum,
     * return true if the tree has a root-to-leaf path such that adding up all the values
     * along the path equals targetSum.
     *
     * A leaf is a node with no children.
     *
     * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
     * Output: true
     *
     * Input: root = [1,2,3], targetSum = 5
     * Output: false
     *
     * Input: root = [1,2], targetSum = 0
     * Output: false
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [0, 5000].
     * -1000 <= Node.val <= 1000
     * -1000 <= targetSum <= 1000
     */

    /**
     * 题目意思: 给定二叉树的根节点 root 和一个整数 targetSum, 判断该树中是否存在根节点到叶子节点的路径
     * 这条路径上所有节点值相加等于目标和 targetSum
     *
     * 思路: 采用递归-回溯-剪枝的思想
     */

    public static void main(String[] args) {
        PathSum demo = new PathSum();
        TreeNode root = demo.treeNode2();
        int targetSum = -5;
        boolean result = demo.hasPathSum(root, targetSum);
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return recursion(root, targetSum, 0);
    }

    private boolean recursion(TreeNode node, int targetSum, int sum) {
        // 终止条件,记得要加上叶子节点的判断
        if (sum + node.val == targetSum && node.left == null && node.right == null) {
            return true;
        }
        // 这里不能这么剪枝,因为存在负数,例如 [-2, null, -3], targetSum = -5
        /*if (sum + node.val > targetSum) {
            return false;
        }*/
        if (node.left != null) {
            sum += node.val;
            boolean result = recursion(node.left, targetSum, sum);
            if (result) {
                return true;
            }
            sum -= node.val;
        }
        if (node.right != null) {
            sum += node.val;
            boolean result = recursion(node.right, targetSum, sum);
            if (result) {
                return true;
            }
            sum -= node.val;
        }
        return false;
    }

    private TreeNode treeNode() {
        // [5,4,8,11,null,13,4,7,2,null,null,null,1]
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
        TreeNode right4 = new TreeNode(1);
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
